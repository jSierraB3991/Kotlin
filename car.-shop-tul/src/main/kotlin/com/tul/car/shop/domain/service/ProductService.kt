package com.tul.car.shop.domain.service

import com.tul.car.shop.domain.model.Product
import java.util.UUID

interface ProductService {
    fun findAll():  List<Product>

    fun save(product: Product): Product

    fun update(product: Product, id: UUID): Product

    fun deleteById(id: UUID)

    fun findById(id: UUID): Product
}