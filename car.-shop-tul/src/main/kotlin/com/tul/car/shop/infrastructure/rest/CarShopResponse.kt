package com.tul.car.shop.infrastructure.rest

import com.tul.car.shop.domain.enums.CarStatus
import java.util.UUID

data class CarShopResponse(
    var id: UUID,
    var priceTotal: Int,
    var status: CarStatus,
    var products: List<ProductCarResponse>) {
}