INSERT INTO users (id, username, firstname, email, password)
VALUES (1, 'admin', 'System', 'admin@admin.com', '{bcrypt}$2a$10$DowJonesStrongHashHere');

INSERT INTO users (id, username, firstname, email, password)
VALUES (2, 'john', 'John', 'john@example.com', '{bcrypt}$2a$10$AnotherStrongHashHere');

-- Products
INSERT INTO product (id, code, name, description, image, category, price, quantity,
                     internal_reference, shell_id, inventory_status, rating, created_at, updated_at)
VALUES
  (1, 'P001', 'Laptop', 'High performance laptop', 'laptop.png', 'Electronics', 1200.00, 10,
   'INT-001', 1, 'INSTOCK', 5, 1672531200, 1672531200),

  (2, 'P002', 'Headphones', 'Noise cancelling headphones', 'headphones.png', 'Electronics', 250.00, 50,
   'INT-002', 2, 'LOWSTOCK', 4, 1672531200, 1672531200);