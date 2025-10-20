package com.pos.repository;

import com.pos.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT IFNULL(SUM(totalAmount), 0) as total FROM Order WHERE DATE(createdAt) = CURRENT DATE ")
    double getTotalSalesToday();

    @Query("SELECT COUNT(*) as total FROM Order WHERE DATE(createdAt) = CURRENT DATE")
    double getTotalOrdersToday();
}
