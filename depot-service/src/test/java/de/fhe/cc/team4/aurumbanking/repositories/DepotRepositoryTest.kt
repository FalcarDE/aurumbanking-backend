package de.fhe.cc.team4.aurumbanking.repositories

import de.fhe.cc.team4.aurumbanking.model.repostories.DepotRepositoryImp
import de.fhe.cc.team4.aurumbanking.util.createDepot
import io.quarkus.test.TestReactiveTransaction
import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.vertx.UniAsserter
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal

@QuarkusTest
class DepotRepositoryTest {

    val depot = createDepot()

    @Inject
    lateinit var DepositRepository: DepotRepositoryImp


    @Test
    @TestReactiveTransaction
    fun testCreateDepot(asserter: UniAsserter) {
        // create a depot and validate the inserted values by searching entry via customer ID

        asserter.assertThat(
                {
                        this.DepositRepository
                                .persist(depot)
                                .replaceWith(this.DepositRepository.findDepotByCustomerId(depot.customerId))
                },
                { depot ->
                        assertNotNull(depot)

                        depot?.let {
            assertEquals(depot.customerId, it.customerId)
            assertEquals(depot.currencyArea, it.currencyArea)
            assertEquals(depot.depositAmount, it.depositAmount)
            assertEquals(depot.fallbackDepositAmount, it.fallbackDepositAmount)

        }
            }
        )
    }


    @Test
    @TestReactiveTransaction
    fun testUpdateAndGetDepositAmount(asserter: UniAsserter) {
        // update the deposit amount for the existing value and validate it by searching the value via depot ID

        asserter.assertThat(
                {
                        this.DepositRepository
                                .findDepotById(depot.id)
                                .replaceWith(this.DepositRepository.updateDepositValueByDepotId(depot.id, BigDecimal.valueOf(3500.35))) // update deposit value
                                .replaceWith(this.DepositRepository.findCurrentDepotValueById(depot.id))
                },
                { depot ->
                        depot?.let {
                        assertEquals(BigDecimal.valueOf(3500.35), it.depositAmount)
                }
                }
        )
    }

    @Test
    @TestReactiveTransaction
    fun testCreateAndDeleteDepot(asserter: UniAsserter) {
        // delete the existing entry and check if the search result for the deleted depot ID is null

        asserter.assertThat(
                {
                        this.DepositRepository
                                .findDepotById(depot.id)
                                .replaceWith(this.DepositRepository.deleteDepotById(depot.id)) // delete depot
                                .replaceWith(this.DepositRepository.findDepotById(depot.id))
                },
                { depot -> assertNull(depot) } // search result should be null to confirm deletion of entry
        )
    }


}
