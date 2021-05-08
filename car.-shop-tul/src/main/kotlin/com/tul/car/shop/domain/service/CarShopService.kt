package com.tul.car.shop.domain.service

import com.tul.car.shop.domain.model.CarShop
import com.tul.car.shop.infrastructure.rest.CarShopRequest
import com.tul.car.shop.infrastructure.rest.CarShopResponse
import java.util.UUID

interface CarShopService {

    fun startCarShop(carShopRequest: CarShopRequest): CarShop

    fun findById(id: UUID): CarShopResponse

    fun completeCarShop(id: UUID)

    fun deleteProduct(idCarShopProduct: Long): CarShop
}