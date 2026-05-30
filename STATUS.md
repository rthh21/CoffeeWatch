# Project Status Report - CoffeeWatch

This document reflects the current status of the project according to the imposed requirements.

## 1) System Definition
| Requirement | Status | Location / Details |
| :--- | :---: | :--- |
| List of at least 15 actions | ✅ | Defined in `ro.watchmanager.service.ShopService` (exposes 18 service actions) |
| List of at least 10 object types | ✅ | `ro.watchmanager.model` package (Brand, Client, MechanicalWatch, MechanismType, Order, Rating, Review, Smartwatch, Strap, Supplier, Voucher, Watch) |

## 2) Implementation
| Requirement | Status | Location / Details |
| :--- | :---: | :--- |
| Simple classes with encapsulation | ✅ | All models use private access modifiers and getter/setter methods. |
| At least 3 different collections (one sorted) | ✅ | `TreeSet` (sorted), `HashMap`, `ArrayList` in `ShopService`. |
| Class inheritance | ✅ | `Watch` -> `MechanicalWatch`, `Smartwatch`. |
| Interface implementation | ✅ | `Discountable` interface is implemented by the `Watch` class. |
| Custom exceptions | ✅ | `InsufficientStockException` in the `exception` package. |
| Service class | ✅ | `ShopService` exposes the system's operations. |
| Main class | ✅ | `ro.watchmanager.main.Main` performs service calls. |

## 3) Persistence (JDBC)
| Requirement | Status | Location / Details |
| :--- | :---: | :--- |
| Database modeling (tables, relationships) | ✅ | Defined in `schema.sql`. |
| ERD Diagram | ✅ | Defined in `ERD.md` (Mermaid format). |
| CRUD services for 6 objects | ✅ | Complete operations in Repositories: Brand, Watch, Client, Order, Strap, Review. |
| Generic DB singleton services | ✅ | All repositories extend `GenericRepository` and are implemented as Singletons. |

## 4) Audit Service
| Requirement | Status | Location / Details |
| :--- | :---: | :--- |
| CSV audit service (name, timestamp) | ✅ | `ro.watchmanager.service.AuditService` (logs to `audit.csv`). |

## 5) Coding best practices
| Design Pattern | Status | Location / Details |
| :--- | :---: | :--- |
| Singleton | ✅ | `AuditService`, `DatabaseConnectionManager`, and all repositories. |
| Builder | ✅ | `ClientBuilder`. |
| Factory | ✅ | `WatchFactory`. |

## 6) Graphical Interface
| Requirement | Status | Location / Details |
| :--- | :---: | :--- |
| Modern Web GUI (Window, Menu, List, Form) | ✅ | **Complete**. Built as a gorgeous single-page Angular application. Contains 1 viewport Window, a navigation Header Menu with 4 options (Home, Catalog, Admin, Orders), a Catalog grid List, and an Add Watch Form in the Admin panel. |

---
**Note:** The project compiles and runs via the `start.sh` script (for Linux environments) or `compile_and_run.ps1` (for Windows environments).
