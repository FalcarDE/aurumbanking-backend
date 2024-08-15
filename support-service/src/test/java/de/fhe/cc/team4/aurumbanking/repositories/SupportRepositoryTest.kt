package de.fhe.cc.team4.aurumbanking.repositories

import de.fhe.cc.team4.aurumbanking.model.SupportRepositoryImp
import de.fhe.cc.team4.aurumbanking.util.createSupportEntry
import de.fhe.cc.team4.aurumbanking.util.createSupportEntry1
import de.fhe.cc.team4.aurumbanking.util.createSupportEntry2
import de.fhe.cc.team4.aurumbanking.util.createAllSupportEntries

import io.quarkus.test.TestReactiveTransaction
import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.vertx.UniAsserter
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@QuarkusTest
class SupportRepositoryTest {

    @Inject
    lateinit var supportRepository: SupportRepositoryImp

    @Test
    @TestReactiveTransaction
    fun testCreateSupportRequest(asserter: UniAsserter) {
        val newSupportRequest = createSupportEntry()

        asserter.assertThat(
            {
                this.supportRepository.persist(newSupportRequest)
                    .flatMap { this.supportRepository.findSupportRequestById(newSupportRequest.id!!) }
            },
            { foundRequest ->
                assertNotNull(foundRequest)
                foundRequest?.let {
                    assertEquals(newSupportRequest.id, it.id)
                    assertEquals(newSupportRequest.customerId, it.customerId)
                    assertEquals(newSupportRequest.type, it.type)
                    assertEquals(newSupportRequest.message, it.message)
                }
            }
        )
    }


    @Test
    @TestReactiveTransaction
    fun testDeleteSupportRequestsByCustomerId(asserter: UniAsserter) {
        val supportRequest1 = createSupportEntry1()
        val supportRequest2 = createSupportEntry2()

        asserter.assertThat(
            {
                this.supportRepository.persist(supportRequest1)
                    .replaceWith(this.supportRepository.persist(supportRequest2))
                    .replaceWith(this.supportRepository.deleteAllSupportRequestByCustomerId(12345))
                    .replaceWith(this.supportRepository.findSupportRequestByCustomerId(12345))
            },
            { foundRequests ->
                assertTrue(foundRequests.isEmpty())
            }
        )
    }


    @Test
    @TestReactiveTransaction
    fun testFindSupportRequestsByType(asserter: UniAsserter) {
        val supportEntries = createAllSupportEntries()
        val supportRequest1 = supportEntries[0].apply { type = "Technical Support" }
        val supportRequest2 = supportEntries[1].apply { type = "Customer Service" }

        asserter.assertThat(
            {
                this.supportRepository.persist(supportRequest1)
                    .replaceWith(this.supportRepository.persist(supportRequest2))
                    .replaceWith(this.supportRepository.getAllRequestsByType("Technical Support"))
            },
            { supportRequests ->
                assertNotNull(supportRequests)
                assertEquals(1, supportRequests.size)
                assertEquals("Technical Support", supportRequests[0].type)
            }
        )
    }
}
