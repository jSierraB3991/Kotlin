package com.tul.car.shop.infrastructure.repository

import com.tul.car.shop.domain.model.CarShop
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CarShopRepository: JpaRepository<CarShop, UUID> {
}