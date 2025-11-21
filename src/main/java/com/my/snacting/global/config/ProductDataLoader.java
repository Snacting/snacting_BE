package com.my.snacting.global.config;

import com.my.snacting.domain.product.entity.Product;
import com.my.snacting.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductDataLoader implements ApplicationRunner {

    private final ProductRepository productRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (productRepository.count() > 0) {
            log.info("Product data already exists. Skipping initialization.");
            return;
        }

        log.info("Initializing product dummy data...");

        List<Product> products = List.of(
                Product.builder()
                        .productName("햄치즈 밥버거 세트")
                        .categories(List.of("도시락/간편식"))
                        .storeLocation("봉구스밥버거 삼각산점")
                        .pricePerPerson(7000)
                        .build(),

                Product.builder()
                        .productName("약매알밥 세트")
                        .categories(List.of("도시락/간편식"))
                        .storeLocation("알촌 성신여대점")
                        .pricePerPerson(8000)
                        .build(),

                Product.builder()
                        .productName("불고기 샌드위치 세트")
                        .categories(List.of("샌드위치"))
                        .storeLocation("읍천리382 정릉점")
                        .pricePerPerson(10000)
                        .build(),

                Product.builder()
                        .productName("맥스파이시 상하이 버거 세트")
                        .categories(List.of("패스트푸드"))
                        .storeLocation("맥도날드 미아DT점")
                        .pricePerPerson(7200)
                        .build(),

                Product.builder()
                        .productName("데미그라스 돈까스")
                        .categories(List.of("돈까스"))
                        .storeLocation("긴자료코 성신여대점")
                        .pricePerPerson(11000)
                        .build(),

                Product.builder()
                        .productName("베이컨치란 김밥")
                        .categories(List.of("도시락/간편식"))
                        .storeLocation("수아당 성북점")
                        .pricePerPerson(7500)
                        .build(),

                Product.builder()
                        .productName("크림치즈 베이글 커피 세트")
                        .categories(List.of("빵/디저트", "음료"))
                        .storeLocation("정릉동 커피")
                        .pricePerPerson(8000)
                        .build(),

                Product.builder()
                        .productName("빅치킨마요 라면 세트")
                        .categories(List.of("도시락/간편식"))
                        .storeLocation("한솥 도시락 삼선교점")
                        .pricePerPerson(6000)
                        .build()
        );

        productRepository.saveAll(products);
        log.info("Product dummy data initialization completed. {} products added.", products.size());
    }
}
