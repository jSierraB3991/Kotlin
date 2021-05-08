package com.tul.car.shop.domain.service.impl

import com.tul.car.shop.domain.enums.CarStatus.COMPLETE
import com.tul.car.shop.domain.enums.CarStatus.OPEN
import com.tul.car.shop.domain.model.CarShop
import com.tul.car.shop.domain.model.CarShopProduct
import com.tul.car.shop.domain.service.CarShopService
import com.tul.car.shop.infrastructure.repository.CarShopProductRepository
import com.tul.car.shop.infrastructure.repository.CarShopRepository
import com.tul.car.shop.infrastructure.repository.ProductRepository
import com.tul.car.shop.infrastructure.rest.CarShopRequest
import com.tul.car.shop.infrastructure.rest.CarShopResponse
import com.tul.car.shop.infrastructure.rest.ProductCarResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.UUID

import com.tul.car.shop.domain.model.Product
import java.lang.RuntimeException

@Component
class CarShopServiceImpl: CarShopService {

    @Autowired
    private lateinit var repository: CarShopRepository
    @Autowired
    private lateinit var repositoryProduct: CarShopProductRepository
    @Autowired
    private lateinit var productRepository: ProductRepository

    override fun startCarShop(carShopRequest: CarShopRequest ): CarShop {
        val product = productRepository.findById(carShopRequest.idProduct).orElseThrow()
        val shop = getCarShop(carShopRequest.idCar)
        if(carShopRequest.stock <= 0) {
            carShopRequest.stock = 1
        }
        val price = getPrice(product, carShopRequest.stock)
        return updateShop(product, shop, price, carShopRequest.stock)
    }

    private fun updateShop(product: Product, shop: CarShop, price: Int, stock: Int): CarShop {

        val carShopList = repositoryProduct.findByProduct(product)
        var newPrice = price
        if(carShopList.isNotEmpty()){
            val carShop = carShopList.firstOrNull { list -> list.carShop.id == shop.id }
            if(carShop!=null){
                shop.priceTotal = shop.priceTotal - carShop.price
                newPrice = updateCarShopProduct(carShop, stock, product)
            }
            else{
                repositoryProduct.save(CarShopProduct(0, product, shop, newPrice, stock))
            }
        }else{
            repositoryProduct.save(CarShopProduct(0, product, shop, newPrice, stock))
        }
        shop.priceTotal = shop.priceTotal + newPrice
        return repository.save(shop)
    }

    private fun updateCarShopProduct( carShop: CarShopProduct, stock: Int, product: Product): Int{
        carShop.stock = stock
        carShop.price = getPrice(product, stock)
        repositoryProduct.save(carShop)
        return carShop.price
    }

    private fun getCarShop(id: String): CarShop{
        var uuid: String = id
        if(uuid.isEmpty()){
            uuid = UUID.randomUUID().toString()
        }
        val shop = repository.findById(UUID.fromString(uuid))
            .orElse(repository.save(CarShop(UUID.randomUUID(), 0, OPEN)))

        if(shop.status == COMPLETE){
            throw RuntimeException("Carrito Completado")
        }
        return shop
    }

    private fun getPrice(product: Product, stock: Int): Int{
        var price = product.price * stock
        if(product.isDiscount){
            price = (product.price * stock) / 2
        }
        return price
    }

    fun getProducts(id: UUID): List<ProductCarResponse> {
        val shop = findCarShopById(id)
        return repositoryProduct.findByCarShop(shop).map { shop ->
            ProductCarResponse(
            shop.id,
            shop.product.id,
            shop.product.name,
            shop.product.sku,
            shop.product.price,
            shop.price,
            shop.stock
        ) }
    }

    override fun findById(id: UUID): CarShopResponse {
        val shop = findCarShopById(id)
        return CarShopResponse(shop.id, shop.priceTotal, shop.status, getProducts(id))
    }

    override fun completeCarShop(id: UUID) {
        val shop = findCarShopById(id)
        shop.status = COMPLETE
        repository.save(shop)
    }

    override fun deleteProduct(idCarShopProduct: Long): CarShop {
        val carShopProduct = repositoryProduct.findById(idCarShopProduct).orElseThrow()
        val shop = carShopProduct.carShop
        shop.priceTotal = shop.priceTotal - carShopProduct.price
        repositoryProduct.deleteById(idCarShopProduct)
        return repository.save(shop)
    }

    private fun findCarShopById(id: UUID): CarShop{
        return repository.findById(id).orElseThrow()
    }
}