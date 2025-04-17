package com.clean.interfaces.product;

import com.clean.domain.product.ProductInfo;
import com.clean.domain.product.ProductService;
import com.clean.support.ApiResponse;
import lombok.RequiredArgsConstructor;
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
    public ApiResponse<ProductResponse.Product> fetch(@PathVariable long id) {
        ProductInfo.Product info = productService.findById(id);

        return ApiResponse.success(ProductResponse.Product.of(
                info.getProductId(), info.getProductName(), info.getProductPrice())
        );
    }

}
