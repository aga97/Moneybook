package com.moneybook.moneybook.service;

import com.moneybook.moneybook.domain.member.Member;
import com.moneybook.moneybook.domain.member.MemberRepository;
import com.moneybook.moneybook.domain.stock.StockInformation;
import com.moneybook.moneybook.domain.stock.StockInformationRepository;
import com.moneybook.moneybook.domain.stock.StockPersonal;
import com.moneybook.moneybook.domain.stock.StockPersonalRepository;
import com.moneybook.moneybook.dto.stockpersonal.StockPersonalReadRequestDto;
import com.moneybook.moneybook.dto.stockpersonal.StockPersonalReadResponseDto;
import com.moneybook.moneybook.dto.stockpersonal.StockPersonalSaveRequestDto;
import com.moneybook.moneybook.dto.stockpersonal.StockPersonalUpdateRequestDto;
import com.moneybook.moneybook.exceptions.DuplicatedStockException;
import com.moneybook.moneybook.exceptions.InvalidIdException;
import com.moneybook.moneybook.exceptions.InvalidTickerException;
import com.moneybook.moneybook.exceptions.InvalidUsernameException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        if(findMember.isEmpty()) throw new InvalidUsernameException("not exist username. check username=" + stock.getUsername());
        List<StockInformation> findStock = stockInformationRepository.findByTicker(stock.getTicker());

        ////////// 주식이 없으면 api로 불러옴(미구현) 아예 없는 티커일때 에러
        if(findStock.isEmpty()) throw new InvalidTickerException("not exist ticker");
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

        List<StockPersonal> findStocks = stockPersonalRepository.findByUsernameAndTicker(
                stockPersonal.getMember().getUsername(),
                stockPersonal.getStockInformation().getTicker()
        );
        if(!findStocks.isEmpty()){
            throw new DuplicatedStockException("already exist stock. check ticker="+ stockPersonal.getStockInformation().getTicker());
        }
    }

    @Transactional(readOnly = true)
    public List<StockPersonalReadResponseDto> findByUsername(StockPersonalReadRequestDto requestDto){
        List<StockPersonal> findStockPersonal = stockPersonalRepository.findByUsername(requestDto.getUsername());
        List<StockPersonalReadResponseDto> responseDtos = new ArrayList<>();

        for (StockPersonal stockPersonal : findStockPersonal) {
            responseDtos.add(new StockPersonalReadResponseDto(stockPersonal));
        }

        return responseDtos;
    }

    @Transactional
    public Long updateAll(StockPersonalUpdateRequestDto requestDto) {
        StockPersonal stockPersonal = stockPersonalRepository.findById(requestDto.getId())
                .orElseThrow(() -> new InvalidIdException("not exist stock_personal. check id=" + requestDto.getId()));

        stockPersonal.changeTargetQuantity(requestDto.getTargetQuantity());
        stockPersonal.tradeCurrentQuantity(requestDto.getCurrentQuantity());
        return stockPersonal.getId();
    }

    @Transactional
    public void deleteStockPersonal(Long id){
        stockPersonalRepository.deleteById(id);
    }

}
