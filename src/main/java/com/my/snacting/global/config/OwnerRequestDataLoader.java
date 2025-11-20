package com.my.snacting.global.config;

import com.my.snacting.domain.order.entity.Order;
import com.my.snacting.domain.order.repository.OrderRepository;
import com.my.snacting.domain.ownerrequest.entity.OwnerRequest;
import com.my.snacting.domain.ownerrequest.repository.OwnerRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OwnerRequestDataLoader implements ApplicationRunner {

    private final OwnerRequestRepository ownerRequestRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (ownerRequestRepository.count() > 0) {
            log.info("OwnerRequest data already exists. Skipping initialization.");
            return;
        }

        log.info("Initializing owner request dummy data...");

        // 주문서 없이 사장 제안만 먼저 생성 (MVP용)
        List<OwnerRequest> ownerRequests = List.of(
                OwnerRequest.builder()
                        .order(null)  // 주문서 없음
                        .storeLocation("국민대 정문")
                        .productName("BBQ 치킨 세트")
                        .pricePerPerson(15000)
                        .build(),

                OwnerRequest.builder()
                        .order(null)
                        .storeLocation("국민대 후문")
                        .productName("김밥천국 세트")
                        .pricePerPerson(8000)
                        .build(),

                OwnerRequest.builder()
                        .order(null)
                        .storeLocation("성신여대 입구")
                        .productName("파파존스 피자")
                        .pricePerPerson(12000)
                        .build(),

                OwnerRequest.builder()
                        .order(null)
                        .storeLocation("국민대 북악관")
                        .productName("맥도날드 세트")
                        .pricePerPerson(9000)
                        .build(),

                OwnerRequest.builder()
                        .order(null)
                        .storeLocation("국민대 중앙도서관")
                        .productName("스타벅스 음료세트")
                        .pricePerPerson(6000)
                        .build()
        );

        ownerRequestRepository.saveAll(ownerRequests);
        log.info("OwnerRequest dummy data initialization completed. {} requests added.", ownerRequests.size());
    }
}
