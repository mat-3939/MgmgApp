CREATE TABLE IF NOT EXISTS products (
    id INTEGER PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description VARCHAR(200),
    price DECIMAL(10, 2) NOT NULL,
    stock INTEGER NOT NULL,
    imge_path TEXT NOT NULL,
    category_id INTEGER NOT NULL REFERENCES categories(id),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
); 