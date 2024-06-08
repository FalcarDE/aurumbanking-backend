package de.fhe.cc.team4.aurumbanking.data

import de.fhe.cc.team4.aurumbanking.data.entities.DepotEntityModel
import de.fhe.cc.team4.aurumbanking.data.entities.TransactionEntityModel
import de.fhe.cc.team4.aurumbanking.data.entities.TransactionType
import de.fhe.cc.team4.aurumbanking.domain.DepositDomainModel
import de.fhe.cc.team4.aurumbanking.domain.TransactionDomainModel

fun DepositDomainModel.toEntity() =
    DepotEntityModel().apply {
        id = this@toEntity.id
        transactions = this@toEntity.transactions.map { transaction ->
            transaction.toEntity(this).also {
                it.depot = this
            }
        }.toMutableList()
        currencyArea = this@toEntity.currencyArea
        depositAmount = this@toEntity.depositAmount
        fallbackDepositAmount = this@toEntity.fallbackDepositAmount
    }

fun DepotEntityModel.toDomain() =
    DepositDomainModel().apply {
        id = this@toDomain.id
        transactions = this@toDomain.transactions.map { transaction-> transaction.toDomain() }.toMutableList()
        currencyArea = this@toDomain.currencyArea
        depositAmount = this@toDomain.depositAmount
        fallbackDepositAmount = this@toDomain.fallbackDepositAmount
    }

fun TransactionDomainModel.toEntity(depot: DepotEntityModel) =
    TransactionEntityModel().apply {
        id = this@toEntity.id
        this.depot = depot
        type = TransactionType.entries.find { it.displayName == this@toEntity.type }
            ?: throw IllegalArgumentException("Unknown Transaction Type: ${this@toEntity.type}")
    }

fun TransactionEntityModel.toDomain() =
    TransactionDomainModel().apply {
        id = this@toDomain.id
        depotId = this@toDomain.depot.id
        type = this@toDomain.type.displayName
    }


fun transactionListToDomain(transactionList: List<TransactionEntityModel>): List<TransactionDomainModel> =
    transactionList.map { it.toDomain() }.toList()