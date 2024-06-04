package de.fhe.cc.team4.aurumbanking.core

import de.fhe.cc.team4.aurumbanking.data.repostories.DepotRepositoryImp
import de.fhe.cc.team4.aurumbanking.domain.Depot
import io.quarkus.logging.Log
import io.quarkus.runtime.StartupEvent
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.event.Observes
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import java.math.BigDecimal
import java.text.SimpleDateFormat

@ApplicationScoped
class DatabaseInitBean {
    /*
    @Inject
    private lateinit var depotRepositoryImp: DepotRepositoryImp
    val depot = Depot(
        id = 0,
        customerId = 1,
        dateTime = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse("2024-06-03T12:00:00Z"),
        flag = true,
        moneyValue = BigDecimal("1500.75"),
        iban = "DE89370400440532013000",
        bic = "COBADEFFXXX",
        type = "Deposit"
    )

    @Transactional
    operator fun invoke(@Observes evt: StartupEvent?) {
        depotRepositoryImp.persistNewDepotInformation(depot)
        Log.info("Test Depot Daten wurden angelegt")
    }


     */
}