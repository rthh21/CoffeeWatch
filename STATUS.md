# Raport Status Proiect - CoffeeWatch

Acest document reflectă stadiul actual al proiectului conform cerințelor impuse.

## 1) Definirea sistemului
| Cerință | Status | Locație / Detalii |
| :--- | :---: | :--- |
| Listă cu cel puțin 15 acțiuni | ✅ | Definite în `ro.watchmanager.service.MagazinService` |
| Listă cu cel puțin 10 tipuri de obiecte | ✅ | Pachetul `ro.watchmanager.model` (Brand, Ceas, CeasMecanic, Client, Comanda, Curea, Furnizor, Recenzie, Smartwatch, Voucher) |

## 2) Implementare
| Cerință | Status | Locație / Detalii |
| :--- | :---: | :--- |
| Clase simple cu încapsulare | ✅ | Toate modelele folosesc modificatori de acces privați și metode de tip getter/setter. |
| Cel puțin 3 colecții diferite (una sortată) | ✅ | `TreeSet` (sortat), `HashMap`, `ArrayList` în `MagazinService`. |
| Moștenirea claselor | ✅ | `Ceas` -> `CeasMecanic`, `Smartwatch`. |
| Implementarea interfețelor | ✅ | Interfața `Discountable` este implementată de clasa `Ceas`. |
| Excepții custom | ✅ | `StocInsuficientException` în pachetul `exception`. |
| Clasă serviciu | ✅ | `MagazinService` expune operațiile sistemului. |
| Clasa Main | ✅ | `ro.watchmanager.main.Main` realizează apelurile către servicii. |

## 3) Persistență (JDBC)
| Cerință | Status | Locație / Detalii |
| :--- | :---: | :--- |
| Modelare baze de date (tabele, relații) | ✅ | Definite în `schema.sql`. |
| Diagramă ERD | ✅ | Definită în `ERD.md` (format Mermaid). |
| Servicii CRUD pentru 6 obiecte | ✅ | Operații complete în Repozitorii: Brand, Ceas, Client, Comanda, Curea, Recenzie. |
| Servicii singleton generice DB | ✅ | Toate repozitoriile extind `GenericRepository` și sunt implementate ca Singleton. |

## 4) Serviciu de audit
| Cerință | Status | Locație / Detalii |
| :--- | :---: | :--- |
| Serviciu audit CSV (nume, timestamp) | ✅ | `ro.watchmanager.service.AuditService`. |

## 5) Coding best practices
| Design Pattern | Status | Locație / Detalii |
| :--- | :---: | :--- |
| Singleton | ✅ | `AuditService`, `DatabaseConnectionManager`. |
| Builder | ✅ | `ClientBuilder`. |
| Factory | ✅ | `CeasFactory`. |

## 6) Interfața grafică
| Cerință | Status | Locație / Detalii |
| :--- | :---: | :--- |
| JavaFX (Fereastră, Meniu, Listă, Formular) | ❌ | **Lipsă**. Proiectul nu conține o interfață grafică. |

---
**Notă:** Proiectul se compilează și rulează prin intermediul scriptului `compile_and_run.ps1` (pentru mediile Windows) sau manual prin apelarea `Main`.
 prin apelarea `Main`.
