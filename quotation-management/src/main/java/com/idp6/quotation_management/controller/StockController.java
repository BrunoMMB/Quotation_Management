package com.idp6.quotation_management.controller;

import com.idp6.quotation_management.model.*;
import com.idp6.quotation_management.repository.QuoteRepository;
import com.idp6.quotation_management.repository.StockRepository;
import com.idp6.quotation_management.services.ParsingService;
import com.idp6.quotation_management.services.StockService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;


@RestController
public class StockController {
    
    private static final String STOCK_MANAGEMENT = "http://localhost:8080/stock";
    
    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private ParsingService parsingService;

    @Autowired
    StockService stockService = new StockService();

    @RequestMapping("/save")
    @GetMapping
    public void saveStockQuotes(@RequestBody Stock clientStock) throws Exception {
        System.out.println("Sent client stock: "+ clientStock.toString());
        stockService.saveStockQuoteValidator(allowedStockList(), clientStock);
    }

    @RequestMapping("/get")
    @GetMapping
    @Cacheable(value = "Stock_Manager_Cache")
    public String allowedStockList() throws Exception {
        StockService stockService = new StockService();
        String cacheValue = stockService.stockManagerCache();
        return cacheValue;
    }

    @DeleteMapping("/stockcache")
    @CacheEvict(value = "Stock_Manager_Cache", allEntries = true)
    public void renewCache() throws Exception {
        allowedStockList();
    }

    @RequestMapping("/registerNewStock")
    @PostMapping
    public void registerOnStock_Manager(@RequestBody StockManagementDTO smd) throws Exception{
        Quotation_Management_Identification identification = new Quotation_Management_Identification();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity("http://localhost:8080/notification", identification, String.class);

        System.out.println(smd);
        restTemplate.postForEntity(STOCK_MANAGEMENT, smd, String.class);
    }

    @RequestMapping("/showAllStocks")
    @GetMapping
    public List<Stock> getStocks() throws Exception{
        return stockRepository.findAll();
    }

    @RequestMapping("/showStock")
    @GetMapping
    public List<Object> getStocks(@RequestBody String stockName) throws Exception{
        System.out.println(stockName);
        return quoteRepository.findByStockStockId(stockName);
    }
}

