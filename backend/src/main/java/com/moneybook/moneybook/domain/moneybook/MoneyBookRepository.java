package com.moneybook.moneybook.domain.moneybook;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.*;

public interface MoneyBookRepository extends JpaRepository<MoneyBook, Long>, MoneyBookQueryRepository {
}
