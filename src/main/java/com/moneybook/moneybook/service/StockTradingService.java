package com.moneybook.moneybook.service;

import com.moneybook.moneybook.domain.member.Member;
import com.moneybook.moneybook.domain.member.MemberRepository;
import com.moneybook.moneybook.domain.stock.StockInformation;
import com.moneybook.moneybook.domain.stock.StockInformationRepository;
import com.moneybook.moneybook.domain.stock.StockTrading;
import com.moneybook.moneybook.domain.stock.StockTradingRepository;
import com.moneybook.moneybook.dto.stocktrading.StockTradingSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@Service
public class StockTradingService {

    private final StockTradingRepository stockTradingRepository;
    private final MemberRepository memberRepository;
    private final StockInformationRepository stockInformationRepository;

    @Transactional
    public Long save(StockTradingSaveRequestDto stock) {

        List<Member> findMember = memberRepository.findByUsername(stock.getUsername());
        if(findMember.isEmpty()) throw new IllegalArgumentException("not exist username");
        List<StockInformation> findStock = stockInformationRepository.findByTicker(stock.getTicker());

        ////////// 주식이 없으면 api로 불러옴(미구현) 아예 없는 티커일때 에러
        if(findStock.isEmpty()) throw new IllegalArgumentException("not exist ticker");
        ///////////////
        StockTrading stockTrading = StockTrading.builder()
                .member(findMember.get(0))
                .stockInformation(findStock.get(0))
                .price(stock.getPrice())
                .stockQuantity(stock.getStockQuantity())
                .tradingDate(LocalDateTime.of(stock.getYear(), stock.getMonth(), stock.getDay(), 0, 0))
                .build();


        return stockTradingRepository.save(stockTrading).getId();
    }
}
