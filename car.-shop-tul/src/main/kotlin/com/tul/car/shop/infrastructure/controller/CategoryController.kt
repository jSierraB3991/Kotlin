package com.tul.car.shop.infrastructure.controller

import com.tul.car.shop.domain.model.Category
import com.tul.car.shop.domain.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import java.util.UUID

@RestController
@RequestMapping(value= ["/api/category"])
class CategoryController {

    @Autowired
    private lateinit var service: CategoryService


    @GetMapping
    fun findAll(): List<Category> {
        return service.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): Category {
        return service.findById(id)
    }

    @PostMapping
    fun save(@RequestBody product: Category): Category {
        return service.save(product)
    }

    @PutMapping("/{id}")
    fun update(@RequestBody category: Category, @PathVariable id: UUID): Category{
        return service.update(category, id)
    }

    @DeleteMapping("/{id}")
    fun deleteBydId(@PathVariable id: UUID){
        service.deleteById(id)
    }

}