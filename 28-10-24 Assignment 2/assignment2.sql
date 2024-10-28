-- Create stored procedure for inserting a new product
DELIMITER //

CREATE PROCEDURE create_product(
    IN p_title VARCHAR(255),
    IN p_price DECIMAL(10,2),
    IN p_description TEXT,
    IN p_stock_qty INT,
    IN p_vendor_id INT,
    IN p_category_id INT
)
BEGIN
    INSERT INTO product (title, price, description, stock_qty, vendor_id, category_id)
    VALUES (p_title, p_price, p_description, p_stock_qty, p_vendor_id, p_category_id);
END //

DELIMITER ;

-- Create stored procedure for reading all products
DELIMITER //

CREATE PROCEDURE read_products()
BEGIN
    SELECT p.id, p.title, p.price, p.description, p.stock_qty, v.name AS vendor_name, c.name AS category_name
    FROM product p
    JOIN vendor v ON p.vendor_id = v.id
    JOIN category c ON p.category_id = c.id;
END //

DELIMITER ;

-- Create stored procedure for updating a product
DELIMITER //

CREATE PROCEDURE update_product(
    IN p_id INT,
    IN p_title VARCHAR(255),
    IN p_price DECIMAL(10,2),
    IN p_description TEXT,
    IN p_stock_qty INT,
    IN p_vendor_id INT,
    IN p_category_id INT
)
BEGIN
    UPDATE product 
    SET title = p_title, 
        price = p_price, 
        description = p_description, 
        stock_qty = p_stock_qty, 
        vendor_id = p_vendor_id, 
        category_id = p_category_id
    WHERE id = p_id;
END //

DELIMITER ;

-- Create stored procedure for deleting a product
DELIMITER //

CREATE PROCEDURE delete_product(IN p_id INT)
BEGIN
    DELETE FROM product WHERE id = p_id;
END //

DELIMITER ;

-- ------------------------------------------------------------------------------------------------------------------------------------

-- Create stored procedure for inserting a new vendor
DELIMITER //

CREATE PROCEDURE create_vendor(
    IN v_name VARCHAR(255),
    IN v_city VARCHAR(100),
    IN v_user_id INT
)
BEGIN
    INSERT INTO vendor (name, city, user_id)
    VALUES (v_name, v_city, v_user_id);
END //

DELIMITER ;

-- Create stored procedure for reading all vendors
DELIMITER //

CREATE PROCEDURE read_vendors()
BEGIN
    SELECT id, name, city, user_id
    FROM vendor;
END //

DELIMITER ;

-- Create stored procedure for updating a vendor
DELIMITER //

CREATE PROCEDURE update_vendor(
    IN v_id INT,
    IN v_name VARCHAR(255),
    IN v_city VARCHAR(100),
    IN v_user_id INT
)
BEGIN
    UPDATE vendor 
    SET name = v_name, 
        city = v_city, 
        user_id = v_user_id
    WHERE id = v_id;
END //

DELIMITER ;

-- Create stored procedure for deleting a vendor
DELIMITER //

CREATE PROCEDURE delete_vendor(IN v_id INT)
BEGIN
    DELETE FROM vendor WHERE id = v_id;
END //

DELIMITER ;

-- ------------------------------------------------------------------------------------------------------------------------------

-- Create stored procedure for inserting a new category
DELIMITER //

CREATE PROCEDURE create_category(
    IN c_name VARCHAR(255),
    IN c_sequence INT
)
BEGIN
    INSERT INTO category (name, sequence)
    VALUES (c_name, c_sequence);
END //

DELIMITER ;

-- Create stored procedure for reading all categories
DELIMITER //

CREATE PROCEDURE read_categories()
BEGIN
    SELECT id, name, sequence
    FROM category;
END //

DELIMITER ;

-- Create stored procedure for updating a category
DELIMITER //

CREATE PROCEDURE update_category(
    IN c_id INT,
    IN c_name VARCHAR(255),
    IN c_sequence INT
)
BEGIN
    UPDATE category 
    SET name = c_name, 
        sequence = c_sequence
    WHERE id = c_id;
END //

DELIMITER ;

-- Create stored procedure for deleting a category
DELIMITER //

CREATE PROCEDURE delete_category(IN c_id INT)
BEGIN
    DELETE FROM category WHERE id = c_id;
END //

DELIMITER ;

-- -------------------------------------------------------------------------------------------------------------------------------------

-- Inserting into users to avoid foreign key issues
INSERT INTO user (username, password, role) VALUES 
('user1', 'password1', 'customer'),
('user2', 'password2', 'vendor'),
('user3', 'password3', 'customer'),
('user4', 'password4', 'vendor'),
('user5', 'password5', 'customer');

-- Inserting 5 vendors
CALL create_vendor('Vendor One', 'City A', 1);
CALL create_vendor('Vendor Two', 'City B', 2);
CALL create_vendor('Vendor Three', 'City C', 3);
CALL create_vendor('Vendor Four', 'City D', 4);
CALL create_vendor('Vendor Five', 'City E', 5);

-- Reading all vendors to verify insertion
CALL read_vendors();

-- Inserting 5 categories
CALL create_category('Category One', 1);
CALL create_category('Category Two', 2);
CALL create_category('Category Three', 3);
CALL create_category('Category Four', 4);
CALL create_category('Category Five', 5);

-- Reading all categories to verify insertion
CALL read_categories();

select * from vendor;

-- Inserting 5 products
CALL create_product('Product One', 10.99, 'Description for product one', 100, 2, 1);
CALL create_product('Product Two', 15.99, 'Description for product two', 200, 3, 2);
CALL create_product('Product Three', 20.99, 'Description for product three', 150, 4, 3);
CALL create_product('Product Four', 25.99, 'Description for product four', 50, 5, 4);
CALL create_product('Product Five', 30.99, 'Description for product five', 75, 6, 5);

-- Reading all products to verify insertion
CALL read_products();

-- Update Vendor One's name and city
CALL update_vendor(1, 'Updated Vendor One', 'Updated City A', 1);

-- Read vendors again to verify update
CALL read_vendors();

-- Update Category One's name and sequence
CALL update_category(1, 'Updated Category One', 10);

-- Read categories again to verify update
CALL read_categories();

-- Update Product One's title and price
CALL update_product(1, 'Updated Product One', 12.99, 'Updated description for product one', 90, 1, 1);

-- Read products again to verify update
CALL read_products();

-- Delete Vendor Two
CALL delete_vendor(2);

-- Read vendors again to verify deletion
CALL read_vendors();

-- Delete Category Two
CALL delete_category(2);

-- Read categories again to verify deletion
CALL read_categories();

-- Delete Product Three
CALL delete_product(3);

-- Read products again to verify deletion
CALL read_products();
