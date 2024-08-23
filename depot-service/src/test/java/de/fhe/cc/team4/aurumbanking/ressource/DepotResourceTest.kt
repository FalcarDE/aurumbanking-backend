package de.fhe.cc.team4.aurumbanking.resources

import de.fhe.cc.team4.aurumbanking.util.createDepot
import io.quarkus.test.common.http.TestHTTPEndpoint
import io.quarkus.test.junit.QuarkusTest
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test



@QuarkusTest
@TestHTTPEndpoint(DepotResource::class)
class DepotResourceTest {


    /**
     * Clears the database by sending a DELETE request to the server.
     *
     * This method is used to clear the database before each test case. It sends a DELETE request to the server
     * with the appropriate Accept header set to JSON. The method expects a 200 status code as a response from the server.
     * If the status code received is not 200, the test fails.
     *
     * @throws AssertionError If the status code received from the server is not 200.
     */
    @BeforeEach
    fun clearDB() {

        Given {
            accept(ContentType.JSON)
        } When {
            delete("deleteDepotBy/1")
        } Then {
            statusCode(200)
        }

    }



    /**
     * Test case for creating, update deposit amount, retrieving and deleting a depot.
     *
     * This test case verifies that a new depot can be created by sending a POST request to the server.
     * It uses the `Given`, `When`, and `Then` DSL (Domain-Specific Language) functions to execute a series
     * of HTTP requests and assertions.
     */
    @Test
    fun `create, update, retrieve and delete depot`() {
        val depot = createDepot()
        val customerId = depot.customerId

        // 1. create new depot
        val response = Given {
            contentType(ContentType.JSON)
            accept(ContentType.ANY)
            body(depot)
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


        // 2. GET request to retrieve depot
        Given {
            contentType(ContentType.JSON)
            accept(ContentType.ANY)
        } When {
            get("/$id")
        } Then {
            statusCode(200)
            body("customerId", equalTo(1))
            body("currencyArea", equalTo("EUR"))
            body("depositAmount", equalTo(2500f))
            body("fallbackDepositAmount", equalTo(1000f))
        }


        // 3. PUT request to update deposit amount
       Given {
            contentType(ContentType.JSON)
            accept(ContentType.ANY)
        } When {
            put("/insertNewDepositValueById/$id/7000.45")
        } Then {
            statusCode(200)
        }


        // 4. GET request to retrieve updated deposit amount
       Given {
            contentType(ContentType.JSON)
            accept(ContentType.ANY)
        } When {
            get("/getCurrentDepotValueById/$id")
        } Then {
            statusCode(200)
            body("depositAmount", equalTo(7000.45f))
        }


        // 5. Check depot by customer ID
          Given {
            contentType(ContentType.JSON)
            accept(ContentType.ANY)
        } When {
            get("getDepotByCustomerId/$customerId")
        } Then {
            statusCode(200)
            body("customerId", equalTo(1))
            body("currencyArea", equalTo("EUR"))
            body("depositAmount", equalTo(7000.45f))
            body("fallbackDepositAmount", equalTo(1000f))
        }


        // 6. Delete depot
        Given {
            accept(ContentType.JSON)
        } When {
            delete("deleteDepotBy/$id")
        } Then {
            statusCode(200)
        }


        // 7. Check if depot is deleted
        Given {
            accept(ContentType.ANY)
        } When {
            get("/$id")
        } Then {
            statusCode(404)
        }
    }
}

