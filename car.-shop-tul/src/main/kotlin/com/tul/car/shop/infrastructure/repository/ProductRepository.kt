package com.tul.car.shop.infrastructure.repository

import com.tul.car.shop.domain.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ProductRepository: JpaRepository<Product, UUID> {
}