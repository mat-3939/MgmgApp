package com.example.mgmgapp.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.mgmgapp.entity.OrderItems;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface AdminOrderItemRepository extends JpaRepository<OrderItems, Integer> {
    /*同じorder_idは1件として取得*/
    @Query("SELECT COUNT(DISTINCT oi.order.id) FROM OrderItems oi")
    int countDistinctOrderCount();
    
    /*重複していないorder_idを取得*/
    @Query("SELECT DISTINCT oi.order.id FROM OrderItems oi")
    List<Integer> findAllDistinctOrderIds();
    
    /*注文IDに基づいて注文商品を取得*/
    List<OrderItems> findByOrderId(Integer orderId);
}
