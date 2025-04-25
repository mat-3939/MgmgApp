package com.example.mgmgapp.DbTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
public class OrdersTableTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @Sql({"/schema/04_orders.sql"})
    public void testOrdersTableExists() {
        
        // テーブルが存在するか確認
        String sql = "SELECT EXISTS (SELECT FROM information_schema.tables WHERE table_name = 'orders')";
        Boolean result = jdbcTemplate.queryForObject(sql, Boolean.class);
        boolean tableExists = result != null && result;
        assertTrue(tableExists, "ordersテーブルが存在しません");

        // テーブルの構造を確認
        String columnCheckSql = """
            SELECT column_name, data_type 
            FROM information_schema.columns 
            WHERE table_name = 'orders'
            """;
        
        jdbcTemplate.query(columnCheckSql, (rs, rowNum) -> {
            String columnName = rs.getString("column_name");
            String dataType = rs.getString("data_type");
            System.out.println("カラム: " + columnName + ", 型: " + dataType);
            return null;
        });
    }
}

