package com.idp6.quotation_management.repository;

import com.idp6.quotation_management.model.QuoteDTO;
import com.idp6.quotation_management.model.Stock;
import java.util.List;
import java.util.UUID;

import com.idp6.quotation_management.model.StockDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, UUID>{

}