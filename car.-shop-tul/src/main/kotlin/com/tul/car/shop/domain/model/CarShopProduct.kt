package com.tul.car.shop.domain.model

import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "car_shop_product")
data class CarShopProduct(@Id @GeneratedValue var id: Long,
                          @ManyToOne var product: Product,
                          @ManyToOne var carShop: CarShop,
                          var price: Int,
                          var stock: Int) {
}