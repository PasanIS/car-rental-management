package com.devstack.POS.service.impl;

import com.devstack.POS.dto.request.ProductRequestDTO;
import com.devstack.POS.dto.response.PagedResponseDTO;
import com.devstack.POS.dto.response.ProductResponseDTO;
import com.devstack.POS.entity.Product;
import com.devstack.POS.exception.EntryNotFoundException;
import com.devstack.POS.repository.ProductRepo;
import com.devstack.POS.service.ProductService;
import com.devstack.POS.util.CustomerMapper;
import com.devstack.POS.util.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceIMPL implements ProductService {

    private final ProductRepo productRepo;
    private final ProductMapper productMapper;

    @Override
    public void createProduct(ProductRequestDTO dto) {
        productRepo.save(productMapper.toProduct(dto));
    }

    @Override
    public void updateProduct(ProductRequestDTO dto, UUID id) {
        Optional<Product> selectedProduct = productRepo.findById(id);
        if (selectedProduct.isEmpty()) {
            throw new EntryNotFoundException("Product Not Found");
        }
        Product product = selectedProduct.get();
        product.setDescription(dto.getDescription());
        product.setUnitPrice(dto.getUnitPrice());
        product.setQtyOnHand(dto.getQtyOnHand());
        productRepo.save(product);
    }

    @Override
    public void deleteProduct(UUID id) {
        productRepo.deleteById(id);
    }

    @Override
    public ProductResponseDTO findProductById(UUID id) {
        Optional<Product> selectedProduct = productRepo.findById(id);
        if (selectedProduct.isEmpty()) {
            throw new EntryNotFoundException("Product Not Found");
        }
        return productMapper.toProductResponseDTO(selectedProduct.get());
    }

    @Override
    public PagedResponseDTO<ProductResponseDTO> searchProducts(String searchText, int page, int size) {
        return

                PagedResponseDTO.<ProductResponseDTO>builder()
                        .dataList(productRepo
                                .findAllProducts("%"+searchText+"%", PageRequest.of(page, size))
                                .stream()
                                .map(productMapper::toProductResponseDTO).toList())
                        .dataCount(productRepo.countAllProducts("%"+searchText+"%"))
                        .build();
    }
}
