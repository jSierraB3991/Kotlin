package com.tul.car.shop.domain.model

import com.tul.car.shop.domain.enums.CarStatus
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "car_shop")
data class CarShop(
    @Id var id: UUID = UUID.randomUUID(),
    var priceTotal: Int,
    var status: CarStatus)  {
}