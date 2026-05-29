CREATE TABLE Brand (
    id SERIAL PRIMARY KEY,
    nume VARCHAR(100) NOT NULL,
    tara_origine VARCHAR(100)
);

CREATE TABLE Curea (
    id SERIAL PRIMARY KEY,
    material VARCHAR(50),
    culoare VARCHAR(50),
    latime INT
);

CREATE TABLE Ceas (
    id VARCHAR(50) PRIMARY KEY,
    brand_id INT,
    nume_model VARCHAR(100),
    pret DOUBLE PRECISION,
    stoc INT,
    curea_id INT,
    tip VARCHAR(20),
    mecanism VARCHAR(100), -- for Mechanical
    rezerva_putere INT, -- for Mechanical
    sistem_operare VARCHAR(50), -- for Smartwatch
    autonomie_baterie INT, -- for Smartwatch
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
    valoare_totala DOUBLE PRECISION,
    FOREIGN KEY (client_email) REFERENCES Client(email)
);

CREATE TABLE Comanda_Ceas (
    id_comanda VARCHAR(50),
    ceas_id VARCHAR(50),
    PRIMARY KEY (id_comanda, ceas_id),
    FOREIGN KEY (id_comanda) REFERENCES Comanda(id_comanda),
    FOREIGN KEY (ceas_id) REFERENCES Ceas(id)
);

-- INSERT MOCK DATA
INSERT INTO Brand (id, nume, tara_origine) VALUES (1, 'Rolex', 'Switzerland');
INSERT INTO Brand (id, nume, tara_origine) VALUES (2, 'Piaget', 'Switzerland');
INSERT INTO Brand (id, nume, tara_origine) VALUES (3, 'Apple', 'USA');
INSERT INTO Brand (id, nume, tara_origine) VALUES (4, 'NOMOS', 'Germany');
INSERT INTO Brand (id, nume, tara_origine) VALUES (5, 'Omega', 'Switzerland');
INSERT INTO Brand (id, nume, tara_origine) VALUES (6, 'Cartier', 'France');
INSERT INTO Brand (id, nume, tara_origine) VALUES (7, 'Garmin', 'USA');
INSERT INTO Brand (id, nume, tara_origine) VALUES (8, 'Tudor', 'Switzerland');
INSERT INTO Brand (id, nume, tara_origine) VALUES (9, 'Samsung', 'South Korea');
INSERT INTO Brand (id, nume, tara_origine) VALUES (10, 'Patek Philippe', 'Switzerland');

INSERT INTO Brand (id, nume, tara_origine) VALUES (11, 'Seiko', 'Japan');
INSERT INTO Brand (id, nume, tara_origine) VALUES (12, 'Casio', 'Japan');
INSERT INTO Brand (id, nume, tara_origine) VALUES (13, 'Tissot', 'Switzerland');
INSERT INTO Brand (id, nume, tara_origine) VALUES (14, 'Longines', 'Switzerland');

INSERT INTO Ceas (id, brand_id, nume_model, pret, stoc, tip, mecanism, rezerva_putere) VALUES ('C1', 1, 'Datejust 41mm Dark', 45000, 5, 'Mechanical', 'AUTOMAT', 70);
INSERT INTO Ceas (id, brand_id, nume_model, pret, stoc, tip, mecanism, rezerva_putere) VALUES ('R2', 1, 'Submariner Date', 65000, 3, 'Mechanical', 'AUTOMAT', 72);
INSERT INTO Ceas (id, brand_id, nume_model, pret, stoc, tip, mecanism, rezerva_putere) VALUES ('R3', 1, 'Daytona Platinum', 380000, 1, 'Mechanical', 'AUTOMAT', 72);
INSERT INTO Ceas (id, brand_id, nume_model, pret, stoc, tip, mecanism, rezerva_putere) VALUES ('R4', 1, 'GMT-Master II Pepsi', 95000, 2, 'Mechanical', 'AUTOMAT', 70);
INSERT INTO Ceas (id, brand_id, nume_model, pret, stoc, tip, mecanism, rezerva_putere) VALUES ('R5', 1, 'Oyster Perpetual 36', 32000, 4, 'Mechanical', 'AUTOMAT', 70);

INSERT INTO Ceas (id, brand_id, nume_model, pret, stoc, tip, mecanism, rezerva_putere) VALUES ('C5', 5, 'Speedmaster Professional', 38000, 3, 'Mechanical', 'MANUAL', 50);
INSERT INTO Ceas (id, brand_id, nume_model, pret, stoc, tip, mecanism, rezerva_putere) VALUES ('O2', 5, 'Seamaster Diver 300M', 28000, 6, 'Mechanical', 'AUTOMAT', 55);
INSERT INTO Ceas (id, brand_id, nume_model, pret, stoc, tip, mecanism, rezerva_putere) VALUES ('O3', 5, 'Constellation Co-Axial', 42000, 2, 'Mechanical', 'AUTOMAT', 60);
INSERT INTO Ceas (id, brand_id, nume_model, pret, stoc, tip, mecanism, rezerva_putere) VALUES ('O4', 5, 'De Ville Prestige', 22000, 4, 'Mechanical', 'AUTOMAT', 48);
INSERT INTO Ceas (id, brand_id, nume_model, pret, stoc, tip, mecanism, rezerva_putere) VALUES ('O5', 5, 'Aqua Terra 150M', 31000, 5, 'Mechanical', 'AUTOMAT', 60);

