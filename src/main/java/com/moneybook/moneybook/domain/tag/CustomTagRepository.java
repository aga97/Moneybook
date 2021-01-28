package com.moneybook.moneybook.domain.tag;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface CustomTagRepository extends JpaRepository<CustomTag, Long> {
    List<CustomTag> findByUsername(String Username);
}
