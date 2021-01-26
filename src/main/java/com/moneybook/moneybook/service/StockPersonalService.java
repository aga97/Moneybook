package com.moneybook.moneybook.service;

import com.moneybook.moneybook.domain.member.Member;
import com.moneybook.moneybook.domain.member.MemberRepository;
import com.moneybook.moneybook.domain.stock.StockInformation;
import com.moneybook.moneybook.domain.stock.StockInformationRepository;
import com.moneybook.moneybook.domain.stock.StockPersonal;
import com.moneybook.moneybook.domain.stock.StockPersonalRepository;
import com.moneybook.moneybook.dto.stockpersonal.StockPersonalSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StockPersonalService {

    private final StockPersonalRepository stockPersonalRepository;
    private final MemberRepository memberRepository;
    private final StockInformationRepository stockInformationRepository;

    @Transactional
    public Long save(StockPersonalSaveRequestDto stock) {

        List<Member> findMember = memberRepository.findByUsername(stock.getUsername());
        if(findMember.isEmpty()) throw new IllegalArgumentException("not exist username");
        List<StockInformation> findStock = stockInformationRepository.findByTicker(stock.getTicker());

        ////////// 주식이 없으면 api로 불러옴(미구현) 아예 없는 티커일때 에러
        if(findStock.isEmpty()) throw new IllegalArgumentException("not exist ticker");
        ///////////////

        StockPersonal stockPersonal = StockPersonal.builder()
                .member(findMember.get(0))
                .stockInformation(findStock.get(0))
                .targetQuantity(stock.getTargetQuantity())
                .currentQuantity(stock.getCurrentQuantity())
                .build();
        validateDuplicateStock(stockPersonal);
        return stockPersonalRepository.save(stockPersonal).getId();
    }

    @Transactional(readOnly = true)
    private void validateDuplicateStock(StockPersonal stockPersonal){

        List<StockPersonal> findStocks = stockPersonalRepository.findByTicker(stockPersonal.getStockInformation().getTicker());
        if(!findStocks.isEmpty()){
            throw new IllegalStateException("already exist stock");
        }
    }
}
