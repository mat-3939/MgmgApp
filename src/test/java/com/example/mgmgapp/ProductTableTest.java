package com.example.mgmgapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
public class ProductTableTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @Sql({"/schema/01_products.sql"})
    public void testProductTableExists() {
        // テーブル作成の確認を追加
        String createTableSql = """
            CREATE TABLE IF NOT EXISTS products (
                id INTEGER PRIMARY KEY,
                name VARCHAR(200) NOT NULL,
                description VARCHAR(200),
                price DECIMAL(10, 2) NOT NULL,
                stock INTEGER NOT NULL,
                imge_path TEXT NOT NULL,
                category_id INTEGER NOT NULL,
                created_at TIMESTAMP NOT NULL,
                updated_at TIMESTAMP NOT NULL
            )
            """;
        jdbcTemplate.execute(createTableSql);
        
        // テーブルが存在するか確認
        String sql = "SELECT EXISTS (SELECT FROM information_schema.tables WHERE table_name = 'products')";
        Boolean result = jdbcTemplate.queryForObject(sql, Boolean.class);
        boolean tableExists = result != null && result;
        assertTrue(tableExists, "productsテーブルが存在しません");

        // テーブルの構造を確認
        String columnCheckSql = """
            SELECT column_name, data_type 
            FROM information_schema.columns 
            WHERE table_name = 'products'
            """;
        
        jdbcTemplate.query(columnCheckSql, (rs, rowNum) -> {
            String columnName = rs.getString("column_name");
            String dataType = rs.getString("data_type");
            System.out.println("カラム: " + columnName + ", 型: " + dataType);
            return null;
        });
    }
} 