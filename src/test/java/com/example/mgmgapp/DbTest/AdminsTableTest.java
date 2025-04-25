package com.example.mgmgapp.DbTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
public class AdminsTableTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @Sql({"/schema/06_admins.sql"})
    public void testAdminsTableExists() {
        // テーブルが存在するか確認
        String sql = "SELECT EXISTS (SELECT FROM information_schema.tables WHERE table_name = 'admins')";
        Boolean result = jdbcTemplate.queryForObject(sql, Boolean.class);
        boolean tableExists = result != null && result;
        assertTrue(tableExists, "adminsテーブルが存在しません");

        // テーブルの構造を確認
        String columnCheckSql = """
            SELECT column_name, data_type 
            FROM information_schema.columns 
            WHERE table_name = 'admins'
            """;
        
        jdbcTemplate.query(columnCheckSql, (rs, rowNum) -> {
            String columnName = rs.getString("column_name");
            String dataType = rs.getString("data_type");
            System.out.println("カラム: " + columnName + ", 型: " + dataType);
            return null;
        });
    }
}

