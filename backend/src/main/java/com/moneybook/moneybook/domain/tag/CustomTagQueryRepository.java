package com.moneybook.moneybook.domain.tag;

import java.util.List;

public interface CustomTagQueryRepository {
    List<CustomTag> findByUsername(String username);
}
