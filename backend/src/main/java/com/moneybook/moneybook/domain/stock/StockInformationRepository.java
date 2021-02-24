package com.moneybook.moneybook.domain.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface StockInformationRepository extends JpaRepository<StockInformation, Long> {
    List<StockInformation> findByTicker(String ticker);
}
