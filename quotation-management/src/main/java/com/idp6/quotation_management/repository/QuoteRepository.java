package com.idp6.quotation_management.repository;

import com.idp6.quotation_management.model.QuoteDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import com.idp6.quotation_management.model.Quote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, String>{
    @Query(value = "SELECT quote.quote_date, quote.quote_value FROM quote, stock WHERE stock.stock_id = :stockName", nativeQuery = true)
    List<Object> findByStockStockId(@Param("stockName") String stockName);
}
