CREATE TABLE Brand (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    country_of_origin VARCHAR(100)
);

CREATE TABLE Strap (
    id SERIAL PRIMARY KEY,
    material VARCHAR(50),
    color VARCHAR(50),
    size_mm INT
);

CREATE TABLE Watch (
    id VARCHAR(50) PRIMARY KEY,
    brand_id INT,
    model_name VARCHAR(100),
    price DOUBLE PRECISION,
    stock INT,
    strap_id INT,
    type VARCHAR(20),
    mechanism_type VARCHAR(100), -- for Mechanical
    power_reserve INT, -- for Mechanical
    operating_system VARCHAR(50), -- for Smartwatch
    battery_capacity INT, -- for Smartwatch
    image_url VARCHAR(255),
    FOREIGN KEY (brand_id) REFERENCES Brand(id),
    FOREIGN KEY (strap_id) REFERENCES Strap(id)
);

CREATE TABLE Client (
    email VARCHAR(100) PRIMARY KEY,
    name VARCHAR(100),
    phone_number VARCHAR(20)
);

CREATE TABLE Review (
    id SERIAL PRIMARY KEY,
    watch_id VARCHAR(50),
    user_name VARCHAR(100),
    text_content TEXT,
    rating INT,
    FOREIGN KEY (watch_id) REFERENCES Watch(id)
);

CREATE TABLE Orders (
    order_id VARCHAR(50) PRIMARY KEY,
    client_email VARCHAR(100),
    order_date DATE,
    total_value DOUBLE PRECISION,
    FOREIGN KEY (client_email) REFERENCES Client(email)
);

CREATE TABLE Order_Watch (
    order_id VARCHAR(50),
    watch_id VARCHAR(50),
    PRIMARY KEY (order_id, watch_id),
    FOREIGN KEY (order_id) REFERENCES Orders(order_id),
    FOREIGN KEY (watch_id) REFERENCES Watch(id)
);

-- INSERT MOCK DATA
INSERT INTO Brand (id, name, country_of_origin) VALUES (1, 'Rolex', 'Switzerland');
INSERT INTO Brand (id, name, country_of_origin) VALUES (2, 'Piaget', 'Switzerland');
INSERT INTO Brand (id, name, country_of_origin) VALUES (3, 'Apple', 'USA');
INSERT INTO Brand (id, name, country_of_origin) VALUES (4, 'NOMOS', 'Germany');
INSERT INTO Brand (id, name, country_of_origin) VALUES (5, 'Omega', 'Switzerland');
INSERT INTO Brand (id, name, country_of_origin) VALUES (6, 'Cartier', 'France');
INSERT INTO Brand (id, name, country_of_origin) VALUES (7, 'Garmin', 'USA');
INSERT INTO Brand (id, name, country_of_origin) VALUES (8, 'Tudor', 'Switzerland');
INSERT INTO Brand (id, name, country_of_origin) VALUES (9, 'Samsung', 'South Korea');
INSERT INTO Brand (id, name, country_of_origin) VALUES (10, 'Patek Philippe', 'Switzerland');

INSERT INTO Brand (id, name, country_of_origin) VALUES (11, 'Seiko', 'Japan');
INSERT INTO Brand (id, name, country_of_origin) VALUES (12, 'Casio', 'Japan');
INSERT INTO Brand (id, name, country_of_origin) VALUES (13, 'Tissot', 'Switzerland');
INSERT INTO Brand (id, name, country_of_origin) VALUES (14, 'Longines', 'Switzerland');

INSERT INTO Watch (id, brand_id, model_name, price, stock, type, mechanism_type, power_reserve, image_url) VALUES ('C1', 1, 'Datejust 41mm Dark', 45000, 5, 'Mechanical', 'AUTOMATIC', 70, 'https://images.rolex.com/2023/catalogue/images/upright-bba-with-shadow/m126334-0014.png');
INSERT INTO Watch (id, brand_id, model_name, price, stock, type, mechanism_type, power_reserve, image_url) VALUES ('R2', 1, 'Submariner Date', 65000, 3, 'Mechanical', 'AUTOMATIC', 72, 'https://images.rolex.com/2023/catalogue/images/upright-bba-with-shadow/m126610ln-0001.png');
INSERT INTO Watch (id, brand_id, model_name, price, stock, type, mechanism_type, power_reserve, image_url) VALUES ('R3', 1, 'Daytona Platinum', 380000, 1, 'Mechanical', 'AUTOMATIC', 72, 'https://images.rolex.com/2023/catalogue/images/upright-bba-with-shadow/m126506-0001.png');
INSERT INTO Watch (id, brand_id, model_name, price, stock, type, mechanism_type, power_reserve, image_url) VALUES ('R4', 1, 'GMT-Master II Pepsi', 95000, 2, 'Mechanical', 'AUTOMATIC', 70, 'https://images.rolex.com/2023/catalogue/images/upright-bba-with-shadow/m126710blro-0001.png');
INSERT INTO Watch (id, brand_id, model_name, price, stock, type, mechanism_type, power_reserve, image_url) VALUES ('R5', 1, 'Oyster Perpetual 36', 32000, 4, 'Mechanical', 'AUTOMATIC', 70, 'https://images.rolex.com/2023/catalogue/images/upright-bba-with-shadow/m126000-0001.png');

