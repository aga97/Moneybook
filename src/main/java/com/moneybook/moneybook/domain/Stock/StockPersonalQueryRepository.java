package com.moneybook.moneybook.domain.stock;

import java.util.*;

public interface StockPersonalQueryRepository {
    List<StockPersonal> findByTicker(String ticker);
}
