package com.clean.interfaces.api;

import com.clean.domain.product.Product;
import com.clean.domain.product.ProductService;
import com.clean.interfaces.model.dto.res.ResFetchProductDto;
import com.clean.interfaces.model.rest.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/fetch/{id}")
    public ResponseEntity<ApiResponse<ResFetchProductDto>> fetch(@PathVariable long id) {
        Product resProduct = productService.findByProductId(id);

        ResFetchProductDto resDto = new ResFetchProductDto();
            resDto.setProductName(resProduct.getProductName());
            resDto.setProductPrice(resProduct.getProductPrice());
            resDto.setProductQuantity(resProduct.getProductQuantity());

        return ResponseEntity.ok(new ApiResponse<>("상품 조회", resDto));
    }
}
