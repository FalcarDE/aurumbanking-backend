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


@QuarkusTest
@TestHTTPEndpoint(CustomerInformationResource::class)
class CustomerInformationResourceTest {

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
            delete("deleteCustomerInformationBy/1")
        } Then {
            statusCode(200)
        }
    }


    /**
     * Test case for creating and retrieving a new customer.
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
            body(customerInformationData)
            accept(ContentType.ANY)
        } When {
            post()
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
            body("email", equalTo("johndoe@example.com"))
            body("phoneNumber", equalTo("+1234567890"))
            body("password", equalTo("securepassword"))
            body("profileImage", equalTo("iVBORw0KGgoAAAANSUhEUgAAAGQAAABkCAIAAAD/gAIDAAAAEklEQVR4nO3BMQEAAADCoPdPbQ43oAAAAABJRU5ErkJggg=="))
        }
    }


    /**
     * Test case for updating password by id.
     *
     * This test case verifies that a new product can be created by sending a POST request to the server.
     * It uses the `Given`, `When`, and `Then` DSL (Domain-Specific Language) functions to execute a series
     * of HTTP requests and assertions.
     */
    @Test
    fun `updating password by id`() {
        val customerInformationData = createCustomerInformation()

        Given {
            body(customerInformationData)
            accept(ContentType.ANY)
        } When {
            post()
            put("updateCustomerPasswordBy/1/password")
        } Then {
            statusCode(200)
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

        Given {
            contentType(ContentType.JSON)
            body(customerInformationData)
            accept(ContentType.ANY)
        } When {
            post()
            delete("deleteCustomerInformationBy/1")
            get("/1")
        } Then {
            statusCode(404)
        }
    }









}



