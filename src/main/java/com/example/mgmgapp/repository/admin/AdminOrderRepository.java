package com.example.mgmgapp.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.math.BigDecimal;

import com.example.mgmgapp.entity.Orders;

@Repository
public interface AdminOrderRepository extends JpaRepository<Orders, Long> {
    
    /* 姓で検索 */
    List<Orders> findByLastNameOrderByOrderDateDesc(String lastName);
    
    /* 名で検索 */
    List<Orders> findByFirstNameOrderByOrderDateDesc(String firstName);
    
    /* 姓を含む検索 */
    List<Orders> findByLastNameContainingOrderByOrderDateDesc(String lastName);
    
    /* 名を含む検索 */
    List<Orders> findByFirstNameContainingOrderByOrderDateDesc(String firstName);

    /* メールアドレスで検索 */
    Orders findByEmailOrderByOrderDateDesc(String email);

    /* 電話番号で検索 */
    Orders findByTelOrderByOrderDateDesc(String tel);

    /* 注文ステータスで検索 */
    List<Orders> findByStatus(Boolean status);
    
    /* 注文日時の範囲で検索 */
    List<Orders> findByOrderDateBetweenOrderByOrderDateDesc(LocalDateTime start, LocalDateTime end);
    
    /* 合計金額が指定値より大きい注文を検索 */
    List<Orders> findByTotalPriceGreaterThanOrderByTotalPriceDesc(BigDecimal amount);
    
    /* 注文日時でソートして検索 */
    List<Orders> findAllByOrderByOrderDateDesc();
    
    /* 特定の顧客の最新の注文を取得 */
    Orders findTopByFirstNameAndLastNameOrderByOrderDateDesc(String firstName, String lastName);
    
    
    // カスタムクエリ例（JPQL）
    // @Query("SELECT o FROM Orders o WHERE o.status = :status AND o.totalAmount > :amount")
    // List<Orders> findOrdersByStatusAndMinAmount(@Param("status") String status, @Param("amount") Double amount);
}
