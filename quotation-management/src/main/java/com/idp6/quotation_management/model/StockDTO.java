package com.idp6.quotation_management.model;

import java.util.List;

public class StockDTO {
    private String nome;
    private List<Quote> quotes;

    public StockDTO(Stock stock) {
        this.nome = stock.getStockId();
        this.quotes = stock.getQuotes();
    }

    public StockDTO() {
    }

    public String getNome() {
        return nome;
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }
}
