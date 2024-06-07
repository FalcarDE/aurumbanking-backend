package de.fhe.cc.team4.aurumbanking.data

import de.fhe.cc.team4.aurumbanking.data.entities.TransactionEntityModel
import de.fhe.cc.team4.aurumbanking.domain.TransactionDomainModel

fun TransactionDomainModel.toEntity() =
    TransactionEntityModel().apply {
        id                  = this@toEntity.id
        customerId          = this@toEntity.customerId
        dateTime            = this@toEntity.dateTime
        flag                = this@toEntity.flag
        moneyValue          = this@toEntity.moneyValue
        iban                = this@toEntity.iban
        bic                 = this@toEntity.bic
        type                = this@toEntity.type
    }

fun TransactionEntityModel.toDomain() =
    TransactionDomainModel().apply {
        id                  = this@toDomain.id
        customerId          = this@toDomain.customerId
        dateTime            = this@toDomain.dateTime
        flag                = this@toDomain.flag
        moneyValue          = this@toDomain.moneyValue
        iban                = this@toDomain.iban
        bic                 = this@toDomain.bic
        type                = this@toDomain.type
    }

fun transactionEntityListToDomain(entityList: List<TransactionEntityModel>): List<TransactionDomainModel> =
    entityList.map { it.toDomain() }.toList()