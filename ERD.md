# Diagrama ERD - CoffeeWatch

Mai jos este reprezentarea relațională a bazei de date utilizând sintaxa Mermaid.

```mermaid
erDiagram
    BRAND ||--o{ CEAS : "produce"
    CUREA ||--o{ CEAS : "este montată pe"
    CEAS ||--o{ RECENZIE : "primește"
    CLIENT ||--o{ COMANDA : "plasează"
    COMANDA ||--|{ COMANDA_CEAS : "conține"
    CEAS ||--|{ COMANDA_CEAS : "apare în"

    BRAND {
        int id PK
        string nume
        string tara_origine
    }

    CUREA {
        int id PK
        string material
        string culoare
        int latime
    }

    CEAS {
        string id PK
        int brand_id FK
        string nume_model
        double pret
        int stoc
        int curea_id FK
        string tip
        string mecanism
        int autonomie_baterie
    }

    CLIENT {
        string email PK
        string nume
        string telefon
    }

    RECENZIE {
        int id PK
        string ceas_id FK
        string utilizator
        string comentariu
        int nota
    }

    COMANDA {
        string id_comanda PK
        string client_email FK
        date data_comanda
        double valoare_totala
    }

    COMANDA_CEAS {
        string id_comanda PK, FK
        string ceas_id PK, FK
    }
```

## Explicație Relații:
1.  **Brand -> Ceas**: Un brand poate produce mai multe modele de ceasuri (1:N).
2.  **Curea -> Ceas**: O curea poate fi folosită pentru mai multe ceasuri (1:N).
3.  **Ceas -> Recenzie**: Un ceas poate avea mai multe recenzii de la utilizatori diferiți (1:N).
4.  **Client -> Comanda**: Un client poate plasa mai multe comenzi în timp (1:N).
5.  **Comanda -> Ceas (M:N)**: O comandă poate conține mai multe ceasuri, iar un ceas poate apărea în mai multe comenzi. Aceasta este realizată prin tabela de legătură `Comanda_Ceas`.
