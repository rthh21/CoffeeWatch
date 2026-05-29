# Project Status Report - CoffeeWatch

This document reflects the current status of the project according to the imposed requirements.

## 1) System Definition
| Requirement | Status | Location / Details |
| :--- | :---: | :--- |
| List of at least 15 actions | ✅ | Defined in `ro.watchmanager.service.MagazinService` |
| List of at least 10 object types | ✅ | `ro.watchmanager.model` package (Brand, Ceas, CeasMecanic, Client, Comanda, Curea, Furnizor, Recenzie, Smartwatch, Voucher) |

## 2) Implementation
| Requirement | Status | Location / Details |
| :--- | :---: | :--- |
| Simple classes with encapsulation | ✅ | All models use private access modifiers and getter/setter methods. |
| At least 3 different collections (one sorted) | ✅ | `TreeSet` (sorted), `HashMap`, `ArrayList` in `MagazinService`. |
| Class inheritance | ✅ | `Ceas` -> `CeasMecanic`, `Smartwatch`. |
| Interface implementation | ✅ | `Discountable` interface is implemented by the `Ceas` class. |
| Custom exceptions | ✅ | `StocInsuficientException` in the `exception` package. |
| Service class | ✅ | `MagazinService` exposes the system's operations. |
| Main class | ✅ | `ro.watchmanager.main.Main` performs service calls. |

## 3) Persistence (JDBC)
| Requirement | Status | Location / Details |
| :--- | :---: | :--- |
| Database modeling (tables, relationships) | ✅ | Defined in `schema.sql`. |
| ERD Diagram | ✅ | Defined in `ERD.md` (Mermaid format). |
| CRUD services for 6 objects | ✅ | Complete operations in Repositories: Brand, Ceas, Client, Comanda, Curea, Recenzie. |
| Generic DB singleton services | ✅ | All repositories extend `GenericRepository` and are implemented as Singletons. |

## 4) Audit Service
| Requirement | Status | Location / Details |
| :--- | :---: | :--- |
| CSV audit service (name, timestamp) | ✅ | `ro.watchmanager.service.AuditService`. |

## 5) Coding best practices
| Design Pattern | Status | Location / Details |
| :--- | :---: | :--- |
| Singleton | ✅ | `AuditService`, `DatabaseConnectionManager`. |
| Builder | ✅ | `ClientBuilder`. |
| Factory | ✅ | `CeasFactory`. |

## 6) Graphical Interface
| Requirement | Status | Location / Details |
| :--- | :---: | :--- |
| JavaFX (Window, Menu, List, Form) | ❌ | **Missing**. The project does not contain a graphical interface. |

---
**Note:** The project compiles and runs via the `compile_and_run.ps1` script (for Windows environments) or manually by calling `Main`.
