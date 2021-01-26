package com.moneybook.moneybook.service;

import com.moneybook.moneybook.domain.stock.StockInformation;
import com.moneybook.moneybook.domain.stock.StockInformationRepository;
import com.moneybook.moneybook.dto.stockinformation.StockInformationSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StockInformationService {

    private final StockInformationRepository stockInformationRepository;

    @Transactional
    public Long save(StockInformationSaveRequestDto stock) {

        StockInformation stockInformation = StockInformation.builder()
                .ticker(stock.getTicker())
                .currency(stock.getCurrency())
                .currentPrice(stock.getCurrentPrice())
                .build();

        validateDuplicateStock(stockInformation);
        return stockInformationRepository.save(stockInformation).getId();
    }

    @Transactional(readOnly = true)
    private void validateDuplicateStock(StockInformation stockInformation){
        List<StockInformation> findStocks = stockInformationRepository.findByTicker(stockInformation.getTicker());
        if(!findStocks.isEmpty()){
            throw new IllegalStateException("already exist stock");
        }
    }

    @Transactional
    public void updateCurrentPrice(String ticker, Double currentPrice){
        List<StockInformation> findStock = stockInformationRepository.findByTicker(ticker);
        if(findStock.isEmpty()) {
            throw new IllegalStateException("not exist stock");
        }
        findStock.get(0).changeCurrentPrice(currentPrice);
    }

    @Transactional
    public void deleteStockInformation(String ticker) {
        List<StockInformation> findStock = stockInformationRepository.findByTicker(ticker);
        if(findStock.isEmpty()) {
            throw new IllegalStateException("not exist stock");
        }
        stockInformationRepository.delete(findStock.get(0));
    }
}
