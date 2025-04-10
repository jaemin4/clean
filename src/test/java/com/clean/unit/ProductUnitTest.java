package com.clean.unit;

import com.clean.domain.product.Product;
import com.clean.domain.product.ProductRepository;
import com.clean.domain.product.ProductService;
import com.clean.interfaces.model.dto.req.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductUnitTest {

    private ProductService productService;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductService(productRepository);
    }

    @Test
    @DisplayName("요청 상품을 찾지 못하면 예외를 발생시킨다")
    void notFoundProduct() {
        Long productId = 3L;
        when(productRepository.findByProductId(productId)).thenReturn(Optional.empty());

        ReqDecreaseStockDto dto = new ReqDecreaseStockDto(productId, 3L);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            productService.decreaseAbleStock(dto);
        });

        assertEquals("Product not found : " + productId, ex.getMessage());
    }

    @Test
    @DisplayName("요청 상품 개수보다 재고가 부족하면 예외를 발생시킨다")
    void notEnoughStockAvailable() {
        Long productId = 1L;
        Product product = new Product("컴퓨터", 1_000_000L, 5L);
        when(productRepository.findByProductId(productId)).thenReturn(Optional.of(product));

        ReqDecreaseStockDto dto = new ReqDecreaseStockDto(productId, 6L);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            productService.decreaseAbleStock(dto);
        });

        assertEquals("재고가 부족합니다.", ex.getMessage());
    }

    @Test
    @DisplayName("결제 파라미터에 중복된 상품 ID가 존재하면 예외를 발생시킨다")
    void duplicateProductIdsExist() {
        List<ReqValidateNoDuplicateItemsDto> list = List.of(
                new ReqValidateNoDuplicateItemsDto(1L, 1L),
                new ReqValidateNoDuplicateItemsDto(1L, 2L)
        );

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            productService.validateNoDuplicateProducts(list);
        });

        assertEquals("중복된 상품 ID가 포함되어 있습니다: 1", ex.getMessage());
    }

    @Test
    @DisplayName("중복 상품 ID가 없으면 예외가 발생하지 않는다")
    void noDuplicateProductIds() {
        List<ReqValidateNoDuplicateItemsDto> list = List.of(
                new ReqValidateNoDuplicateItemsDto(1L, 1L),
                new ReqValidateNoDuplicateItemsDto(2L, 2L)
        );

        assertDoesNotThrow(() -> productService.validateNoDuplicateProducts(list));
    }

    @Test
    @DisplayName("총액 계산 단위 테스트")
    void calculateTotalAmount() {
        Product p1 = new Product("컴퓨터", 1_000_000L, 10L);
        Product p2 = new Product("마우스", 10_000L, 10L);

        when(productRepository.findByProductId(1L)).thenReturn(Optional.of(p1));
        when(productRepository.findByProductId(2L)).thenReturn(Optional.of(p2));

        List<ReqCalculateTotalAmountDto> items = List.of(
                new ReqCalculateTotalAmountDto(1L, 1L),
                new ReqCalculateTotalAmountDto(2L, 2L)
        );

        long total = productService.calculateTotalAmount(items);
        assertEquals(1_020_000L, total);
    }
}
