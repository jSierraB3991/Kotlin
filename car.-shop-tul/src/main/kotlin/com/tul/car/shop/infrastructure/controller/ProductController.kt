package com.tul.car.shop.infrastructure.controller

import com.tul.car.shop.domain.model.Product
import com.tul.car.shop.domain.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.DeleteMapping
import java.util.UUID

@RestController
@RequestMapping(value= ["/api/product"])
class ProductController {

    @Autowired
    private lateinit var service: ProductService

    @GetMapping
    fun findAll(): List<Product> {
        return service.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): Product {
        return service.findById(id)
    }

    @PostMapping
    fun save(@RequestBody product: Product): Product {
        return service.save(product)
    }

    @PutMapping("/{id}")
    fun update(@RequestBody product: Product, @PathVariable id: UUID): Product {
        return service.update(product, id)
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: UUID){
        service.deleteById(id)
    }

}