CREATE TABLE IF NOT EXISTS products (
    id INTEGER PRIMARY KEY,
    name VARCHAR(200) NOT NULL UNIQUE,
    description TEXT,
    price NUMERIC(10, 2) NOT NULL,
    stock INTEGER NOT NULL DEFAULT 100,
    image_path TEXT NOT NULL,
    category_id INTEGER NOT NULL REFERENCES categories(id),
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT 
); 