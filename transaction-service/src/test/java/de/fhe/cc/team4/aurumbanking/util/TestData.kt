
package de.fhe.cc.team4.aurumbanking.util


import de.fhe.cc.team4.aurumbanking.domain.TransactionDomainModel
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())

fun createTransactions(): List<TransactionDomainModel> {
    val firstTestTransaction = TransactionDomainModel()

    firstTestTransaction.created = LocalDateTime.now()
    firstTestTransaction.depotId = 1
    firstTestTransaction.country = "Germany"
    firstTestTransaction.recipient = "John Doe"
    firstTestTransaction.iban = "DE89370400440532013000"
    firstTestTransaction.bic = "COBADEFFXXX"
    firstTestTransaction.moneyValue = BigDecimal(1000.00)
    firstTestTransaction.purposeOfUse = "Payment for services"
    firstTestTransaction.standingOrder = false
    firstTestTransaction.transactionType = "outcome"
    firstTestTransaction.transactionClassification = "Dauerauftrag"
    firstTestTransaction.dateTimeOfFirstExecution = dateFormat.parse("2024-01-01T10:00:00")
    firstTestTransaction.dateTimeOfLastExecution = dateFormat.parse("2024-01-01T10:00:00")


    val secondTestTransaction = TransactionDomainModel()
    secondTestTransaction.created = firstTestTransaction.created.plusMinutes(1)
    secondTestTransaction.depotId = 1
    secondTestTransaction.country = "Germany"
    secondTestTransaction.recipient = "HOUSE MANAGEMENT"
    secondTestTransaction.iban = "DE89370400440532013000"
    secondTestTransaction.bic = "COBADEFFXXX"
    secondTestTransaction.moneyValue = BigDecimal(2000.00)
    secondTestTransaction.purposeOfUse = "RENT"
    secondTestTransaction.standingOrder = false
    secondTestTransaction.transactionType = "outcome"
    secondTestTransaction.transactionClassification = "Standard Überweisung"
    secondTestTransaction.dateTimeOfFirstExecution = dateFormat.parse("2024-01-02T10:00:00")
    secondTestTransaction.dateTimeOfLastExecution = dateFormat.parse("2024-01-02T10:00:00")

    val thirdTestTransaction = TransactionDomainModel()
    thirdTestTransaction.created = firstTestTransaction.created.plusMinutes(2)
    thirdTestTransaction.depotId = 1
    thirdTestTransaction.country = "Germany"
    thirdTestTransaction.recipient = "Test GmbH"
    thirdTestTransaction.iban = "DE89370400440532013000"
    thirdTestTransaction.bic = "COBADEFFXXX"
    thirdTestTransaction.moneyValue = BigDecimal(4000.00)
    thirdTestTransaction.purposeOfUse = "GEHALT"
    thirdTestTransaction.standingOrder = false
    thirdTestTransaction.transactionType = "income"
    thirdTestTransaction.transactionClassification = "Standard Überweisung"
    thirdTestTransaction.dateTimeOfFirstExecution = dateFormat.parse("2024-01-03T10:00:00")
    thirdTestTransaction.dateTimeOfLastExecution = dateFormat.parse("2024-01-03T10:00:00")

    val fourthTestTransaction = TransactionDomainModel()
    fourthTestTransaction.created = firstTestTransaction.created.plusMinutes(3)
    fourthTestTransaction.depotId = 1
    fourthTestTransaction.country = "Germany"
    fourthTestTransaction.recipient = "Payment for services"
    fourthTestTransaction.iban = "DE89370400440532013000"
    fourthTestTransaction.bic = "COBADEFFXXX"
    fourthTestTransaction.moneyValue = BigDecimal(4000.00)
    fourthTestTransaction.purposeOfUse = "GEHALT"
    fourthTestTransaction.standingOrder = false
    fourthTestTransaction.transactionType = "outcome"
    fourthTestTransaction.transactionClassification = "Standard Überweisung"
    fourthTestTransaction.dateTimeOfFirstExecution = dateFormat.parse("2024-01-04T10:00:00")
    fourthTestTransaction.dateTimeOfLastExecution = dateFormat.parse("2024-01-04T10:00:00")

    return listOf<TransactionDomainModel>(firstTestTransaction, secondTestTransaction, thirdTestTransaction, fourthTestTransaction)

}