INSERT INTO Watch (id, brand_id, model_name, price, stock, type, mechanism_type, power_reserve, image_url) VALUES ('C5', 5, 'Speedmaster Professional', 38000, 3, 'Mechanical', 'MANUAL', 50, 'https://www.omegawatches.com/media/catalog/product/o/m/omega-speedmaster-moonwatch-professional-co-axial-master-chronometer-chronograph-42-mm-31030425001001-l.png');
INSERT INTO Watch (id, brand_id, model_name, price, stock, type, mechanism_type, power_reserve, image_url) VALUES ('O2', 5, 'Seamaster Diver 300M', 28000, 6, 'Mechanical', 'AUTOMATIC', 55, 'https://www.omegawatches.com/media/catalog/product/o/m/omega-seamaster-diver-300m-co-axial-master-chronometer-42-mm-21030422001001-l.png');
INSERT INTO Watch (id, brand_id, model_name, price, stock, type, mechanism_type, power_reserve, image_url) VALUES ('O3', 5, 'Constellation Co-Axial', 42000, 2, 'Mechanical', 'AUTOMATIC', 60, 'https://www.omegawatches.com/media/catalog/product/o/m/omega-constellation-co-axial-master-chronometer-39-mm-13110392001001-l.png');
INSERT INTO Watch (id, brand_id, model_name, price, stock, type, mechanism_type, power_reserve, image_url) VALUES ('O4', 5, 'De Ville Prestige', 22000, 4, 'Mechanical', 'AUTOMATIC', 48, 'https://www.omegawatches.com/media/catalog/product/o/m/omega-de-ville-prestige-co-axial-master-chronometer-41-mm-43410412001001-l.png');
INSERT INTO Watch (id, brand_id, model_name, price, stock, type, mechanism_type, power_reserve, image_url) VALUES ('O5', 5, 'Aqua Terra 150M', 31000, 5, 'Mechanical', 'AUTOMATIC', 60, 'https://www.omegawatches.com/media/catalog/product/o/m/omega-seamaster-aqua-terra-150m-co-axial-master-chronometer-41-mm-22010412101001-l.png');

INSERT INTO Watch (id, brand_id, model_name, price, stock, type, mechanism_type, power_reserve, image_url) VALUES ('S1', 11, 'Prospex Turtle SRP777', 2500, 15, 'Mechanical', 'AUTOMATIC', 41, 'https://seikousa.com/cdn/shop/products/SRP777_800x.png');
INSERT INTO Watch (id, brand_id, model_name, price, stock, type, mechanism_type, power_reserve, image_url) VALUES ('S2', 11, 'Presage Cocktail Time', 2200, 10, 'Mechanical', 'AUTOMATIC', 41, 'https://seikousa.com/cdn/shop/products/SRPB41_800x.png');
INSERT INTO Watch (id, brand_id, model_name, price, stock, type, mechanism_type, power_reserve, image_url) VALUES ('S3', 11, '5 Sports SRPD55', 1500, 25, 'Mechanical', 'AUTOMATIC', 41, 'https://seikousa.com/cdn/shop/products/SRPD55_800x.png');
INSERT INTO Watch (id, brand_id, model_name, price, stock, type, operating_system, battery_capacity, image_url) VALUES ('S4', 11, 'Astron GPS Solar', 12000, 5, 'Smartwatch', 'SeikoOS', 168, 'https://seikousa.com/cdn/shop/products/SSH003_800x.png');
INSERT INTO Watch (id, brand_id, model_name, price, stock, type, mechanism_type, power_reserve, image_url) VALUES ('S5', 11, 'Grand Seiko Heritage', 35000, 2, 'Mechanical', 'SPRING_DRIVE', 72, 'https://www.grand-seiko.com/instructions/html/images/SBGA211_G.png');

