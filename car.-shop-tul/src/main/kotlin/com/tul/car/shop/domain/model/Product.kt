package com.tul.car.shop.domain.model

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
@Table(name = "product")
data class Product(
    @Id var id: UUID = UUID.randomUUID(),
    var name: String,
    var sku: String,
    var price: Int,
    var isDiscount: Boolean,
    @ManyToOne var category: Category) {
}