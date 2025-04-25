CREATE TABLE IF NOT EXISTS cart_items (
    -- カートアイテムID（主キー）
    id SERIAL PRIMARY KEY,
    
    -- セッションID（not null、一意）
    session_id VARCHAR(200) NOT NULL UNIQUE,
    
    -- 商品ID（not null、一意、多対一の関連）
    product_id INTEGER NOT NULL UNIQUE REFERENCES products(id),
    
    -- 商品数量（not null、デフォルト1）
    quantity INTEGER NOT NULL DEFAULT 1 CHECK (quantity > 0)
);
