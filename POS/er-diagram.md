```mermaid
erDiagram
    Customer {
        UUID id
        String address
        String name
        double salary
    }

    Product {
        UUID id
        String description
        Integer qtyOnHand
        Double unitPrice
    }

    CustomerOrder {
        UUID orderId
        LocalDate date
        double totalCost
    }

    OrderDetails {
        UUID id
        Integer qty
        Double unitPrice
    }

    Customer ||--o{ CustomerOrder : "orders"

    Product ||--o{ OrderDetails : "detailsList"

    CustomerOrder ||--|| Customer : "customer"
    CustomerOrder ||--o{ OrderDetails : "detailsList"

    OrderDetails ||--|| CustomerOrder : "customerOrder"
    OrderDetails ||--|| Product : "product"

```
