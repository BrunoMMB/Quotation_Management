package com.idp6.quotation_management.model;

//@JsonBackReference
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;

@Entity
public class Quote {
    @Id
    @GeneratedValue()
    @Type(type="uuid-char")
    private UUID id;

    @ManyToOne
    @JsonIgnore
    private Stock stockId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate quoteDate;
    
    private Double quoteValue;

    public Stock getStock() {
        return stockId;
    }

    public void setStock(Stock stock) {
        this.stockId = stock;
    }

    public LocalDate getQuoteDate() {
        return quoteDate;
    }

    public void setQuoteDate(LocalDate quoteDate) {
        this.quoteDate = quoteDate;
    }
    
    public void setQuoteDateNow() {
        this.quoteDate = LocalDate.now();
    }

    public Double getQuoteValue() {
        return quoteValue;
    }

    public void setQuoteValue(Double quoteValue) {
        this.quoteValue = quoteValue;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Stock getStockId() {
        return stockId;
    }

    public void setStockId(Stock stockId) {
        this.stockId = stockId;
    }
}
