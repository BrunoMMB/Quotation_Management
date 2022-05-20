package com.idp6.quotation_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.idp6.quotation_management.model.Quote;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, String>{
    
}
