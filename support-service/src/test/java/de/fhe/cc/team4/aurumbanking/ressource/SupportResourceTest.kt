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
     * Test for inserting a new support request
     */
    @Test
    fun `insert new support request`() {
        val newSupportRequestJson = """
        {
            "message": "I have an issue with my account.",
            "type": "technical-1",
            "customerId": 1001
        }
        """.trimIndent()

        // Insert new support request
        Given {
            contentType(ContentType.JSON)
            accept(ContentType.JSON)
            body(newSupportRequestJson)
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
        // First, insert a support request to retrieve it later
        val newSupportRequestJson = """
        {
            "message": "I have an issue with my account.",
            "type": "technical-3",
            "customerId": 1003
        }
        """.trimIndent()

        // Insert new support request
        val response = Given {
            contentType(ContentType.JSON)
            accept(ContentType.JSON)
            body(newSupportRequestJson)
        } When {
            post()
        } Then {
            statusCode(201)
        } Extract {
            response()
        }

        // Extract the ID from the Location header
        val locationHeader = response.getHeader("Location")
        val requestId = locationHeader?.split("/")?.last()?.toLong()
            ?: throw IllegalStateException("Location header is missing or does not contain an ID")

        // Get support request by ID
        Given {
            accept(ContentType.JSON)
        } When {
            get("/$requestId")
        } Then {
            statusCode(200)
            body("id", equalTo(requestId.toInt()))
            body("customerId", equalTo(1003))
        }
    }

    /**
     * Test for getting all support requests by type
     */
    @Test
    fun `get all support requests by type`() {
        // Insert support requests
        val firstSupportRequestJson = """
        {
            "message": "I have an issue with my account.",
            "type": "technical-4",
            "customerId": 1004
        }
        """.trimIndent()

        val secondSupportRequestJson = """
        {
            "message": "I forgot what to ask.",
            "type": "technical-4",
            "customerId": 1005
        }
        """.trimIndent()

        val thirdSupportRequestJson = """
        {
            "message": "I forgot what to ask.",
            "type": "transaction-4",
            "customerId": 1005
        }
        """.trimIndent()

        // Insert the support requests
        Given {
            contentType(ContentType.JSON)
            accept(ContentType.JSON)
            body(firstSupportRequestJson)
        } When {
            post()
        } Then {
            statusCode(201)
        }

        Given {
            contentType(ContentType.JSON)
            accept(ContentType.JSON)
            body(secondSupportRequestJson)
        } When {
            post()
        } Then {
            statusCode(201)
        }

        Given {
            contentType(ContentType.JSON)
            accept(ContentType.JSON)
            body(thirdSupportRequestJson)
        } When {
            post()
        } Then {
            statusCode(201)
        }

        // Get all support requests by type
        val requestType = "technical-4"

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
        assert(supportRequests.size == 2) { "Expected 2 support requests, but got ${supportRequests.size}" }
    }

    /**
     * Test for getting all support requests by customer ID
     */
    @Test
    fun `get all support requests by customer id`() {
        // Insert support request for customer ID 1006 and one other
        val newSupportRequestJson = """
        {
            "message": "I forgot what to ask.",
            "type": "technical-5",
            "customerId": 1006
        }
        """.trimIndent()

        val secondSupportRequestJson = """
        {
            "message": "I forgot what to ask.",
            "type": "technical-5",
            "customerId": 1006
        }
        """.trimIndent()

        val thirdSupportRequestJson = """
        {
            "message": "I forgot what to ask.",
            "type": "technical-5",
            "customerId": 1111
        }
        """.trimIndent()

        Given {
            contentType(ContentType.JSON)
            accept(ContentType.JSON)
            body(newSupportRequestJson)
        } When {
            post()
        } Then {
            statusCode(201)
        }

        Given {
            contentType(ContentType.JSON)
            accept(ContentType.JSON)
            body(secondSupportRequestJson)
        } When {
            post()
        } Then {
            statusCode(201)
        }

        Given {
            contentType(ContentType.JSON)
            accept(ContentType.JSON)
            body(thirdSupportRequestJson)
        } When {
            post()
        } Then {
            statusCode(201)
        }

        // Get all support requests by customer ID
        val customerId = 1006L

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
        assert(supportRequests.size == 2) { "Expected 2 support request, but got ${supportRequests.size}" }
    }

    /**
     * Test for creating and deleting a support request by ID
     */
    @Test
    fun `create and delete a support request by id`() {
        val newSupportRequestJson = """
        {
            "message": "I have an issue with my account.",
            "type": "technical-6",
            "customerId": 1007
        }
        """.trimIndent()

        // 1. Create new support request
        val response = Given {
            contentType(ContentType.JSON)
            accept(ContentType.JSON)
            body(newSupportRequestJson)
        } When {
            post()
        } Then {
            statusCode(201)
        } Extract {
            response()
        }

        // 2. Extract ID from the Location header
        val locationHeader = response.getHeader("Location")
        val requestId = locationHeader?.split("/")?.last()?.toLong()
            ?: throw IllegalStateException("Location header is missing or does not contain an ID")

        // 3. Delete the support request
        Given {
            accept(ContentType.ANY)
        } When {
            delete("/deleteRequestById/$requestId")
        } Then {
            statusCode(200)
        }

        // 4. Check if the support request is deleted
        Given {
            accept(ContentType.ANY)
        } When {
            get("/$requestId")
        } Then {
            statusCode(404)
        }
    }


    /**
     * Test for deleting all support requests by customer ID
     */
    @Test
    fun `delete request by customer id`() {
        val customerId = 1010L

        // First, insert support requests for this customer
        val firstSupportRequestJson = """
        {
            "message": "This is the first support request for deletion.",
            "type": "technical-7",
            "customerId": $customerId
        }
        """.trimIndent()

        val secondSupportRequestJson = """
        {
            "message": "This is the second support request for deletion.",
            "type": "technical-7",
            "customerId": $customerId
        }
        """.trimIndent()

        // Insert both support requests for this customer ID
        Given {
            contentType(ContentType.JSON)
            accept(ContentType.JSON)
            body(firstSupportRequestJson)
        } When {
            post()
        } Then {
            statusCode(201)
        }

        Given {
            contentType(ContentType.JSON)
            accept(ContentType.JSON)
            body(secondSupportRequestJson)
        } When {
            post()
        } Then {
            statusCode(201)
        }

        // Now delete all support requests for this customer
        Given {
            accept(ContentType.JSON)
        } When {
            delete("/deleteRequestByCustomerId/$customerId")
        } Then {
            statusCode(200)
        }

        // Verify that all support requests for this customer have been deleted
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

        // Assert that no support requests exist for this customer
        assert(supportRequests.isEmpty()) { "Support requests still exist for customer ID $customerId" }
    }
}
