package de.fhe.cc.team4.aurumbanking.resources

import de.fhe.cc.team4.aurumbanking.util.createCustomerInformation
import io.quarkus.test.common.http.TestHTTPEndpoint
import io.quarkus.test.junit.QuarkusTest
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.Base64


@QuarkusTest
@TestHTTPEndpoint(CustomerInformationResource::class)
class CustomerInformationResourceTest {

    fun encodeCredentials(username: String, password: String): String {
        val credentials = "$username:$password"
        return Base64.getEncoder().encodeToString(credentials.toByteArray())
    }


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
        val encodedCredentials = encodeCredentials("Hoang", "admin")

        Given {
            accept(ContentType.JSON)
            header("Authorization", "Basic $encodedCredentials")
        } When {
            delete("deleteCustomerInformationBy/1")
        } Then {
            statusCode(200)
        }
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

        Given {
            contentType(ContentType.JSON)
            accept(ContentType.ANY)
            body(customerInformationData)
        } When {
            post()
            put("updateCustomerPasswordBy/1/password")
            put("updateCustomerEmailBy/1/t@t.de")
            get("/1")
        } Then {
            //statusCode(201)
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
            body(
                "profileImage",
                equalTo("iVBORw0KGgoAAAANSUhEUgAAAGQAAABkCAIAAAD/gAIDAAAAEklEQVR4nO3BMQEAAADCoPdPbQ43oAAAAABJRU5ErkJggg==")
            )
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

        // Create Customer Information
        Given {
            contentType(ContentType.JSON)
            accept(ContentType.ANY)
            body(customerInformationData)
        } When {
            post()
        } Then {
            statusCode(201)
        }

        // Delete customer information
        Given {
            accept(ContentType.ANY)
            header("Authorization", "Basic $encodedCredentials")
        } When {
            delete("deleteCustomerInformationBy/1")
        } Then {
            statusCode(200)
        }

        // Check if customer information is deleted
        Given {
            accept(ContentType.ANY)
        } When {
            get("/1")
        } Then {
            statusCode(404)
        }
    }
}



