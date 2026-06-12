```mermaid
erDiagram
    Product {
        UUID id
        String description
        Integer qtyOnHand
        Double unitPrice
    }

    OrderDetails {
        UUID id
        Integer qty
        Double unitPrice
    }

    Customer {
        UUID id
        String address
        String name
        double salary
    }

    CustomerOrder {
        UUID orderId
        LocalDate date
        double totalCost
    }

    Product ||--o{ OrderDetails : "detailsList"

    OrderDetails ||--|| CustomerOrder : "customerOrder"
    OrderDetails ||--|| Product : "product"

    Customer ||--o{ CustomerOrder : "orders"

    CustomerOrder ||--|| Customer : "customer"
    CustomerOrder ||--o{ OrderDetails : "detailsList"

```
