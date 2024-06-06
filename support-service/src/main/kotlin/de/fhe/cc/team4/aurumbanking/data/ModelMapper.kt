package de.fhe.cc.team4.aurumbanking.data

import de.fhe.cc.team4.aurumbanking.data.entities.SupportEntityModel import de.fhe.cc.team4.aurumbanking.domain.SupportDomainModel

fun SupportDomainModel.toEntity() =
    SupportEntityModel().apply {
        id                      = this@toEntity.id
        customerId              = this@toEntity.customerId
        dateTime                = this@toEntity.dateTime
        type                    = this@toEntity.type
        message                 = this@toEntity.message
    }


fun SupportEntityModel.toDomain() =
    SupportDomainModel().apply {
        id                      = this@toDomain.id
        customerId              = this@toDomain.customerId
        dateTime                = this@toDomain.dateTime
        type                    = this@toDomain.type
        message                 = this@toDomain.message
    }

