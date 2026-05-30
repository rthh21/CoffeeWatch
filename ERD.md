# ERD Diagram - CoffeeWatch

Below is the relational representation of the database using Mermaid syntax.

```mermaid
erDiagram
    BRAND ||--o{ WATCH : "produces"
    STRAP ||--o{ WATCH : "is mounted on"
    WATCH ||--o{ REVIEW : "receives"
    CLIENT ||--o{ ORDERS : "places"
    ORDERS ||--|{ ORDER_WATCH : "contains"
    WATCH ||--|{ ORDER_WATCH : "appears in"

    BRAND {
        int id PK
        string name
        string country_of_origin
    }

    STRAP {
        int id PK
        string material
        string color
        int size_mm
    }

    WATCH {
        string id PK
        int brand_id FK
        string model_name
        double price
        int stock
        int strap_id FK
        string type
        string mechanism_type
        int power_reserve
        string operating_system
        int battery_capacity
    }

    CLIENT {
        string email PK
        string name
        string phone_number
    }

    REVIEW {
        int id PK
        string watch_id FK
        string user_name
        string text_content
        int rating
    }

    ORDERS {
        string order_id PK
        string client_email FK
        date order_date
        double total_value
    }

    ORDER_WATCH {
        string order_id PK, FK
        string watch_id PK, FK
    }
```

## Relationship Explanation:
1.  **Brand -> Watch**: A brand can produce multiple watch models (1:N).
2.  **Strap -> Watch**: A strap can be used for multiple watches (1:N).
3.  **Watch -> Review**: A watch can have multiple reviews from different users (1:N).
4.  **Client -> Orders**: A client can place multiple orders over time (1:N).
5.  **Orders -> Watch (M:N)**: An order can contain multiple watches, and a watch can appear in multiple orders. This is achieved through the `Order_Watch` link table.
