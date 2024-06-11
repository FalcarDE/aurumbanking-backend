package de.fhe.cc.team4.aurumbanking.domain

import de.fhe.cc.team4.aurumbanking.core.TransactionClassification
import de.fhe.cc.team4.aurumbanking.core.TransactionType
import de.fhe.cc.team4.aurumbanking.data.entities.DepotEntityModel
import de.fhe.cc.team4.aurumbanking.data.entities.TransactionEntityModel
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import java.math.BigDecimal
import java.text.SimpleDateFormat

import java.util.*

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
) {
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
