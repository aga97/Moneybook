package com.moneybook.moneybook.domain.stock;

import java.time.LocalDateTime;
import java.util.*;

public interface StockTradingQueryRepository {
    List<StockTrading> findByUsernameAndDate(String username, Integer year, Integer month);

    List<StockTrading> findByUsernameAndTicker(String username, String ticker);
}
