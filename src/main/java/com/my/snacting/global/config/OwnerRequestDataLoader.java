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
                        .storeLocation("로드샌드위치 미아사거리역점")
                        .productName("페스츄리 로드샌드위치, 밀크티 세트")
                        .pricePerPerson(8500)
                        .build(),

                OwnerRequest.builder()
                        .order(null)
                        .storeLocation("명량핫도그 성신여대점")
                        .productName("감자통모짜핫도그 세트")
                        .pricePerPerson(6000)
                        .build(),

                OwnerRequest.builder()
                        .order(null)
                        .storeLocation("신전떡복이 성신여대점")
                        .productName("신전 떡튀순 세트")
                        .pricePerPerson(7000)
                        .build(),

                OwnerRequest.builder()
                        .order(null)
                        .storeLocation("피자스쿨 돈암점")
                        .productName("2인 피자 세트")
                        .pricePerPerson(9000)
                        .build(),

                OwnerRequest.builder()
                        .order(null)
                        .storeLocation("이삭토스트 정릉시장점")
                        .productName("햄치즈 토스트 세트")
                        .pricePerPerson(6200)
                        .build()
        );

        ownerRequestRepository.saveAll(ownerRequests);
        log.info("OwnerRequest dummy data initialization completed. {} requests added.", ownerRequests.size());
    }
}
