package de.fhe.cc.team4.aurumbanking.data

import de.fhe.cc.team4.aurumbanking.core.TransactionClassification
import de.fhe.cc.team4.aurumbanking.core.TransactionType
import de.fhe.cc.team4.aurumbanking.data.entities.TransactionEntityModel
import de.fhe.cc.team4.aurumbanking.domain.TransactionDomainModel


fun TransactionDomainModel.toEntity() =
    TransactionEntityModel().apply {
        id = this@toEntity.id
        depotId = this@toEntity.depotId
        country = this@toEntity.country
        recipient = this@toEntity.recipient
        iban = this@toEntity.iban
        bic = this@toEntity.bic
        moneyValue = this@toEntity.moneyValue
        purposeOfUse = this@toEntity.purposeOfUse
        standingOrder = this@toEntity.standingOrder
        transactionType = TransactionType.entries.find { it.displayName == this@toEntity.transactionType }
            ?: throw IllegalArgumentException("Unknown Transaction TransactionType: ${this@toEntity.transactionType}")
        transactionClassification =
            TransactionClassification.entries.find { it.displayName == this@toEntity.transactionClassification }
                ?: throw IllegalArgumentException("Unknown Transaction Classification: ${this@toEntity.transactionClassification}")
        dateTimeOfFirstExecution = this@toEntity.dateTimeOfFirstExecution
        dateTimeOfLastExecution = this@toEntity.dateTimeOfLastExecution
    }

fun TransactionEntityModel.toDomain() =
    TransactionDomainModel().apply {
        id = this@toDomain.id
        depotId = this@toDomain.depotId
        //created = this@toDomain.created
        country = this@toDomain.country
        recipient = this@toDomain.recipient
        iban = this@toDomain.iban
        bic = this@toDomain.bic
        moneyValue = this@toDomain.moneyValue
        purposeOfUse = this@toDomain.purposeOfUse
        standingOrder = this@toDomain.standingOrder
        transactionType = this@toDomain.transactionType.displayName
        transactionClassification = this@toDomain.transactionClassification.displayName
        dateTimeOfFirstExecution = this@toDomain.dateTimeOfFirstExecution
        dateTimeOfLastExecution = this@toDomain.dateTimeOfLastExecution
    }


fun transactionListToDomain(transactionList: List<TransactionEntityModel>): List<TransactionDomainModel> =
    transactionList.map { it.toDomain() }.toList()