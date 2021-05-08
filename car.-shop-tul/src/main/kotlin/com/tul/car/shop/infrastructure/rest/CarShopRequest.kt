package com.tul.car.shop.infrastructure.rest

import java.util.UUID

data class CarShopRequest(var idCar: String,
                        var idProduct: UUID,
                        var stock: Int) {
}