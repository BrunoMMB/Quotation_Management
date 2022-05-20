package com.idp6.quotation_management.controller;

import com.idp6.quotation_management.model.Quotation_Management_Identification;
import com.idp6.quotation_management.model.Stock;
import com.idp6.quotation_management.model.StockManagementDTO;
import com.idp6.quotation_management.repository.QuoteRepository;
import com.idp6.quotation_management.repository.StockRepository;
import com.idp6.quotation_management.services.ParsingService;
import com.idp6.quotation_management.services.StockService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import java.util.List;


@RestController
public class StockController {
    
    private static final String STOCK_MANAGEMENT = "http://localhost:8080/stock";
    
    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ParsingService parsingService;

    @Autowired
    StockService stockService = new StockService();
    
    @Autowired
    private QuoteRepository quoteRepository;

    @RequestMapping("/save")
    @GetMapping                                     //todo checar se pode ser getmapping
    public void saveStockQuotes(@RequestBody Stock clientStock){
        System.out.println("Sent client stock: "+ clientStock.toString());
        stockService.saveStockQuoteValidator(allowedStockList(), clientStock);
    }
                                                    //todo checar se os valores sao nullos
    @RequestMapping("/get")
    @GetMapping
    @Cacheable(value = "Stock_Manager_Cache")
    public String allowedStockList(){
        StockService stockService = new StockService();
        String cacheValue = stockService.stockManagerCache();
        return cacheValue;
    }

    @DeleteMapping("/stockcache")
    @CacheEvict(value = "Stock_Manager_Cache", allEntries = true)
    public void renewCache(){
        allowedStockList();
    }

    @RequestMapping("/registerNewStock")
    @PostMapping
    public void registerOnStock_Manager(@RequestBody StockManagementDTO smd){
        Quotation_Management_Identification identification = new Quotation_Management_Identification();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity("http://localhost:8080/notification", identification, String.class);

        System.out.println(smd);
        restTemplate.postForEntity(STOCK_MANAGEMENT, smd, String.class);
    }
                                                    //todo mostrar todos os quotes salvos

    @RequestMapping("/showAllStocks")
    @GetMapping
    public List<Stock> getStocks(){
        return stockRepository.findAll();
    }

    @RequestMapping("/showStock")
    @GetMapping
    public List<Stock> getStocks(@RequestBody String stockName){
        return stockRepository.findByStock_stockId(stockName);
    }
}

