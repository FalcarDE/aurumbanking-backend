package de.fhe.cc.team4.aurumbanking.model

import de.fhe.cc.team4.aurumbanking.domain.DepositDomainModel
import de.fhe.cc.team4.aurumbanking.model.entities.DepotEntityModel

fun DepositDomainModel.toEntity() =
    DepotEntityModel().apply {
        id = this@toEntity.id
        customerId = this@toEntity.customerId
        currencyArea = this@toEntity.currencyArea
        depositAmount = this@toEntity.depositAmount
        fallbackDepositAmount = this@toEntity.fallbackDepositAmount
    }

fun DepotEntityModel.toDomain() =
    DepositDomainModel().apply {
        id = this@toDomain.id
        customerId = this@toDomain.customerId
        currencyArea = this@toDomain.currencyArea
        depositAmount = this@toDomain.depositAmount
        fallbackDepositAmount = this@toDomain.fallbackDepositAmount
    }

