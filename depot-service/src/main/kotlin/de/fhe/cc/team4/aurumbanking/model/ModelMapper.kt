package de.fhe.cc.team4.aurumbanking.model

import de.fhe.cc.team4.aurumbanking.model.entities.DepotEntityModel
import de.fhe.cc.team4.aurumbanking.model.entities.TransactionEntityModel
import de.fhe.cc.team4.aurumbanking.model.entities.core.TransactionClassification
import de.fhe.cc.team4.aurumbanking.model.entities.core.TransactionType
import de.fhe.cc.team4.aurumbanking.domain.DepositDomainModel
import de.fhe.cc.team4.aurumbanking.domain.DepotDTO
import de.fhe.cc.team4.aurumbanking.domain.TransactionDomainModel
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer

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
        country = this@toEntity.country
        recipient = this@toEntity.recipient
        iban = this@toEntity.iban
        bic = this@toEntity.bic
        moneyValue = this@toEntity.moneyValue
        purposeOfUse = this@toEntity.purposeOfUse
        dateTimeOfExecution = this@toEntity.dateTimeOfExecution
        standingOrder = this@toEntity.standingOrder
        transactionType = TransactionType.entries.find{ it.displayName == this@toEntity.transactionType }
                ?: throw IllegalArgumentException("Unknown Transaction TransactionType: ${this@toEntity.transactionType}")
        transactionClassification = TransactionClassification.entries.find { it.displayName == this@toEntity.transactionClassification }
            ?: throw IllegalArgumentException("Unknown Transaction Classification: ${this@toEntity.transactionClassification}")
        dateTimeOfFirstExecution = this@toEntity.dateTimeOfFirstExecution
        dateTimeOfLastExecution = this@toEntity.dateTimeOfLastExecution
    }

fun TransactionEntityModel.toDomain() =
    TransactionDomainModel().apply {
        id = this@toDomain.id
        depotId = this@toDomain.depot.id
        country = this@toDomain.country
        recipient = this@toDomain.recipient
        iban = this@toDomain.iban
        bic = this@toDomain.bic
        moneyValue = this@toDomain.moneyValue
        purposeOfUse = this@toDomain.purposeOfUse
        dateTimeOfExecution = this@toDomain.dateTimeOfExecution
        standingOrder = this@toDomain.standingOrder
        transactionType = this@toDomain.transactionType.displayName
        transactionClassification = this@toDomain.transactionClassification.displayName
        dateTimeOfFirstExecution = this@toDomain.dateTimeOfFirstExecution
        dateTimeOfLastExecution = this@toDomain.dateTimeOfLastExecution
    }


fun transactionListToDomain(transactionList: List<TransactionEntityModel>): List<TransactionDomainModel> =
    transactionList.map { it.toDomain() }.toList()


class ReviewAggregateDeserializer : ObjectMapperDeserializer<DepotDTO>(DepotDTO::class.java)