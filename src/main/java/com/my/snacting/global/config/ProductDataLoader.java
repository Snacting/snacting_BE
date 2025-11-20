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
                        .productName("BBQ 치킨 세트")
                        .categories(List.of("치킨"))
                        .storeLocation("국민대 정문")
                        .pricePerPerson(15000)
                        .build(),

                Product.builder()
                        .productName("김밥천국 세트")
                        .categories(List.of("한식", "분식"))
                        .storeLocation("국민대 후문")
                        .pricePerPerson(8000)
                        .build(),

                Product.builder()
                        .productName("파파존스 피자")
                        .categories(List.of("양식", "피자"))
                        .storeLocation("성신여대 입구")
                        .pricePerPerson(12000)
                        .build(),

                Product.builder()
                        .productName("맥도날드 세트")
                        .categories(List.of("양식", "패스트푸드"))
                        .storeLocation("국민대 북악관")
                        .pricePerPerson(9000)
                        .build(),

                Product.builder()
                        .productName("스타벅스 음료세트")
                        .categories(List.of("카페", "음료"))
                        .storeLocation("국민대 중앙도서관")
                        .pricePerPerson(6000)
                        .build()
        );

        productRepository.saveAll(products);
        log.info("Product dummy data initialization completed. {} products added.", products.size());
    }
}
