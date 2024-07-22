package de.fhe.cc.team4.aurumbanking.repositories

import de.fhe.cc.team4.aurumbanking.model.CustomerInformationRepositoryImpl
import de.fhe.cc.team4.aurumbanking.util.createCustomerInformation
import io.quarkus.test.TestReactiveTransaction
import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.vertx.UniAsserter
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@QuarkusTest
class CustomerInformationRepositoryTest {

    @Inject
    lateinit var customerInformationRepository: CustomerInformationRepositoryImpl


    @Test
    @TestReactiveTransaction
    fun testCreateCustomerInformation(asserter: UniAsserter) {

        val customer = createCustomerInformation()

        asserter.assertThat(
            {
                this.customerInformationRepository
                    .persist(customer)
                    .replaceWith(this.customerInformationRepository.findCustomerInformationById(1))
            },
            { customerInformation ->
                assertNotNull(customerInformation)

                customerInformation?.let {
                    assertEquals("John", it.firstname)
                    assertEquals("Doe", it.lastname)
                    assertEquals("johndoe", it.username)
                    assertEquals("johndoe@example.com", it.email)
                }
            }
        )
    }


    @Test
    @TestReactiveTransaction
    fun testUpdateAndGetCustomerInformationCredentials(asserter: UniAsserter) {

        val customer = createCustomerInformation()

        asserter.assertThat(
            {
                this.customerInformationRepository
                    .persist(customer)
                    .replaceWith(this.customerInformationRepository.updateCustomerEmailById(1, "t@t.de"))
                    .replaceWith(this.customerInformationRepository.updateCustomerPasswordById(1, "testpassword123"))
                    .replaceWith(this.customerInformationRepository.findCustomerLoginInformationById(1))
            },
            { customerInformation ->
                customerInformation?.let {
                    assertEquals("t@t.de", it.email)
                    assertEquals("testpassword123", it.password)
                }
            }
        )
    }

    @Test
    @TestReactiveTransaction
    fun testCreateAndDeleteCustomerInformation(asserter: UniAsserter) {

        val customer = createCustomerInformation()

        asserter.assertThat(
            {
                this.customerInformationRepository
                    .persist(customer)
                    .replaceWith(this.customerInformationRepository.deleteCustomerInformationById(1))
                    .replaceWith(this.customerInformationRepository.findCustomerInformationById(1))
            },
            { customerInformation -> assertNull(customerInformation) }
        )
    }


}