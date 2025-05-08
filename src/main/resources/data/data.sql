INSERT INTO categories (name) VALUES ('和食');

INSERT INTO products (id, name, description, price, stock, image_path, category_id) 
VALUES (1, 'おかず12マス弁当～瑞～', '店主がおすすめ！是非食べて頂きたいおかずを12マスにお詰めしました。なかでもおすすめはマヨネーズインハンバーグです。冷めても美味しくてご飯が進む味わいとなっています。', 1180, 100, 'w_1.jpg', 1),
(2, 'test', 'test', 1280, 100, 'w_1.jpg', 1);

INSERT INTO orders (id, first_name, last_name, email, postcode, prefecture, city, address_line, building, tel, payment_method, total_price, order_date, status) VALUES (1, '大阪', '太郎', 'osaka@gmail.com', '123-4567', '大阪府', '大阪市', '大阪1-1-1', '大阪マンション101号室', '090-1234-5678', 'クレジットカード', 2360, '2025/01/01', FALSE);
INSERT INTO orders (id, first_name, last_name, email, postcode, prefecture, city, address_line, building, tel, payment_method, total_price, order_date, status) VALUES (2, '京都', '次郎', 'kyoto@gmail.com', '765-4321', '京都府', '京都市', '京都1-1-1', '京都マンション101号室', '090-8765-4321', 'クレジットカード', 1180, '2025/05/01', TRUE);
INSERT INTO orders (id, first_name, last_name, email, postcode, prefecture, city, address_line, building, tel, payment_method, total_price, order_date, status) VALUES (3, '東京', '三郎', 'tokyo@gmail.com', '876-5432', '東京府', '東京市', '東京1-1-1', '東京マンション101号室', '090-7654-3210', 'クレジットカード', 2560, '2025/04/30', FALSE);
INSERT INTO orders (id, first_name, last_name, email, postcode, prefecture, city, address_line, building, tel, payment_method, total_price, order_date, status) VALUES (4, '大阪', '花子', 'osaka@gmail.com', '123-4567', '大阪府', '大阪市', '大阪1-1-1', '大阪マンション101号室', '090-1234-5678', 'クレジットカード', 2360, '2025/05/07', FALSE);

INSERT INTO order_items (id, order_id, product_id, quantity, price) VALUES (1, 1, 1, 1, 1180);
INSERT INTO order_items (id, order_id, product_id, quantity, price) VALUES (2, 1, 2, 1, 1280);
INSERT INTO order_items (id, order_id, product_id, quantity, price) VALUES (3, 2, 1, 1, 1180);
INSERT INTO order_items (id, order_id, product_id, quantity, price) VALUES (4, 3, 2, 1, 1280);
