package de.fhe.cc.team4.aurumbanking.domain

import de.fhe.cc.team4.aurumbanking.data.entities.TransactionEntityModel
import java.math.BigDecimal

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
    var transactionClassification: String = ""
){
    constructor() : this(
        id = 0,
        depotId = 0,
        transactionClassification= "",
    )
}

