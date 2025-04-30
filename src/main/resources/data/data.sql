INSERT INTO categories (name) VALUES ('和食');
INSERT INTO categories (name) VALUES ('洋食');
INSERT INTO categories (name) VALUES ('中華');
INSERT INTO categories (name) VALUES ('その他');

INSERT INTO products (name, description, price, stock, image_path, category_id, created_at, updated_at) 
VALUES ('おかず12マス弁当～瑞～', '店主がおすすめ！是非食べて頂きたいおかずを12マスにお詰めしました。なかでもおすすめはマヨネーズインハンバーグです。冷めても美味しくてご飯が進む味わいとなっています。', 1180, 100, '/img/washoku/w_01.jpg', 1, '2025-04-28 16:10:00', '2025-04-28 16:10:00');
