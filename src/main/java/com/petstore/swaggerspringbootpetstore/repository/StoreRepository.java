package com.petstore.swaggerspringbootpetstore.repository;

import com.petstore.swaggerspringbootpetstore.enity.store.StoreOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreOrder, Long> {
}
