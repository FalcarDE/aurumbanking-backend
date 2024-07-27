package de.fhe.cc.team4.aurumbanking.ressources

import de.fhe.cc.team4.aurumbanking.resources.TransactionResource
import de.fhe.cc.team4.aurumbanking.util.createTransaction
import io.quarkus.test.common.http.TestHTTPEndpoint
import io.quarkus.test.junit.QuarkusTest
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test
import java.math.BigDecimal


@QuarkusTest
@TestHTTPEndpoint(TransactionResource::class)
class TransactionResourceTest {
    /**
     * Test case for creating, update password, email and retrieving a new customer.
     *
     * This test case verifies that a new product can be created by sending a POST request to the server.
     * It uses the `Given`, `When`, and `Then` DSL (Domain-Specific Language) functions to execute a series
     * of HTTP requests and assertions.
     */
    @Test
    fun `create and retrieve a new transaction`() {
        val testTransactionDataList = createTransaction()

        // 1. create three new transactions with given test data
        Given {
            contentType(ContentType.JSON)
            accept(ContentType.ANY)
            body(testTransactionDataList[0])
        } When {
            post()
        } Then {
            statusCode(201)
        }

        Given {
            contentType(ContentType.JSON)
            accept(ContentType.ANY)
            body(testTransactionDataList[1])
        } When {
            post()
        } Then {
            statusCode(201)
        }

        Given {
            contentType(ContentType.JSON)
            accept(ContentType.ANY)
            body(testTransactionDataList[1])
        } When {
            post()
        } Then {
            statusCode(201)
        }


        // 2. GET Request to retrieve the first transaction
        Given {
            contentType(ContentType.JSON)
            accept(ContentType.ANY)
        } When {
            get("getTransactionsId/1")
        } Then {
            statusCode(200)
            body("depotId", equalTo(1))
            body("country", equalTo("Germany"))
            body("recipient", equalTo("John Doe"))
            body("iban", equalTo("DE89370400440532013000"))
            body("bic", equalTo("COBADEFFXXX"))
            body("moneyValue", equalTo(BigDecimal(1000.00).toFloat()))
            body("purposeOfUse", equalTo("Payment for services"))
            body("standingOrder", equalTo(false))
            body("transactionType", equalTo("outcome"))
            body("transactionClassification", equalTo("Dauerauftrag"))

        }
    }
}



