package com.my.snacting.domain.product.service;

import com.my.snacting.domain.order.entity.Order;
import com.my.snacting.domain.order.repository.OrderRepository;
import com.my.snacting.domain.product.dto.response.ProductGetResponse;
import com.my.snacting.domain.product.entity.Product;
import com.my.snacting.domain.product.repository.ProductRepository;
import com.my.snacting.domain.user.entity.User;
import com.my.snacting.domain.user.repository.UserRepository;
import com.my.snacting.global.exception.BusinessException;
import com.my.snacting.global.exception.errorCode.OrderErrorCode;
import com.my.snacting.global.exception.errorCode.ProductErrorCode;
import com.my.snacting.global.exception.errorCode.UserErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProductGetResponse> getAllProducts(List<String> categories) {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .filter(product -> filterByCategories(product, categories))
                .map(product -> new ProductGetResponse(
                        product.getId(),
                        product.getProductName(),
                        product.getCategories(),
                        product.getStoreLocation(),
                        product.getPricePerPerson()
                ))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductGetResponse> getProductsByUserBudget(Long userId, List<String> categories) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(UserErrorCode.USER_NOT_FOUND));

        Order order = orderRepository.findByUser(user)
                .orElseThrow(() -> new BusinessException(OrderErrorCode.ORDER_NOT_FOUND));

        int budgetPerPerson = order.getBudgetPerPerson();

        List<Product> products = productRepository.findAll();

        return products.stream()
                .filter(product -> product.getPricePerPerson() <= budgetPerPerson)
                .filter(product -> filterByCategories(product, categories))
                .map(product -> new ProductGetResponse(
                        product.getId(),
                        product.getProductName(),
                        product.getCategories(),
                        product.getStoreLocation(),
                        product.getPricePerPerson()
                ))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductGetResponse getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BusinessException(ProductErrorCode.PRODUCT_NOT_FOUND));

        return new ProductGetResponse(
                product.getId(),
                product.getProductName(),
                product.getCategories(),
                product.getStoreLocation(),
                product.getPricePerPerson()
        );
    }

    private boolean filterByCategories(Product product, List<String> categories) {
        if (categories == null || categories.isEmpty()) {
            return true;
        }
        return product.getCategories().stream()
                .anyMatch(categories::contains);
    }
}