package de.fhe.cc.team4.aurumbanking.repositories

import de.fhe.cc.team4.aurumbanking.core.TransactionClassification
import de.fhe.cc.team4.aurumbanking.data.repostories.TransactionRepositoryImp
import de.fhe.cc.team4.aurumbanking.data.toDomain
import de.fhe.cc.team4.aurumbanking.data.toEntity
import de.fhe.cc.team4.aurumbanking.util.createTransactions
import de.fhe.cc.team4.aurumbanking.util.createTransactionsforGetAllTransactionByDepotId
import io.quarkus.test.TestReactiveTransaction
import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.vertx.UniAsserter
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal

@QuarkusTest
class TransactionsRepositoryTest {

    @Inject
    lateinit var transactionRepositoryImp: TransactionRepositoryImp

    @Test
    @TestReactiveTransaction
    fun testCreateAndGetTransaction(asserter: UniAsserter) {

        val testDataList = createTransactions()

        asserter.assertThat({
            this.transactionRepositoryImp.persistAndFlush(testDataList[0].toEntity())
                .replaceWith(this.transactionRepositoryImp.getTransactionById(1))
        }, { transaction ->
            transaction?.let {
                assertEquals(1, it.depotId)
                assertEquals("Germany", it.country)
                assertEquals("John Doe", it.recipient)
                assertEquals("DE89370400440532013000", it.iban)
                assertEquals("COBADEFFXXX", it.bic)
                assertEquals(BigDecimal(1000.00), it.moneyValue)
                assertEquals("Payment for services", it.purposeOfUse)
                assertEquals(false, it.standingOrder)
                assertEquals("outcome", it.transactionType)
                assertEquals("Dauerauftrag", it.transactionClassification)
            }
        })
    }


    @Test
    @TestReactiveTransaction
    fun testGetThreeLatestTransactionByDepotId(asserter: UniAsserter) {

        val testDataList = createTransactions()

        asserter.assertThat({
            this.transactionRepositoryImp.persistAndFlush(testDataList[0].toEntity())
                .replaceWith(this.transactionRepositoryImp.persistAndFlush(testDataList[1].toEntity()))
                .replaceWith(this.transactionRepositoryImp.persistAndFlush(testDataList[2].toEntity()))
                .replaceWith(this.transactionRepositoryImp.persistAndFlush(testDataList[3].toEntity()))
                .replaceWith(this.transactionRepositoryImp.getThreeLatestTransactionByDepotId(1))

        }, { transaction ->
            transaction?.let {
                assertEquals(3, it.size)

                val expectedTransactions = listOf(
                    testDataList[3], testDataList[2], testDataList[1]
                )

                expectedTransactions.forEachIndexed { index, expected ->
                    val actual = it[index]
                    assertNotNull(actual)

                    assertEquals(expected.depotId, actual.depotId)
                    assertEquals(expected.country, actual.country)
                    assertEquals(expected.recipient, actual.recipient)
                    assertEquals(expected.iban, actual.iban)
                    assertEquals(expected.bic, actual.bic)
                    assertEquals(expected.moneyValue, actual.moneyValue)
                    assertEquals(expected.purposeOfUse, actual.purposeOfUse)
                    assertEquals(expected.standingOrder, actual.standingOrder)
                    assertEquals(expected.transactionType, actual.transactionType)
                    assertEquals(expected.transactionClassification, actual.transactionClassification)
                    assertEquals(expected.dateTimeOfFirstExecution, actual.dateTimeOfFirstExecution)
                    assertEquals(expected.dateTimeOfLastExecution, actual.dateTimeOfLastExecution)
                }
            }
        })
    }



    @Test
    @TestReactiveTransaction
    fun testUpdateTransactionById(asserter: UniAsserter) {
        val initialTransaction = createTransactions().first().toEntity()

        asserter.assertThat(
            {
                this.transactionRepositoryImp.persistAndFlush(initialTransaction)
                    .replaceWith(
                        // Update the transaction
                        this.transactionRepositoryImp.updateTransactionById(
                            initialTransaction.toDomain().copy(
                                country = "Updated Country",
                                recipient = "Updated Recipient",
                                iban = "Updated Iban",
                                bic = "Updated Bic",
                                moneyValue = BigDecimal(2000.00),
                                purposeOfUse = "Updated Purpose",
                                standingOrder = true,
                                transactionType = "income",
                                transactionClassification = "Standard Überweisung"
                            )
                        )
                    )
                    .replaceWith(
                        this.transactionRepositoryImp.getTransactionById(initialTransaction.id!!)
                    )
            },
            { updatedTransaction ->
                updatedTransaction?.let {
                    assertEquals("Updated Country", it.country)
                    assertEquals("Updated Recipient", it.recipient)
                    assertEquals("Updated Iban", it.iban)
                    assertEquals("Updated Bic", it.bic)
                    assertEquals(BigDecimal(2000.00), it.moneyValue)
                    assertEquals("Updated Purpose", it.purposeOfUse)
                    assertEquals(true, it.standingOrder)
                    assertEquals("income", it.transactionType)
                    assertEquals("Standard Überweisung", it.transactionClassification)
                }
            }
        )
    }

    @Test
    @TestReactiveTransaction
    fun testGetAllTransactionByDepotId(asserter: UniAsserter) {
        val testDataList = createTransactionsforGetAllTransactionByDepotId()

        asserter.assertThat({
            this.transactionRepositoryImp.persistAndFlush(testDataList[0].toEntity())
                .flatMap { this.transactionRepositoryImp.persistAndFlush(testDataList[1].toEntity()) }
                .flatMap { this.transactionRepositoryImp.persistAndFlush(testDataList[2].toEntity()) }
                .flatMap { this.transactionRepositoryImp.persistAndFlush(testDataList[3].toEntity()) }
                .flatMap { this.transactionRepositoryImp.findAllTransactionByDepotId(2) }
        }, { transactions ->
            transactions?.let {
                assertEquals(testDataList.size, it.size)

                testDataList.forEachIndexed { index, expected ->
                    val actual = it.getOrNull(index)
                    assertNotNull(actual)

                    assertEquals(expected.depotId, actual?.depotId)
                    assertEquals(expected.country, actual?.country)
                    assertEquals(expected.recipient, actual?.recipient)
                    assertEquals(expected.iban, actual?.iban)
                    assertEquals(expected.bic, actual?.bic)
                    assertEquals(expected.moneyValue, actual?.moneyValue)
                    assertEquals(expected.purposeOfUse, actual?.purposeOfUse)
                    assertEquals(expected.standingOrder, actual?.standingOrder)
                    assertEquals(expected.transactionType, actual?.transactionType)
                    assertEquals(expected.transactionClassification, actual?.transactionClassification)
                    assertEquals(expected.dateTimeOfFirstExecution, actual?.dateTimeOfFirstExecution)
                    assertEquals(expected.dateTimeOfLastExecution, actual?.dateTimeOfLastExecution)
                }
            }
        })
    }


    @Test
    @TestReactiveTransaction
    fun testDeleteTransactionById(asserter: UniAsserter) {
        val transaction = createTransactions().first()

        asserter.assertThat(
            {
                this.transactionRepositoryImp
                    .persistAndFlush(transaction.toEntity())
                    .flatMap { this.transactionRepositoryImp.deleteTransactionById(transaction.id!!) }
                    .flatMap { this.transactionRepositoryImp.getTransactionById(transaction.id!!) }
            },
            { transactionInformation ->
                assertNull(transactionInformation, "Transaction should be null after deletion")
            }
        )
    }

}