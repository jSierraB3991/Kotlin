package com.tul.car.shop.domain.service.impl

import com.tul.car.shop.domain.model.Category
import com.tul.car.shop.domain.service.CategoryService
import com.tul.car.shop.infrastructure.repository.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class CategoryServiceImpl: CategoryService {

    @Autowired
    private lateinit var repository: CategoryRepository

    override fun findAll(): List<Category> {
        return repository.findAll()
    }

    override fun findById(id: UUID): Category {
        return repository.findById(id).orElseThrow()
    }

    override fun save(category: Category): Category {
        return repository.save(category)
    }

    override fun update(category: Category, id: UUID): Category {
        findById(id)
        category.id = id
        return save(category)
    }

    override fun deleteById(id: UUID) {
        findById(id)
        repository.deleteById(id)
    }
}