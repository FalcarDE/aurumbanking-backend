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
        val supportRequest = createSupportEntry()

        asserter.assertThat(
            {
                this.supportRepository.persist(supportRequest) //  this.supportRepository.persistNewSupportInformation(supportRequest)
                    .replaceWith(this.supportRepository.findSupportRequestById(supportRequest.id))
            },
            { foundRequest ->
                assertNotNull(foundRequest)

                foundRequest?.let {
                    assertEquals(12345, it.customerId)
                    assertEquals("Konto", it.type)
                    assertEquals( "Wie kann ich mein Konto erstellen?", it.message)
                }
            }
        )
    }

/*
    @Test
    @TestReactiveTransaction
    fun testDeleteSupportRequestsByCustomerId(asserter: UniAsserter) {
        // Erstelle mehrere Support-Einträge mit der gleichen customerId
        val supportRequest1 = createSupportEntry1()
        val supportRequest2 = createSupportEntry2()
        asserter.assertThat(
            {
                // Persistiere beide Support-Anfragen
                this.supportRepository.persist(supportRequest1) // this.supportRepository.persistNewSupportInformation(supportRequest1)
                    .replaceWith(this.supportRepository.persist(supportRequest2))
                    // Lösche alle Support-Anfragen für diese customerId
                    .replaceWith(this.supportRepository.deleteAllSupportRequestByCustomerId(12345))
                    // Überprüfe, ob alle Anfragen gelöscht wurden, indem du versuchst, sie zu finden
                    .replaceWith(this.supportRepository.findSupportRequestByCustomerId(12345))
            },
            { foundRequests ->
                // Überprüfe, dass keine Anfragen mehr gefunden werden
                assertTrue(foundRequests.isEmpty())
            }
        )
    }


    @Test
    @TestReactiveTransaction
    fun testFindSupportRequestsByType(asserter: UniAsserter) {
        // Hole die Liste der Testdaten
        val supportEntries = createAllSupportEntries()

        // Wähle die ersten beiden Support-Einträge aus der Liste aus
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


 */
}
