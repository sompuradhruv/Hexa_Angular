CREATE TABLE user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('customer', 'vendor') NOT NULL
);

CREATE TABLE customer (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    contact VARCHAR(50),
    city VARCHAR(100),
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE vendor (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    city VARCHAR(100),
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE category (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    sequence INT
);

CREATE TABLE product (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2),
    description TEXT,
    stock_qty INT,
    vendor_id INT,
    category_id INT,
    FOREIGN KEY (vendor_id) REFERENCES vendor(id),
    FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE review (
    id INT PRIMARY KEY AUTO_INCREMENT,
    message TEXT,
    rating INT,
    customer_id INT,
    product_id INT,
    FOREIGN KEY (customer_id) REFERENCES customer(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE product_customer (
    customer_id INT,
    product_id INT,
    quantity INT,
    date_of_purchase DATE,
    PRIMARY KEY (customer_id, product_id),
    FOREIGN KEY (customer_id) REFERENCES customer(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);
