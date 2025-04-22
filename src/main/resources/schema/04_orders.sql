CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,
    customer_name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    postcode VARCHAR(50) NOT NULL,
    address TEXT NOT NULL,
    tel VARCHAR(50) ,
    total_price NUMERIC(10, 2) ,
    order_date TIMESTAMP NOT NULL,
    status BOOLEAN NOT NULL DEFAULT FALSE
    );
