CREATE TABLE Brand (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nume VARCHAR(100) NOT NULL,
    tara_origine VARCHAR(100)
);

CREATE TABLE Curea (
    id INT AUTO_INCREMENT PRIMARY KEY,
    material VARCHAR(50),
    culoare VARCHAR(50),
    latime INT
);

CREATE TABLE Ceas (
    id VARCHAR(50) PRIMARY KEY,
    brand_id INT,
    nume_model VARCHAR(100),
    pret DOUBLE,
    stoc INT,
    curea_id INT,
    tip VARCHAR(20),
    mecanism VARCHAR(100), -- pt Mecanic
    autonomie_baterie INT, -- pt Smartwatch
    FOREIGN KEY (brand_id) REFERENCES Brand(id),
    FOREIGN KEY (curea_id) REFERENCES Curea(id)
);

CREATE TABLE Client (
    email VARCHAR(100) PRIMARY KEY,
    nume VARCHAR(100),
    telefon VARCHAR(20)
);

CREATE TABLE Recenzie (
    id SERIAL PRIMARY KEY,
    ceas_id VARCHAR(50),
    utilizator VARCHAR(100),
    comentariu TEXT,
    nota INT,
    FOREIGN KEY (ceas_id) REFERENCES Ceas(id)
);

CREATE TABLE Comanda (
    id_comanda VARCHAR(50) PRIMARY KEY,
    client_email VARCHAR(100),
    data_comanda DATE,
    valoare_totala DOUBLE,
    FOREIGN KEY (client_email) REFERENCES Client(email)
);

CREATE TABLE Comanda_Ceas (
    id_comanda VARCHAR(50),
    ceas_id VARCHAR(50),
    PRIMARY KEY (id_comanda, ceas_id),
    FOREIGN KEY (id_comanda) REFERENCES Comanda(id_comanda),
    FOREIGN KEY (ceas_id) REFERENCES Ceas(id)
);
