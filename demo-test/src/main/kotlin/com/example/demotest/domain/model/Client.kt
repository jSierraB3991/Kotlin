package com.example.demotest.domain.model

import com.example.demotest.domain.enums.ClientLevel
import com.example.demotest.domain.enums.ClientLevel.BRONZE
import com.example.demotest.domain.enums.ClientType
import com.example.demotest.domain.enums.ClientType.B2B
import com.example.demotest.domain.enums.Status
import com.example.demotest.domain.enums.Status.PENDING
import com.example.demotest.domain.model.listener.ClientListener
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.envers.Audited
import org.jetbrains.annotations.NotNull
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EntityListeners
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Version

@Table(name = "clients")
@Entity
@Audited
@DynamicUpdate
@EntityListeners(
    value = [
        ClientListener::class,
        AuditingEntityListener::class
    ]
)
data class Client(

    @Id
    var uuid: UUID = UUID.randomUUID(),

    var apiId: Long? = null,

    @Version
    var version: Long? = null,

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    var createdAt: LocalDateTime? = null,

    @Column(name = "last_modified_at", updatable = false)
    @LastModifiedDate
    var lastModifiedAt: LocalDateTime? = null,

    @field:NotNull
    @Enumerated(EnumType.STRING)
    var level: ClientLevel? = BRONZE,

    var code: Long? = null,

    @field:NotNull
    var type: ClientType? = B2B,

    @field:NotNull
    @Enumerated(EnumType.STRING)
    var status: Status? = PENDING
)