fun createTransactionsforGetAllTransactionByDepotId(): List<TransactionDomainModel> {
    val firstTestTransaction = TransactionDomainModel()

    firstTestTransaction.created = LocalDateTime.now()
    firstTestTransaction.depotId = 2
    firstTestTransaction.country = "Germany"
    firstTestTransaction.recipient = "John Doe"
    firstTestTransaction.iban = "DE89370400440532013000"
    firstTestTransaction.bic = "COBADEFFXXX"
    firstTestTransaction.moneyValue = BigDecimal(1000.00)
    firstTestTransaction.purposeOfUse = "Payment for services"
    firstTestTransaction.standingOrder = false
    firstTestTransaction.transactionType = "outcome"
    firstTestTransaction.transactionClassification = "Dauerauftrag"
    firstTestTransaction.dateTimeOfFirstExecution = dateFormat.parse("2024-01-01T10:00:00")
    firstTestTransaction.dateTimeOfLastExecution = dateFormat.parse("2024-01-01T10:00:00")


    val secondTestTransaction = TransactionDomainModel()
    secondTestTransaction.created = firstTestTransaction.created.plusMinutes(1)
    secondTestTransaction.depotId = 2
    secondTestTransaction.country = "Germany"
    secondTestTransaction.recipient = "HOUSE MANAGEMENT"
    secondTestTransaction.iban = "DE89370400440532013000"
    secondTestTransaction.bic = "COBADEFFXXX"
    secondTestTransaction.moneyValue = BigDecimal(2000.00)
    secondTestTransaction.purposeOfUse = "RENT"
    secondTestTransaction.standingOrder = false
    secondTestTransaction.transactionType = "outcome"
    secondTestTransaction.transactionClassification = "Standard Überweisung"
    secondTestTransaction.dateTimeOfFirstExecution = dateFormat.parse("2024-01-02T10:00:00")
    secondTestTransaction.dateTimeOfLastExecution = dateFormat.parse("2024-01-02T10:00:00")

    val thirdTestTransaction = TransactionDomainModel()
    thirdTestTransaction.created = firstTestTransaction.created.plusMinutes(2)
    thirdTestTransaction.depotId = 2
    thirdTestTransaction.country = "Germany"
    thirdTestTransaction.recipient = "Test GmbH"
    thirdTestTransaction.iban = "DE89370400440532013000"
    thirdTestTransaction.bic = "COBADEFFXXX"
    thirdTestTransaction.moneyValue = BigDecimal(4000.00)
    thirdTestTransaction.purposeOfUse = "GEHALT"
    thirdTestTransaction.standingOrder = false
    thirdTestTransaction.transactionType = "income"
    thirdTestTransaction.transactionClassification = "Standard Überweisung"
    thirdTestTransaction.dateTimeOfFirstExecution = dateFormat.parse("2024-01-03T10:00:00")
    thirdTestTransaction.dateTimeOfLastExecution = dateFormat.parse("2024-01-03T10:00:00")

    val fourthTestTransaction = TransactionDomainModel()
    fourthTestTransaction.created = firstTestTransaction.created.plusMinutes(3)
    fourthTestTransaction.depotId = 2
    fourthTestTransaction.country = "Germany"
    fourthTestTransaction.recipient = "Payment for services"
    fourthTestTransaction.iban = "DE89370400440532013000"
    fourthTestTransaction.bic = "COBADEFFXXX"
    fourthTestTransaction.moneyValue = BigDecimal(4000.00)
    fourthTestTransaction.purposeOfUse = "GEHALT"
    fourthTestTransaction.standingOrder = false
    fourthTestTransaction.transactionType = "outcome"
    fourthTestTransaction.transactionClassification = "Standard Überweisung"
    fourthTestTransaction.dateTimeOfFirstExecution = dateFormat.parse("2024-01-04T10:00:00")
    fourthTestTransaction.dateTimeOfLastExecution = dateFormat.parse("2024-01-04T10:00:00")

    return listOf<TransactionDomainModel>(firstTestTransaction, secondTestTransaction, thirdTestTransaction, fourthTestTransaction)

}
