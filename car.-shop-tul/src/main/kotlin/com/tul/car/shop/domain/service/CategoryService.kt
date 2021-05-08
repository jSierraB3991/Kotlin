package com.tul.car.shop.domain.service

import com.tul.car.shop.domain.model.Category
import java.util.UUID

interface CategoryService {
    fun findAll(): List<Category>

    fun findById(id: UUID): Category

    fun save(category: Category): Category

    fun update(category: Category, id: UUID): Category

    fun deleteById(id: UUID)
}