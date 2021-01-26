package com.moneybook.moneybook.domain.stock;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockTradingRepository extends JpaRepository<StockTrading, Long> {
}