INSERT INTO Ceas (id, brand_id, nume_model, pret, stoc, tip, mecanism, rezerva_putere) VALUES ('S1', 11, 'Prospex Turtle SRP777', 2500, 15, 'Mechanical', 'AUTOMAT', 41);
INSERT INTO Ceas (id, brand_id, nume_model, pret, stoc, tip, mecanism, rezerva_putere) VALUES ('S2', 11, 'Presage Cocktail Time', 2200, 10, 'Mechanical', 'AUTOMAT', 41);
INSERT INTO Ceas (id, brand_id, nume_model, pret, stoc, tip, mecanism, rezerva_putere) VALUES ('S3', 11, '5 Sports SRPD55', 1500, 25, 'Mechanical', 'AUTOMAT', 41);
INSERT INTO Ceas (id, brand_id, nume_model, pret, stoc, tip, sistem_operare, autonomie_baterie) VALUES ('S4', 11, 'Astron GPS Solar', 12000, 5, 'Smartwatch', 'SeikoOS', 168);
INSERT INTO Ceas (id, brand_id, nume_model, pret, stoc, tip, mecanism, rezerva_putere) VALUES ('S5', 11, 'Grand Seiko Heritage', 35000, 2, 'Mechanical', 'SPRING_DRIVE', 72);

INSERT INTO Ceas (id, brand_id, nume_model, pret, stoc, tip, sistem_operare, autonomie_baterie) VALUES ('CA1', 12, 'G-Shock GA-2100', 600, 50, 'Smartwatch', 'CasioOS', 17520); -- 2 years
INSERT INTO Ceas (id, brand_id, nume_model, pret, stoc, tip, sistem_operare, autonomie_baterie) VALUES ('CA2', 12, 'F-91W Classic', 100, 100, 'Smartwatch', 'CasioOS', 61320); -- 7 years
INSERT INTO Ceas (id, brand_id, nume_model, pret, stoc, tip, mecanism, rezerva_putere) VALUES ('CA3', 12, 'Edifice Chronograph', 900, 20, 'Mechanical', 'QUARTZ', 0);
INSERT INTO Ceas (id, brand_id, nume_model, pret, stoc, tip, sistem_operare, autonomie_baterie) VALUES ('CA4', 12, 'Pro Trek Solar', 1800, 10, 'Smartwatch', 'CasioOS', 4320);
INSERT INTO Ceas (id, brand_id, nume_model, pret, stoc, tip, sistem_operare, autonomie_baterie) VALUES ('CA5', 12, 'Vintage A168', 250, 40, 'Smartwatch', 'CasioOS', 61320);

INSERT INTO Ceas (id, brand_id, nume_model, pret, stoc, tip, mecanism, rezerva_putere) VALUES ('T1', 13, 'PRX Powermatic 80', 3800, 12, 'Mechanical', 'AUTOMAT', 80);
INSERT INTO Ceas (id, brand_id, nume_model, pret, stoc, tip, mecanism, rezerva_putere) VALUES ('L1', 14, 'Heritage Classic', 12000, 4, 'Mechanical', 'AUTOMAT', 72);

INSERT INTO Ceas (id, brand_id, nume_model, pret, stoc, tip, mecanism, rezerva_putere) VALUES ('C2', 2, 'Polo Date Automatic', 54600, 2, 'Mechanical', 'AUTOMAT', 48);
INSERT INTO Ceas (id, brand_id, nume_model, pret, stoc, tip, sistem_operare, autonomie_baterie) VALUES ('C3', 3, 'Watch Ultra 2 Titanium', 4500, 20, 'Smartwatch', 'watchOS', 36);
INSERT INTO Ceas (id, brand_id, nume_model, pret, stoc, tip, mecanism, rezerva_putere) VALUES ('C4', 4, 'Tangente Neomatik', 14900, 4, 'Mechanical', 'AUTOMAT', 43);
INSERT INTO Ceas (id, brand_id, nume_model, pret, stoc, tip, mecanism, rezerva_putere) VALUES ('C6', 6, 'Tank Must de Cartier', 18500, 6, 'Mechanical', 'AUTOMAT', 40);
INSERT INTO Ceas (id, brand_id, nume_model, pret, stoc, tip, sistem_operare, autonomie_baterie) VALUES ('C7', 7, 'Epix Pro (Gen 2)', 5200, 15, 'Smartwatch', 'GarminOS', 384);
INSERT INTO Ceas (id, brand_id, nume_model, pret, stoc, tip, mecanism, rezerva_putere) VALUES ('C8', 8, 'Black Bay 58', 21000, 8, 'Mechanical', 'AUTOMAT', 70);
INSERT INTO Ceas (id, brand_id, nume_model, pret, stoc, tip, sistem_operare, autonomie_baterie) VALUES ('C9', 9, 'Galaxy Watch 6 Classic', 1800, 25, 'Smartwatch', 'WearOS', 40);
INSERT INTO Ceas (id, brand_id, nume_model, pret, stoc, tip, mecanism, rezerva_putere) VALUES ('C10', 10, 'Nautilus 5711', 285000, 1, 'Mechanical', 'AUTOMAT', 45);
