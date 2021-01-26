package com.moneybook.moneybook.service;

import com.moneybook.moneybook.domain.stock.StockPersonal;
import com.moneybook.moneybook.domain.stock.StockPersonalRepository;
import com.moneybook.moneybook.dto.stockpersonal.StockPersonalSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StockPersonalService {

    private final StockPersonalRepository stockPersonalRepository;

    @Transactional
    public Long save(StockPersonalSaveRequestDto stock){
        StockPersonal stockPersonal = stock.toEntity();
        validateDuplicateStock(stockPersonal);
        return stockPersonalRepository.save(stockPersonal).getId();
    }

    @Transactional(readOnly = true)
    private void validateDuplicateStock(StockPersonal stockPersonal){
        List<StockPersonal> findStocks = stockPersonalRepository.findByTicker(stockPersonal.getTicker());
        if(!findStocks.isEmpty()){
            throw new IllegalStateException("already exist stock");
        }
    }
}
