package com.my.snacting.domain.product.controller;

import com.my.snacting.domain.product.dto.response.ProductGetResponse;
import com.my.snacting.domain.product.service.ProductService;
import com.my.snacting.global.auth.CurrentUserId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "Product API", description = "사장님 상품 관련 API")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "유저 예산 내 상품 조회", description = "현재 로그인한 유저의 주문서 일인 가격 내에 있는 상품만 조회합니다. 카테고리를 입력하지 않으면 모든 상품을 조회합니다.")
    public List<ProductGetResponse> getProductsByUserBudget(
            @Parameter(hidden = true) @CurrentUserId Long userId,
            @RequestParam(required = false) List<String> categories
    ) {
        return productService.getProductsByUserBudget(userId, categories);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "전체 상품 조회", description = "사장님이 올린 모든 상품을 조회합니다. 카테고리를 입력하지 않으면 모든 상품을 조회합니다.")
    public List<ProductGetResponse> getAllProducts(
            @RequestParam(required = false) List<String> categories
    ) {
        return productService.getAllProducts(categories);
    }

    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "상품 단건 조회", description = "상품 ID로 특정 상품을 조회합니다.")
    public ProductGetResponse getProductById(
            @PathVariable Long productId
    ) {
        return productService.getProductById(productId);
    }

    @PostMapping("/{productId}/like")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "상품 좋아요 토글", description = "상품에 좋아요를 추가하거나 취소합니다.")
    public void toggleProductLike(
            @Parameter(hidden = true) @CurrentUserId Long userId,
            @PathVariable Long productId
    ) {
        productService.toggleProductLike(userId, productId);
    }

    @GetMapping("/liked")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "좋아요한 상품 조회", description = "현재 로그인한 유저가 좋아요한 상품 목록을 조회합니다.")
    public List<ProductGetResponse> getLikedProducts(
            @Parameter(hidden = true) @CurrentUserId Long userId
    ) {
        return productService.getLikedProducts(userId);
    }
}