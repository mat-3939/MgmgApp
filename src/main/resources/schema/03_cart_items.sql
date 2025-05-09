CREATE TABLE IF NOT EXISTS cart_items (
    -- カートアイテムID（主キー）
    id SERIAL PRIMARY KEY,
    
    -- セッションID
--    session_id VARCHAR(255) NOT NULL UNIQUE,
    session_id VARCHAR(255),
    --↑NOT NULL UNIQUEを削除
    
    -- 商品ID（not null、多対一の関連）
    product_id INTEGER NOT NULL REFERENCES products(id),
    
    -- 商品数量（デフォルト1）
    quantity INTEGER DEFAULT 1 CHECK (quantity > 0)
);
