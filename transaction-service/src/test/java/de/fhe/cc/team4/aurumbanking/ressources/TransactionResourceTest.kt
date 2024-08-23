

package de.fhe.cc.team4.aurumbanking.ressources

import de.fhe.cc.team4.aurumbanking.domain.TransactionDomainModel
import de.fhe.cc.team4.aurumbanking.resources.TransactionResource
import de.fhe.cc.team4.aurumbanking.util.createTransactions
import io.quarkus.test.common.http.TestHTTPEndpoint
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import io.restassured.parsing.Parser
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal


@QuarkusTest
@TestHTTPEndpoint(TransactionResource::class)
class TransactionResourceTest {

    @BeforeEach
    fun setup() {
        // set den standard parser to JSON
        RestAssured.defaultParser = Parser.JSON
    }

    /**
     * Test case for creating and retrieving a new transaction.
     *
     * This test case verifies that a new transaction can be created by sending a POST request to the server.
     * It uses the `Given`, `When`, and `Then` DSL (Domain-Specific Language) functions to execute a series
     * of HTTP requests and assertions.
     */
    @Test
    fun `create and retrieve a new transaction`() {
        val testTransactionDataList = createTransactions()

        // 1. create three new transactions with given test data
        val response = Given {
            contentType(ContentType.JSON)
            accept(ContentType.JSON)
            body(testTransactionDataList[0])
        } When {
            post()
        } Then {
            statusCode(201)
        } Extract {
            response()
        }


        // extract Location-Header
        val locationHeader = response.getHeader("Location")
        val id = locationHeader.split("/").last()

        // 2. GET Request to retrieve the first transaction
        Given {
            contentType(ContentType.JSON)
            accept(ContentType.JSON)
        } When {
            get("getTransactionById/$id")
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

    /**
     * Test case for creating and retrieving three latest transaction .
     *
     * This test case verifies that  multiple transactions can be created by sending a POST request to the server
     * and returns the three latest transaction as a list via the endpoint getThreeLatestTransactionByDepotId/1.
     * It uses the `Given`, `When`, and `Then` DSL (Domain-Specific Language) functions to execute a series
     * of HTTP requests and assertions.
     */
    @Test
    fun `create and retrieve three latest transactions`() {
        val testTransactionDataList = createTransactions()

        // create multiple new transactions with given test data
        testTransactionDataList.forEach { data ->
            Given {
                contentType(ContentType.JSON)
                accept(ContentType.JSON)
                body(data)
            } When {
                post()
            } Then {
                statusCode(201)
            } Extract {
                response()
            }
        }

        // Iterate through each test transaction and perform a GET request
        testTransactionDataList.forEachIndexed { _, transaction ->
            val depotId = transaction.depotId
            Given {
                contentType(ContentType.JSON)
                accept(ContentType.JSON)
            } When {
                get("getThreeLatestTransactionByDepotId/$depotId")
            } Then {
                statusCode(200)

                // Get the list of transactions from the response
                val responseTransactions = extract().jsonPath().getList(".", TransactionDomainModel::class.java)

                // Iterate through the response list and compare with the expected data
                responseTransactions.forEachIndexed { responseIndex, _ ->

                    val expectedTransaction = testTransactionDataList[testTransactionDataList.size - 1 - responseIndex]

                    body("[$responseIndex].depotId", equalTo(expectedTransaction.depotId.toInt()))
                    body("[$responseIndex].country", equalTo(expectedTransaction.country))
                    body("[$responseIndex].recipient", equalTo(expectedTransaction.recipient))
                    body("[$responseIndex].iban", equalTo(expectedTransaction.iban))
                    body("[$responseIndex].bic", equalTo(expectedTransaction.bic))
                    body("[$responseIndex].moneyValue", equalTo(expectedTransaction.moneyValue.toFloat()))
                    body("[$responseIndex].purposeOfUse", equalTo(expectedTransaction.purposeOfUse))
                    body("[$responseIndex].standingOrder", equalTo(expectedTransaction.standingOrder))
                    body("[$responseIndex].transactionType", equalTo(expectedTransaction.transactionType))
                    body(
                        "[$responseIndex].transactionClassification",
                        equalTo(expectedTransaction.transactionClassification)
                    )
                }
            }
        }
    }

    @Test
    fun `update an existing transaction by ID`() {
        val testTransactionDataList = createTransactions()

        // Create a new transaction
        val createResponse = Given {
            contentType(ContentType.JSON)
            accept(ContentType.JSON)
            body(testTransactionDataList[0])
        } When {
            post()
        } Then {
            statusCode(201)
        } Extract {
            response()
        }

        // Extract the Location header to get the ID of the created transaction
        val locationHeader = createResponse.getHeader("Location")
        val id = locationHeader.split("/").last()

        // Update the transaction
        val updatedTransaction = testTransactionDataList[0].copy(
            id = id.toLong(),
            moneyValue = BigDecimal(2000.00),
            purposeOfUse = "Updated payment for services"
        )

        Given {
            contentType(ContentType.JSON)
            accept(ContentType.JSON)
            body(updatedTransaction)
        } When {
            post("/updateTransactionById")
        } Then {
            statusCode(201)
        } Extract {
            response()
        }

        // Verify that the transaction was updated correctly
        Given {
            contentType(ContentType.JSON)
            accept(ContentType.JSON)
        } When {
            get("getTransactionById/$id")
        } Then {
            statusCode(200)
            body("moneyValue", equalTo(BigDecimal(2000.00).toFloat()))
            body("purposeOfUse", equalTo("Updated payment for services"))
        }
    }

    @Test
    fun `create and retrieve all transactions by depot ID`() {
        val testTransactionDataList = createTransactions()

        // Create multiple new transactions with the same depotId
        testTransactionDataList.forEach { data ->
            Given {
                contentType(ContentType.JSON)
                accept(ContentType.JSON)
                body(data)
            } When {
                post()
            } Then {
                statusCode(201)
            } Extract {
                response()
            }
        }

        // Get all transactions by depotId
        val depotId = testTransactionDataList[0].depotId
        Given {
            contentType(ContentType.JSON)
            accept(ContentType.JSON)
        } When {
            get("getAllTransactionsByDepotId/$depotId")
        } Then {
            statusCode(200)

            // Validate the size of the returned list matches the number of created transactions
            body("$.size()", equalTo(testTransactionDataList.size))

            // Validate each transaction's details
            testTransactionDataList.forEachIndexed { index, expectedTransaction ->
                body("[$index].depotId", equalTo(expectedTransaction.depotId.toInt()))
                body("[$index].country", equalTo(expectedTransaction.country))
                body("[$index].recipient", equalTo(expectedTransaction.recipient))
                body("[$index].iban", equalTo(expectedTransaction.iban))
                body("[$index].bic", equalTo(expectedTransaction.bic))
                body("[$index].moneyValue", equalTo(expectedTransaction.moneyValue.toFloat()))
                body("[$index].purposeOfUse", equalTo(expectedTransaction.purposeOfUse))
                body("[$index].standingOrder", equalTo(expectedTransaction.standingOrder))
                body("[$index].transactionType", equalTo(expectedTransaction.transactionType))
                body("[$index].transactionClassification", equalTo(expectedTransaction.transactionClassification))
            }
        }
    }

    /**
     * Test for creating and deleting a transaction by ID.
     */
    @Test
    fun `create and delete a transaction by id`() {
        val newTransactionJson = """
        {
            "depotId": 1,
            "country": "Germany",
            "recipient": "John Doe",
            "iban": "DE89370400440532013000",
            "bic": "COBADEFFXXX",
            "moneyValue": 1000.00,
            "purposeOfUse": "Payment for services",
            "standingOrder": false,
            "transactionType": "outcome",
            "transactionClassification": "Dauerauftrag"
        }
        """.trimIndent()

        // 1. Create a new transaction
        val createResponse = Given {
            contentType(ContentType.JSON)
            accept(ContentType.JSON)
            body(newTransactionJson)
        } When {
            post()
        } Then {
            statusCode(201)
        } Extract {
            response()
        }

        // 2. Extract ID from the Location header
        val locationHeader = createResponse.getHeader("Location")
        val transactionId = locationHeader?.split("/")?.last()?.toLong()
            ?: throw IllegalStateException("Location header is missing or does not contain an ID")

        // 3. Delete the transaction by ID
        Given {
            accept(ContentType.ANY)
        } When {
            delete("/deleteTransactionBy/$transactionId")
        } Then {
            statusCode(200)
        }

        // 4. Check if the transaction is deleted
        Given {
            accept(ContentType.ANY)
        } When {
            get("/getTransactionById/$transactionId")
        } Then {
            statusCode(404) // Adjusted to expect 404 Not Found if the transaction was deleted
        }
    }

}




