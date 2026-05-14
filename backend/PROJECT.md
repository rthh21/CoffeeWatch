# WatchManager - Documentație Proiect

Aplicația este structurată pentru a gestiona stocul și comenzile unui magazin de ceasuri, folosind Java, JDBC (PostgreSQL) și Design Patterns.

## Etapele Proiectului

### Etapa 1: Extinderea Modelului și a Colecțiilor
*   **Interfață Custom**: `Discountable` (aplică reduceri).
    *   Locație: `model/Discountable.java`
*   **Excepție Custom**: `StocInsuficientException` (aruncată la plasarea comenzii).
    *   Locație: `exception/StocInsuficientException.java`
*   **Clase Model Noi**: `Furnizor` și `Voucher`.
    *   Locație: `model/Furnizor.java`, `model/Voucher.java`

### Etapa 2: Logică Service Avansată
*   S-au adăugat 5 metode noi în `MagazinService` pentru procesarea datelor (discount general, top produse, etc.).
    *   Locație: `service/MagazinService.java`

### Etapa 3: Persistența Datelor (JDBC & PostgreSQL)
*   **Configurare Bază de Date**: Trecerea la PostgreSQL și securizarea interogărilor prin `PreparedStatement`.
    *   Manager Conexiune: `repository/DatabaseConnectionManager.java`
    *   Script SQL: `schema.sql` (folosește tipuri de date specifice Postgres, ex: `SERIAL`).
*   **Repozitorii (CRUD)**: Clase care gestionează operațiile pe tabele (Client, Brand, Ceas, etc.).
    *   Locație: folderul `repository/`

### Etapa 4: Serviciu de Audit
*   Toate acțiunile din service sunt logate automat într-un fișier CSV.
    *   Locație: `service/AuditService.java`
    *   Rezultat: `audit.csv`

### Etapa 5: Design Patterns
*   **Singleton**: `DatabaseConnectionManager`, `AuditService`.
*   **Factory**: `CeasFactory` (pentru a crea instanțe de CeasMecanic/Smartwatch).
    *   Locație: `factory/CeasFactory.java`
*   **Builder**: `ClientBuilder` (pentru construcția obiectelor Client).
    *   Locație: `model/ClientBuilder.java`

## Securitate SQL
Toate interogările folosesc `PreparedStatement` pentru a preveni atacurile de tip SQL Injection. Nu există query-uri construite prin concatenare de șiruri.

## Structură Fișiere
```text
ro/watchmanager/
├── exception/
│   └── StocInsuficientException.java
├── factory/
│   └── CeasFactory.java
├── model/
│   ├── Ceas.java (implementează Discountable)
│   ├── ClientBuilder.java
│   ├── Discountable.java
│   ├── Furnizor.java
│   └── Voucher.java
├── repository/
│   ├── DatabaseConnectionManager.java (Singleton, Postgres)
│   ├── BrandRepository.java
│   ├── ClientRepository.java
│   ├── ... (restul repozitoriilor)
└── service/
    ├── AuditService.java (Singleton)
    └── MagazinService.java
```
