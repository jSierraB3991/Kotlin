package com.tul.car.shop.domain.model

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "category")
data class Category (
    @Id
    var id: UUID = UUID.randomUUID(),
    var name: String){
}