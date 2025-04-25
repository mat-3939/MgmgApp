-- 商品カテゴリ情報を管理するテーブル
CREATE TABLE IF NOT EXISTS categories (
    -- カテゴリID（主キー）
    id SERIAL PRIMARY KEY,
    
    -- カテゴリ名（not null、最大200文字、一意）
    name VARCHAR(200) NOT NULL UNIQUE
);
