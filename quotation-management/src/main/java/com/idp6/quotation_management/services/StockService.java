package com.idp6.quotation_management.services;

import com.google.gson.Gson;
import com.idp6.quotation_management.model.Quote;
import com.idp6.quotation_management.model.StockDTO;
import com.idp6.quotation_management.model.StockManagementDTO;
import com.idp6.quotation_management.repository.StockRepository;
import net.bytebuddy.dynamic.DynamicType;
import org.springframework.beans.factory.annotation.Autowired;
import com.idp6.quotation_management.model.Stock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Transactional
public class StockService {
    @Autowired
    public StockRepository stockRepository;

    private static final String STOCK_MANAGEMENT_IDENTIFICATOR = "http://localhost:8080/notification";

    public boolean confirmStockManagement(String allowedStocks, Stock wishedStock){
        //convertendo informações para array
        Gson gson = new Gson();
        StockManagementDTO[] allowedStocksArray = gson.fromJson(allowedStocks, StockManagementDTO[].class);

        //checando se o array possui certo valor desejado
        Stream<StockManagementDTO> stream = Arrays.stream(allowedStocksArray);
        return stream.anyMatch(StockManagementDTO -> StockManagementDTO.id.equals(wishedStock.getStockId()) == true);
    }

    public String stockManagerCache(){
        RestTemplate restTemplate = new RestTemplate();
        String jsonResult = restTemplate.getForObject("http://localhost:8080/stock", String.class);
        return jsonResult;
    }

    public void saveStockQuoteValidator(String allowedStockList, Stock clientStock){
        boolean isAllowed = confirmStockManagement(allowedStockList, clientStock);
        if(clientStock != null)
        {
            stockRepository.save(clientStock);
        }else {
            System.out.println("clientStock is null, no saved");
        }
    }
}
