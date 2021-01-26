package com.moneybook.moneybook.domain.stock;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;
public interface StockTradingRepository extends JpaRepository<StockTrading, Long> {
    List<StockTrading> findByTicker(String ticker);
    List<StockTrading> findByUsername(String username);
}
