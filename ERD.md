# ERD Diagram - CoffeeWatch

Below is the relational representation of the database using Mermaid syntax.

```mermaid
erDiagram
    BRAND ||--o{ CEAS : "produces"
    CUREA ||--o{ CEAS : "is mounted on"
    CEAS ||--o{ RECENZIE : "receives"
    CLIENT ||--o{ COMANDA : "places"
    COMANDA ||--|{ COMANDA_CEAS : "contains"
    CEAS ||--|{ COMANDA_CEAS : "appears in"

    BRAND {
        int id PK
        string name
        string country_of_origin
    }

    CUREA {
        int id PK
        string material
        string color
        int width
    }

    CEAS {
        string id PK
        int brand_id FK
        string model_name
        double price
        int stock
        int strap_id FK
        string type
        string mechanism
        int battery_life
    }

    CLIENT {
        string email PK
        string name
        string phone
    }

    RECENZIE {
        int id PK
        string watch_id FK
        string user
        string comment
        int rating
    }

    COMANDA {
        string order_id PK
        string client_email FK
        date order_date
        double total_value
    }

    COMANDA_CEAS {
        string order_id PK, FK
        string watch_id PK, FK
    }
```

## Relationship Explanation:
1.  **Brand -> Ceas**: A brand can produce multiple watch models (1:N).
2.  **Curea -> Ceas**: A strap can be used for multiple watches (1:N).
3.  **Ceas -> Recenzie**: A watch can have multiple reviews from different users (1:N).
4.  **Client -> Comanda**: A client can place multiple orders over time (1:N).
5.  **Comanda -> Ceas (M:N)**: An order can contain multiple watches, and a watch can appear in multiple orders. This is achieved through the `Comanda_Ceas` link table.
