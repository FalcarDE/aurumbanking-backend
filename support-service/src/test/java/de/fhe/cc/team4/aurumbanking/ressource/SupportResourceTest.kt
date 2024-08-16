package de.fhe.cc.team4.aurumbanking.resources

import io.quarkus.test.common.http.TestHTTPEndpoint
import io.quarkus.test.junit.QuarkusTest
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Test
import org.hamcrest.CoreMatchers.equalTo

@QuarkusTest
@TestHTTPEndpoint(SupportResource::class)
class SupportResourceTest {

    /**
     * Tests for inserting two support requests
     */
    @Test
    fun `insert new support request`() {
        val newSupportRequest = mapOf(
       
            "message" to "I have an issue with my account.",
            "type" to "technical",
            "customerId" to 1L
        )

        // Insert new support request
        Given {
            contentType(ContentType.JSON)
            accept(ContentType.JSON)
            body(newSupportRequest)
        } When {
            post()
        } Then {
            statusCode(201)
        } Extract {
            response()
        }
    }

    @Test
    fun `insert second support request`() {
        val newSupportRequest = mapOf(
            "message" to "I forgot what to ask.",
            "type" to "technical",
            "customerId" to 2L
        )

        // Insert new support request
        Given {
            contentType(ContentType.JSON)
            accept(ContentType.JSON)
            body(newSupportRequest)
        } When {
            post()
        } Then {
            statusCode(201)
        } Extract {
            response()
        }
    }

    @Test
    fun `insert third support request`() {
        val newSupportRequest = mapOf(
            "message" to "blablub.",
            "type" to "transaction",
            "customerId" to 3L
        )

        // Insert new support request
        Given {
            contentType(ContentType.JSON)
            accept(ContentType.JSON)
            body(newSupportRequest)
        } When {
            post()
        } Then {
            statusCode(201)
        } Extract {
            response()
        }
    }

    /**
     * Test for getting support request by ID
     */
    @Test
    fun `get support request by id`() {
        val requestId = 1L

        // Get support request by ID
        Given {
            accept(ContentType.JSON)
        } When {
            get("/$requestId")
        } Then {
            statusCode(200)
            body("id", equalTo(requestId.toInt()))
        }
    }


    /**
     * Test for getting all support requests by type
     */
    @Test
    fun `get all support requests by type`() {
        val requestType = "technical"

        // Get all support requests by type
        val response = Given {
            accept(ContentType.JSON)
        } When {
            get("/get-all-support-reqeuests-by-type/$requestType")
        } Then {
            statusCode(200)
        } Extract {
            response()
        }

        val jsonResponse = response.jsonPath()
        val supportRequests = jsonResponse.getList<Map<String, Any>>("")

        // Assertions based on the actual response
        assert(supportRequests.isNotEmpty()) { "No support requests found" }
        assert(supportRequests.size == 2) { "Expected 2 support request, but got ${supportRequests.size}" }
    }


    /**
     * Test for getting all support requests by customer ID
     */
    @Test
    fun `get all support requests by customer id`() {
        val customerId = 2L

        // Get all support requests by customer ID
        val response = Given {
            accept(ContentType.JSON)
        } When {
            get("/get-all-support-reqeuests-by-customer-id/$customerId")
        } Then {
            statusCode(200)
        } Extract {
            response()
        }

        val jsonResponse = response.jsonPath()
        val supportRequests = jsonResponse.getList<Map<String, Any>>("")

        // Assertions based on the actual response
        assert(supportRequests.isNotEmpty()) { "No support requests found for customer with ID $customerId" }
        assert(supportRequests.size == 1) { "Expected 1 support request, but got ${supportRequests.size}" }
    }


    /**
     * Test for creating and deleting a support request by customer ID
     */
    @Test
    fun `create and delete a support request by id`() {
        val newSupportRequest = mapOf(
            "message" to "I have an issue with my account.",
            "type" to "technical",
            "customerId" to 1L
        )

        // 1. Create new support request
        val response = Given {
            contentType(ContentType.JSON)
            accept(ContentType.JSON)
            body(newSupportRequest)
        } When {
            post()
        } Then {
            statusCode(201)
        } Extract {
            response()
        }

        // Extract ID from location-header
        val locationHeader = response.getHeader("Location")
        val requestId = locationHeader?.split("/")?.last()?.toLong()
            ?: throw IllegalStateException("Location header is missing or does not contain an ID")

        // 2. Delete the support request
        Given {
            accept(ContentType.ANY)
        } When {
            delete("/deleteRequestById/$requestId")
        } Then {
            statusCode(200)
        }

        // 3. Check if the support request is deleted
        Given {
            accept(ContentType.ANY)
        } When {
            get("/$requestId")
        } Then {
            statusCode(404)
        }
    }


    @Test
    fun `delete request by customer id`() {
        val customerId = 1L

        Given {
            accept(ContentType.JSON)
        } When {
            delete("/deleteRequestByCustomerId/$customerId")
        } Then {
            statusCode(200)
        }

        Given {
            accept(ContentType.JSON)
        } When {
            get("/get-all-support-reqeuests-by-customer-id/$customerId")
        } Then {
            statusCode(200)
        } Extract {
            response()
        }
    }
}


