# WatchManager - Platformă pentru Gestiunea unui Magazin de Ceasuri

Proiectul are ca scop gestionarea unui magazin de ceasuri. Aplicația permite administrarea produselor din stoc, a clienților și a comenzilor plasate, alături de alte detalii specifice (recenzii, accesorii).

## 1. Definirea sistemului

**Tipuri de obiecte (entități):**
Sistemul este modelat folosind 8 tipuri principale de obiecte:
1. `Ceas` (clasa de bază pentru produse)
2. `CeasMecanic` (subclasă pentru ceasurile clasice/mecanice)
3. `Smartwatch` (subclasă pentru ceasurile inteligente)
4. `Brand` (detalii despre producător)
5. `Client` (detaliile cumpărătorilor)
6. `Comanda` (coșul de cumpărături plasat)
7. `Curea` (accesorii/detalii pentru ceasuri)
8. `Recenzie` (feedback din partea clienților)

**Acțiuni și interogări principale:**
În cadrul sistemului au fost definite și implementate următoarele 10 acțiuni:
1. Adăugarea unui ceas nou în stocul magazinului.
2. Înregistrarea unui utilizator/client nou în baza de date.
3. Plasarea unei comenzi (cu actualizarea aferentă a stocului).
4. Afișarea tuturor ceasurilor disponibile, sortate automat după preț.
5. Filtrarea și afișarea produselor pe baza unui anumit brand.
6. Modificarea detaliilor (stocului și a prețului) pentru un anumit model de ceas deja existent.
7. Eliminarea completă a unui ceas din sistem.
8. Vizualizarea istoricului detaliat de comenzi pentru un client specific.
9. Adăugarea unei recenzii la un anumit ceas din stoc.
10. Calcularea încasărilor (valoarea totală a comenzilor) înregistrate pentru o anumită dată calendaristică.

## 2. Detalii de implementare

Aplicația este dezvoltată strict în Java, respectând cerințele OOP stabilite:

- **Clase și încapsulare**: Toate entitățile de mai sus au atribute private sau protected, starea lor fiind controlată prin constructori și metode de acces (getteri și setteri).
- **Colecții utilizate**: 
  - `TreeSet<Ceas>`: folosit pentru stocul de ceasuri. Astfel, colecția este mereu sortată (îndeplinind cerința pentru colecție sortată).
  - `HashMap<String, Client>`: utilizat pentru înregistrarea și căutarea rapidă a clienților după o cheie unică (adresa de email).
  - `List<Comanda>`: folosită pentru a menține istoricul tuturor comenzilor.
- **Moștenire și polimorfism**: Clasa de bază `Ceas` este moștenită activ de `CeasMecanic` și `Smartwatch`. Aceste obiecte derivate sunt folosite și integrate împreună cu obiectele de bază în aceeași colecție de stoc.
- **Clasa Serviciu**: Logica aplicației a fost decuplată de modele prin introducerea clasei `MagazinService`. Aceasta are rolul de a gestiona colecțiile și de a expune toate operațiile și interogările sistemului.
- **Clasa Main**: Reprezintă punctul de intrare în aplicație. Aici se instanțiază datele de test și se fac apeluri succesive către clasa de serviciu pentru a demonstra funcționalitățile.
