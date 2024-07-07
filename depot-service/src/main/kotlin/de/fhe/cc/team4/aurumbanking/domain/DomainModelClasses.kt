package de.fhe.cc.team4.aurumbanking.domain

import java.math.BigDecimal

data class DepositDomainModel(
    var id: Long = 0,
    var customerId : Long = 0,
    var currencyArea: String,
    var depositAmount: BigDecimal = BigDecimal.ZERO,
    var fallbackDepositAmount: BigDecimal,
) {
    constructor() : this(
        id = 0,
        customerId = 0,
        currencyArea = "",
        depositAmount = BigDecimal(0.00),
        fallbackDepositAmount = BigDecimal(1500.75),
    )
}

data class IncomingTransactionDTO(val depotId: Long, val moneyValue: BigDecimal, var transactionType: String)


