# Watch Shop Management Platform (Stage 1)

The project aims to manage a watch shop. The application allows the administration of stock products, customers, and placed orders, along with other specific details (reviews, accessories).

## 1. System Definition

**Object Types (Entities):**
The system consists of 8 objects:
1. `Ceas` (base class for products)
2. `CeasMecanic` (subclass for classic/mechanical watches)
3. `Smartwatch` (subclass for smartwatches)
4. `Brand` (manufacturer details)
5. `Client` (buyer details)
6. `Comanda` (placed shopping cart)
7. `Curea` (watch accessories/details)
8. `Recenzie` (customer feedback)

**Main Actions and Queries:**
Within the system, I have defined and implemented the following 15 actions:
1. Adding a new watch to the shop's stock.
2. Registering a new user/customer in the database.
3. Placing an order (with stock update).
4. Displaying all available watches, automatically sorted by price.
5. Filtering and displaying products based on a specific brand.
6. Modifying details (stock and price) for an existing watch model.
7. Completely removing a watch from the system.
8. Viewing the detailed order history for a specific customer.
9. Adding a review to a specific watch in stock.
10. Calculating revenue (total value of orders) recorded for a specific date.
11. Applying a general discount for all products in stock.
12. Obtaining the list of watches with limited stock (below a specified threshold).
13. Removing a customer from the database.
14. Calculating the average value of all placed orders.
15. Obtaining a top of the most reviewed products.

## 2. Implementation Details

The application is developed strictly in Java, adhering to the following requirements:

- **Classes and Encapsulation**: Private or protected attributes, their state being controlled through constructors and access methods (getters and setters).
- **Collections Used**: 
  - `TreeSet<Ceas>`: used for the watch stock. Thus, the collection is always sorted (fulfilling the requirement for a sorted collection).
  - `HashMap<String, Client>`: used for rapid registration and searching of customers by a unique key (email address).
  - `List<Comanda>`: used to maintain the history of all orders.
- **Inheritance and Polymorphism**: The base class `Ceas` is actively inherited by `CeasMecanic` and `Smartwatch`. These derived objects are used and integrated together with the base objects in the same stock collection.
- **Service Class**: The application logic has been decoupled from the models through the `MagazinService` class. This has the role of managing collections and exposing all system operations and queries.
- **Main Class**: Represents the entry point of the application. Here, test data is instantiated and successive calls are made to the service class to demonstrate functionalities.
