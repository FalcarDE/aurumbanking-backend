package de.fhe.cc.team4.aurumbanking.resources

import de.fhe.cc.team4.aurumbanking.util.createCustomerInformation
import io.quarkus.test.common.http.TestHTTPEndpoint
import io.quarkus.test.junit.QuarkusTest
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import io.restassured.RestAssured
import io.restassured.parsing.Parser
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*


@QuarkusTest
@TestHTTPEndpoint(CustomerInformationResource::class)
class CustomerInformationResourceTest {

    fun encodeCredentials(username: String, password: String): String {
        val credentials = "$username:$password"
        return Base64.getEncoder().encodeToString(credentials.toByteArray())
    }

    @BeforeEach
    fun setup() {
        // set den standard parser to JSON
        RestAssured.defaultParser = Parser.JSON
    }


    /**
     * Test case for creating, update password, email and retrieving a new customer.
     *
     * This test case verifies that a new product can be created by sending a POST request to the server.
     * It uses the `Given`, `When`, and `Then` DSL (Domain-Specific Language) functions to execute a series
     * of HTTP requests and assertions.
     */
    @Test
    fun `create and retrieve a new customer`() {
        val customerInformationData = createCustomerInformation()

        val encodedCredentials = encodeCredentials("Hoang", "admin")

        // 1. Create JWT Token
        val token = Given {
            accept(ContentType.ANY)
            header("Authorization", "Basic $encodedCredentials")
        } When {
            get("/createJwtToken")
        } Then {
            statusCode(200)
        } Extract {
            asString() // Extract the token from the response
        }

        // 2. create new customer with auth token
        val response = Given {
            contentType(ContentType.JSON)
            accept(ContentType.JSON)
            header("Authorization", "Bearer $token")
            body(customerInformationData)
        } When {
            post()
        } Then {
            statusCode(201)
        } Extract {
            response()
        }

        // Extract ID from location-header
        val locationHeader = response.getHeader("Location")
        val customerId = locationHeader?.split("/")?.last() ?: throw IllegalStateException("Location header is missing or does not contain an ID")

        // 3. PUT Request to update password
        Given {
            contentType(ContentType.JSON)
            accept(ContentType.ANY)
        } When {
            put("/updateCustomerPasswordBy/$customerId/password")
        } Then {
            statusCode(200)
        }

        // 4. PUT Request to update email
        Given {
            contentType(ContentType.JSON)
            accept(ContentType.ANY)
        } When {
            put("/updateCustomerEmailBy/$customerId/t@t.de")
        } Then {
            statusCode(200)
        }

        // 5. GET Request to retrieve customer information
        Given {
            contentType(ContentType.JSON)
            accept(ContentType.ANY)
        } When {
            get("/$customerId")
        } Then {
            statusCode(200)
            body("firstname", equalTo("John"))
            body("lastname", equalTo("Doe"))
            body("streetName", equalTo("Main Street"))
            body("housenumber", equalTo("123"))
            body("city", equalTo("Sample City"))
            body("country", equalTo("Sample Country"))
            body("zipcode", equalTo("DE17538"))
            body("username", equalTo("johndoe"))
            body("email", equalTo("t@t.de"))
            body("phoneNumber", equalTo("+1234567890"))
            body("password", equalTo("password"))
        }
    }

    /**
     * Test case for creating and deleting a customer.
     *
     * This test case verifies that a new product can be created by sending a POST request to the server.
     * It uses the `Given`, `When`, and `Then` DSL (Domain-Specific Language) functions to execute a series
     * of HTTP requests and assertions.
     */
    @Test
    fun `create and delete a customer`() {
        val customerInformationData = createCustomerInformation()

        val encodedCredentials = encodeCredentials("Hoang", "admin")


        // 1. Create JWT Token
        val token = Given {
            accept(ContentType.ANY)
            header("Authorization", "Basic $encodedCredentials")
        } When {
            get("/createJwtToken")
        } Then {
            statusCode(200)
        } Extract {
            asString() // Extract the token from the response
        }


        // 2. create new customer with auth token
        val response = Given {
            contentType(ContentType.JSON)
            accept(ContentType.JSON)
            header("Authorization", "Bearer $token")
            body(customerInformationData)
        } When {
            post()
        } Then {
            statusCode(201)
        } Extract {
            response()
        }

        // Extract ID from location-header
        val locationHeader = response.getHeader("Location")
        val customerId = locationHeader?.split("/")?.last() ?: throw IllegalStateException("Location header is missing or does not contain an ID")

        // 3. Delete customer information
        Given {
            accept(ContentType.ANY)
            header("Authorization", "Bearer $token")
        } When {
            delete("/deleteCustomerInformationBy/$customerId")
        } Then {
            statusCode(200)
        }

        // 4. Check if customer information is deleted
        Given {
            accept(ContentType.ANY)
        } When {
            get("/$customerId")
        } Then {
            statusCode(404)
        }
    }
}



