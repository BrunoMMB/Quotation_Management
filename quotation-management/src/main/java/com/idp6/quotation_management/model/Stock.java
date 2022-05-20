package com.idp6.quotation_management.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.*;

@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="uuid-char")
    private UUID id;
    
    private String stockId;
    
    @OneToMany(cascade= CascadeType.ALL)                                //o cascade type manda o quote persistir no momento em que o stock persistir //a stock for many quotes
    @JoinColumn(name = "stockId")
    private List<Quote> quotes;

    public Stock() {
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public UUID getId() {
        return id;
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", stockId='" + stockId + '\'' +
                ", quotes=" + quotes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stock)) return false;
        Stock stock = (Stock) o;
        return getStockId().equals(stock.getStockId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStockId());
    }
}
