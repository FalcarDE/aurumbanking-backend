package de.fhe.cc.team4.aurumbanking.domain

import java.math.BigDecimal
import java.util.*

data class DepositDomainModel(
    var id: Long = 0,
    var transactions: MutableList<TransactionDomainModel> = mutableListOf(),
    var currencyArea: String,
    var depositAmount: BigDecimal = BigDecimal.ZERO,
    var fallbackDepositAmount: BigDecimal,
) {
    constructor() : this(
        id = 0,
        transactions = mutableListOf() ,
        currencyArea = "",
        depositAmount = BigDecimal(0.00),
        fallbackDepositAmount = BigDecimal(1500.75),
    )
}


data class TransactionDomainModel(
    var id: Long = 0,
    var depotId: Long = 0,
    var country: String = "",
    var recipient: String = "",
    var iban: String = "",
    var bic: String = "",
    var moneyValue: BigDecimal = BigDecimal.ZERO,
    var purposeOfUse: String = "",
    var dateTimeOfExecution: Date,
    var standingOrder: Boolean? ,
    var transactionType: String,
    var transactionClassification: String,
    var dateTimeOfFirstExecution: Date,
    var dateTimeOfLastExecution: Date
){
    constructor() : this(
        id = 0,
        depotId = 0,
        country = "",
        recipient = "",
        iban = "",
        bic = "",
        moneyValue = BigDecimal.ZERO,
        purposeOfUse = "",
        dateTimeOfExecution = Date(),
        standingOrder = false,
        transactionType = "",
        transactionClassification = "",
        dateTimeOfFirstExecution = Date(),
        dateTimeOfLastExecution = Date()
    )
}


data class DepotDTO(val depositAmount: BigDecimal )


