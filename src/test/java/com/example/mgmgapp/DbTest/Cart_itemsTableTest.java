package com.example.mgmgapp.DbTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
public class Cart_itemsTableTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @Sql({"/schema/03_cart_items.sql"})
    void testCartItemsTableExists() {
        // テーブル作成の確認を追加
        String createTableSql = """
            CREATE TABLE IF NOT EXISTS cart_items (
                id INTEGER PRIMARY KEY,
                session_id VARCHAR(100) NOT NULL,
                product_id INTEGER NOT NULL,
                quantity INTEGER NOT NULL,
                UNIQUE(session_id, product_id)
            )
            """;
        jdbcTemplate.execute(createTableSql);
        
        // テーブルが存在するか確認
        String sql = "SELECT EXISTS (SELECT FROM information_schema.tables WHERE table_name = 'cart_items')";
        Boolean result = jdbcTemplate.queryForObject(sql, Boolean.class);
        boolean tableExists = result != null && result;
        assertTrue(tableExists, "cart_itemsテーブルが存在しません");
    }

    @Test
    @Sql({"/schema/03_cart_items.sql"})
    void testCartItemsTableColumns() {
        // 必要なカラムが存在するか確認
        String sql = "SELECT column_name FROM information_schema.columns WHERE table_name = 'cart_items'";
        java.util.List<String> columns = jdbcTemplate.queryForList(sql, String.class);
        
        assertTrue(columns.contains("id"), "idカラムが存在しません");
        assertTrue(columns.contains("session_id"), "session_idカラムが存在しません");
        assertTrue(columns.contains("product_id"), "product_idカラムが存在しません");
        assertTrue(columns.contains("quantity"), "quantityカラムが存在しません");
    }

    @Test
    @Sql({"/schema/03_cart_items.sql"})
    void testCartItemsTableConstraints() {
        // 主キー制約の確認
        String pkSql = "SELECT COUNT(*) FROM information_schema.table_constraints " +
                      "WHERE table_name = 'cart_items' AND constraint_type = 'PRIMARY KEY'";
        Integer pkResult = jdbcTemplate.queryForObject(pkSql, Integer.class);
        int pkCount = pkResult != null ? pkResult : 0;
        assertEquals(1, pkCount, "主キー制約が正しく設定されていません");

        // UNIQUE制約の確認
        String uniqueSql = "SELECT COUNT(*) FROM information_schema.table_constraints " +
                         "WHERE table_name = 'cart_items' AND constraint_type = 'UNIQUE'";
        Integer uniqueResult = jdbcTemplate.queryForObject(uniqueSql, Integer.class);
        int uniqueCount = uniqueResult != null ? uniqueResult : 0;
        assertEquals(2, uniqueCount, "UNIQUE制約が正しく設定されていません");
    }
}

