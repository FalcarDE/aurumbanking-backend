package de.fhe.cc.team4.aurumbanking.repositories

import de.fhe.cc.team4.aurumbanking.data.repostories.TransactionRepositoryImp
import de.fhe.cc.team4.aurumbanking.data.toEntity
import de.fhe.cc.team4.aurumbanking.util.createTransactions
import io.quarkus.test.TestReactiveTransaction
import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.vertx.UniAsserter
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
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


}