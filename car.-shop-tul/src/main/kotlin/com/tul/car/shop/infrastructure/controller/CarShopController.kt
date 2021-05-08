package com.tul.car.shop.infrastructure.controller

import com.tul.car.shop.domain.model.CarShop
import com.tul.car.shop.domain.service.CarShopService
import com.tul.car.shop.infrastructure.rest.CarShopRequest
import com.tul.car.shop.infrastructure.rest.CarShopResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.DeleteMapping
import java.util.UUID

@RestController
@RequestMapping(value= ["/api/car-shop"])
class CarShopController {

    @Autowired
    private lateinit var service: CarShopService


    @PostMapping("/add-product")
    fun startCarShop(@RequestBody carShopRequest: CarShopRequest): CarShop{
        return service.startCarShop(carShopRequest)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): CarShopResponse{
        return service.findById(id)
    }

    @PutMapping("/{id}")
    fun completeCarShop(@PathVariable id: UUID){
        service.completeCarShop(id)
    }

    @DeleteMapping("/product/{id}")
    fun deleteProductInCar(@PathVariable id: Long): CarShop {
        return service.deleteProduct(id)
    }

}