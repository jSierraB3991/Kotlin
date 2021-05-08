package com.tul.car.shop.domain.service.impl

import com.tul.car.shop.domain.model.Product
import com.tul.car.shop.domain.service.ProductService
import com.tul.car.shop.infrastructure.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class ProductServiceImpl: ProductService {
    @Autowired
    private lateinit var repository: ProductRepository

    override fun findAll(): List<Product> {
        return repository.findAll()
    }

    override fun save(product: Product): Product {
        return repository.save(product)
    }

    override fun update(product: Product, id: UUID): Product {
        findById(id)
        return save(product)
    }

    override fun deleteById(id: UUID) {
        findById(id)
        repository.deleteById(id)
    }

    override fun findById(id: UUID): Product {
        return repository.findById(id).orElseThrow()
    }
}