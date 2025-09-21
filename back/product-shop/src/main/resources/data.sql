INSERT INTO users (id, username, firstname, email, password)
VALUES (1, 'admin23', 'system_user23', 'admin@admin23.com', 'admin23');

INSERT INTO users (id, username, firstname, email, password)
VALUES (2, 'sam', 'ham', 'sam@ham.com', 'sam$password2025');

-- Products
INSERT INTO product (id, code, name, description, image, category, price, quantity,
                     internal_reference, shell_id, inventory_status, rating, created_at, updated_at)
VALUES
  (1, 'P001', 'Laptop', 'High performance laptop', 'laptop.png', 'Electronics', 1200.00, 10,
   'INT-001', 1, 'INSTOCK', 5, 1672531200, 1672531200),

  (2, 'P002', 'Headphones', 'Noise cancelling headphones', 'headphones.png', 'Electronics', 250.00, 50,
   'INT-002', 2, 'LOWSTOCK', 4, 1672531200, 1672531200);