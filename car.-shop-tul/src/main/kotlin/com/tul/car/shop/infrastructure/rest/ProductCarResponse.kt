package com.tul.car.shop.infrastructure.rest

import java.util.UUID

data class ProductCarResponse(var id: Long,
                              var idProduct: UUID,
                              var name: String,
                              var sku: String,
                              var unitPrice: Int,
                              var totalPrice: Int,
                            var stock: Int){
}