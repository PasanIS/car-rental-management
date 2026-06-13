```mermaid
erDiagram
    OrderDetails {
        UUID id
        Integer qty
        Double unitPrice
    }

    CustomerOrder {
        UUID orderId
        LocalDate date
        double totalCost
    }

    Product {
        UUID id
        String description
        Integer qtyOnHand
        Double unitPrice
    }

    Customer {
        UUID id
        String address
        String name
        double salary
    }

    OrderDetails ||--|| CustomerOrder : "customerOrder"
    OrderDetails ||--|| Product : "product"

    CustomerOrder ||--|| Customer : "customer"
    CustomerOrder ||--o{ OrderDetails : "detailsList"

    Product ||--o{ OrderDetails : "detailsList"

    Customer ||--o{ CustomerOrder : "orders"

```
