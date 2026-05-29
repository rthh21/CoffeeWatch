# WatchManager - Project Documentation

The application is structured to manage the stock and orders of a watch shop, using Java, JDBC (PostgreSQL), and Design Patterns.

## Project Stages

### Stage 1: Extending the Model and Collections
*   **Custom Interface**: `Discountable` (applies discounts).
    *   Location: `model/Discountable.java`
*   **Custom Exception**: `StocInsuficientException` (thrown when placing an order).
    *   Location: `exception/StocInsuficientException.java`
*   **New Model Classes**: `Furnizor` and `Voucher`.
    *   Location: `model/Furnizor.java`, `model/Voucher.java`

### Stage 2: Advanced Service Logic
*   Added 5 new methods in `MagazinService` for data processing (general discount, top products, etc.).
    *   Location: `service/MagazinService.java`

### Stage 3: Data Persistence (JDBC & PostgreSQL)
*   **Database Configuration**: Switching to PostgreSQL and securing queries through `PreparedStatement`.
    *   Connection Manager: `repository/DatabaseConnectionManager.java`
    *   SQL Script: `schema.sql` (uses specific Postgres data types, e.g., `SERIAL`).
*   **Repositories (CRUD)**: Classes that manage operations on tables (Client, Brand, Ceas, etc.).
    *   Location: `repository/` folder

### Stage 4: Audit Service
*   All service actions are automatically logged in a CSV file.
    *   Location: `service/AuditService.java`
    *   Result: `audit.csv`

### Stage 5: Design Patterns
*   **Singleton**: `DatabaseConnectionManager`, `AuditService`.
*   **Factory**: `CeasFactory` (to create instances of CeasMecanic/Smartwatch).
    *   Location: `factory/CeasFactory.java`
*   **Builder**: `ClientBuilder` (for constructing Client objects).
    *   Location: `model/ClientBuilder.java`

## SQL Security
All queries use `PreparedStatement` to prevent SQL Injection attacks. There are no queries built by string concatenation.

## File Structure
```text
ro/watchmanager/
├── exception/
│   └── StocInsuficientException.java
├── factory/
│   └── CeasFactory.java
├── model/
│   ├── Ceas.java (implements Discountable)
│   ├── ClientBuilder.java
│   ├── Discountable.java
│   ├── Furnizor.java
│   └── Voucher.java
├── repository/
│   ├── DatabaseConnectionManager.java (Singleton, Postgres)
│   ├── BrandRepository.java
│   ├── ClientRepository.java
│   ├── ... (rest of the repositories)
└── service/
    ├── AuditService.java (Singleton)
    └── MagazinService.java
```
