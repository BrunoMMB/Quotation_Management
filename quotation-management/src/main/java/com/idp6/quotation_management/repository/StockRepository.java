package com.idp6.quotation_management.repository;

import com.idp6.quotation_management.model.Stock;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, UUID>{
        List<Stock> findByStock_stockId(String stockName);
}