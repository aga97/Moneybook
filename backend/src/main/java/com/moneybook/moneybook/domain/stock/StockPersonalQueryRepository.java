package com.moneybook.moneybook.domain.stock;

import java.util.*;

public interface StockPersonalQueryRepository {
    List<StockPersonal> findByTicker(String ticker);
    List<StockPersonal> findByUsername(String username);
    List<StockPersonal> findByUsernameAndTicker(String username, String ticker);
    void delById(Long id);
}
