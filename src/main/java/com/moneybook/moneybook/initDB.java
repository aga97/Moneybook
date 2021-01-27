package com.moneybook.moneybook;

import com.moneybook.moneybook.domain.member.Member;
import com.moneybook.moneybook.domain.moneybook.MoneyBook;
import com.moneybook.moneybook.domain.stock.StockInformation;
import com.moneybook.moneybook.domain.stock.StockPersonal;
import com.moneybook.moneybook.domain.stock.StockTrading;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class initDB {

    private final InitService initService;

    //@PostConstruct
    public void init(){
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final EntityManager em;

        public void dbInit(){
            Member member = Member.builder()
                    .username("userA")
                    .password("password")
                    .email("test@test.com")
                    .build();
            em.persist(member);

            Member member2 = Member.builder()
                    .username("userB")
                    .password("password")
                    .email("test@test.com2")
                    .build();
            em.persist(member2);

            for(int i=0; i<100; i++) {
                em.persist(MoneyBook.builder()
                        .member(member)
                        .date(LocalDateTime.now())
                        .context("test context" + i)
                        .amount(1000L + i)
                        .tag("test tag" + i)
                        .build());
            }

            for(int i=0; i<100; i++) {
                em.persist(MoneyBook.builder()
                        .member(member2)
                        .date(LocalDateTime.now())
                        .context("test context" + i)
                        .amount(1000L + i)
                        .tag("test tag" + i)
                        .build());
            }

            StockInformation stock = StockInformation.builder()
                    .ticker("APPL")
                    .currency("USD")
                    .currentPrice(100.1)
                    .build();
            em.persist(stock);

            StockInformation stock2 = StockInformation.builder()
                    .ticker("SDGR")
                    .currency("USD")
                    .currentPrice(100.1)
                    .build();
            em.persist(stock2);

            Long stockQuantity = 0L;

            StockPersonal stockPersonal = StockPersonal.builder()
                    .member(member)
                    .stockInformation(stock)
                    .targetQuantity(100L)
                    .currentQuantity(stockQuantity)
                    .build();
            em.persist(stockPersonal);

            for(int i=0; i<100; i++) {
                em.persist(StockTrading.builder()
                        .stockPersonal(stockPersonal)
                        .price(10L + i)
                        .stockQuantity(100L + i)
                        .tradingDate(LocalDateTime.now())
                        .build());

                stockQuantity += 100L+i;
            }


            stockQuantity = 0L;


            StockPersonal stockPersonal2 = StockPersonal.builder()
                    .member(member2)
                    .stockInformation(stock)
                    .targetQuantity(100L)
                    .currentQuantity(stockQuantity)
                    .build();
            em.persist(stockPersonal2);

            for(int i=0; i<100; i++) {
                em.persist(StockTrading.builder()
                        .stockPersonal(stockPersonal2)
                        .price(10L + i)
                        .stockQuantity(100L + i)
                        .tradingDate(LocalDateTime.now())
                        .build());

                stockQuantity += 100L+i;
            }
        }
    }
}
