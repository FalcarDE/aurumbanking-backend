package de.fhe.cc.team4.aurumbanking.domain

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class TransactionDomainModel(
    var id: Long = 0,
    var depotId: Long = 0,
    var created: LocalDateTime,
    var country: String = "",
    var recipient: String = "",
    var iban: String = "",
    var bic: String = "",
    var moneyValue: BigDecimal = BigDecimal.ZERO,
    var purposeOfUse: String = "",
    var standingOrder: Boolean?,
    var transactionType: String,
    var transactionClassification: String,
    var dateTimeOfFirstExecution: Date,
    var dateTimeOfLastExecution: Date
) {
    constructor() : this(
        id = 0,
        depotId = 0,
        created = LocalDateTime.now(),
        country = "",
        recipient = "",
        iban = "",
        bic = "",
        moneyValue = BigDecimal.ZERO,
        purposeOfUse = "",
        standingOrder = false,
        transactionType = "",
        transactionClassification = "",
        dateTimeOfFirstExecution = Date(),
        dateTimeOfLastExecution = Date()
    )
}