INSERT INTO Watch (id, brand_id, model_name, price, stock, type, operating_system, battery_capacity, image_url) VALUES ('CA1', 12, 'G-Shock GA-2100', 600, 50, 'Smartwatch', 'CasioOS', 17520, 'https://www.casio.com/content/dam/casio/product-info/locales/ro/ro/timepiece/product/watch/G/GA/GA2/GA-2100-1A1/assets/GA-2100-1A1.png');
INSERT INTO Watch (id, brand_id, model_name, price, stock, type, operating_system, battery_capacity, image_url) VALUES ('CA2', 12, 'F-91W Classic', 100, 100, 'Smartwatch', 'CasioOS', 61320, 'https://www.casio.com/content/dam/casio/product-info/locales/ro/ro/timepiece/product/watch/F/F9/F91/F-91W-1/assets/F-91W-1.png');
INSERT INTO Watch (id, brand_id, model_name, price, stock, type, mechanism_type, power_reserve, image_url) VALUES ('CA3', 12, 'Edifice Chronograph', 900, 20, 'Mechanical', 'QUARTZ', 0, 'https://www.casio.com/content/dam/casio/product-info/locales/ro/ro/timepiece/product/watch/E/EF/EF5/EF-527D-1AV/assets/EF-527D-1AV.png');
INSERT INTO Watch (id, brand_id, model_name, price, stock, type, operating_system, battery_capacity, image_url) VALUES ('CA4', 12, 'Pro Trek Solar', 1800, 10, 'Smartwatch', 'CasioOS', 4320, 'https://www.casio.com/content/dam/casio/product-info/locales/ro/ro/timepiece/product/watch/P/PR/PRG/PRG-270-1/assets/PRG-270-1.png');
INSERT INTO Watch (id, brand_id, model_name, price, stock, type, operating_system, battery_capacity, image_url) VALUES ('CA5', 12, 'Vintage A168', 250, 40, 'Smartwatch', 'CasioOS', 61320, 'https://www.casio.com/content/dam/casio/product-info/locales/ro/ro/timepiece/product/watch/A/A1/A16/A168WG-9/assets/A168WG-9.png');

INSERT INTO Watch (id, brand_id, model_name, price, stock, type, mechanism_type, power_reserve, image_url) VALUES ('T1', 13, 'PRX Powermatic 80', 3800, 12, 'Mechanical', 'AUTOMATIC', 80, 'https://www.tissotwatches.com/media/catalog/product/t/1/t137.407.11.041.00_1.png');
INSERT INTO Watch (id, brand_id, model_name, price, stock, type, mechanism_type, power_reserve, image_url) VALUES ('L1', 14, 'Heritage Classic', 12000, 4, 'Mechanical', 'AUTOMATIC', 72, 'https://www.longines.com/media/catalog/product/w/a/watch-the-longines-heritage-classic-l2-828-4-73-0-600x600.png');

INSERT INTO Watch (id, brand_id, model_name, price, stock, type, mechanism_type, power_reserve, image_url) VALUES ('C2', 2, 'Polo Date Automatic', 54600, 2, 'Mechanical', 'AUTOMATIC', 48, 'https://www.piaget.com/media/catalog/product/g/0/g0a41002-polo-watch-large.png');
INSERT INTO Watch (id, brand_id, model_name, price, stock, type, operating_system, battery_capacity, image_url) VALUES ('C3', 3, 'Watch Ultra 2 Titanium', 4500, 20, 'Smartwatch', 'watchOS', 36, 'https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/MTXF3_VW_34FR+watch-49-ultra-titanium-cell-ultra-ocean-orange_VW_34FR_WF_CO?wid=750&hei=750&trim=1&fmt=p-jpg&qlt=95&.v=1694553250493');
INSERT INTO Watch (id, brand_id, model_name, price, stock, type, mechanism_type, power_reserve, image_url) VALUES ('C4', 4, 'Tangente Neomatik', 14900, 4, 'Mechanical', 'AUTOMATIC', 43, 'https://nomos-glashuette.com/media/image/bc/0d/1e/tangente-neomatik-41-update-180-face-600x600.png');
INSERT INTO Watch (id, brand_id, model_name, price, stock, type, mechanism_type, power_reserve, image_url) VALUES ('C6', 6, 'Tank Must de Cartier', 18500, 6, 'Mechanical', 'AUTOMATIC', 40, 'https://www.cartier.com/dw/image/v2/BDB0_PRD/on/demandware.static/-/Sites-cartier-master/default/dw83748243/images/images-juw/WSTA0041_01.png');
INSERT INTO Watch (id, brand_id, model_name, price, stock, type, operating_system, battery_capacity, image_url) VALUES ('C7', 7, 'Epix Pro (Gen 2)', 5200, 15, 'Smartwatch', 'GarminOS', 384, 'https://static.garmincdn.com/en/products/010-02803-00/g/epix-pro-47mm-sapphire-titanium-chestnut-leather-600.png');
INSERT INTO Watch (id, brand_id, model_name, price, stock, type, mechanism_type, power_reserve, image_url) VALUES ('C8', 8, 'Black Bay 58', 21000, 8, 'Mechanical', 'AUTOMATIC', 70, 'https://www.tudorwatch.com/-/media/tudorwatch/data/collection/m79030n-0001.png');
INSERT INTO Watch (id, brand_id, model_name, price, stock, type, operating_system, battery_capacity, image_url) VALUES ('C9', 9, 'Galaxy Watch 6 Classic', 1800, 25, 'Smartwatch', 'WearOS', 40, 'https://images.samsung.com/is/image/samsung/p6pim/ro/2307/p6pim-sm-r960nzkaeue-537416346?$720_576_PNG$');
INSERT INTO Watch (id, brand_id, model_name, price, stock, type, mechanism_type, power_reserve, image_url) VALUES ('C10', 10, 'Nautilus 5711', 285000, 1, 'Mechanical', 'AUTOMATIC', 45, 'https://static.patek.com/images/articles/face/350/5711_1A_010.png');
