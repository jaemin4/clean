package com.clean.infra.product;

import com.clean.domain.product.Product;
import com.clean.domain.product.ProductSellingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


public interface ProductJpaRepository extends JpaRepository<Product, Long> {

    List<Product> findAllBySellStatusIn(Collection<ProductSellingStatus> sellStatuses);

    List<Product> findAllByIdIn(Collection<Long> ids);
}
