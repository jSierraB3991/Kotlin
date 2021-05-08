package com.tul.car.shop.infrastructure.repository

import com.tul.car.shop.domain.model.CarShop
import com.tul.car.shop.domain.model.CarShopProduct
import com.tul.car.shop.domain.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CarShopProductRepository: JpaRepository<CarShopProduct, Long> {

    fun findByCarShop(carsShop: CarShop): List<CarShopProduct>

    fun findByProduct(product: Product): List<CarShopProduct>
}