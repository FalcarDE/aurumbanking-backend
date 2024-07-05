package de.fhe.cc.team4.messaging

import de.fhe.cc.team4.aurumbanking.domain.IncomingTransactionDTO
import de.fhe.cc.team4.aurumbanking.model.repostories.DepotRepositoryImp
import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.eclipse.microprofile.reactive.messaging.Incoming
import java.math.BigDecimal

@ApplicationScoped
class TransactionMessageProcessor {

    @Inject
    lateinit var depotRepositoryImp: DepotRepositoryImp


    @Incoming("update-depot-value-topic")
    @WithSession
    fun handleUpdateDepotValueFromKafkaEmitterById(incomingTransactionDTO: IncomingTransactionDTO): Uni<Void> {
        println("Got new Transaction: $incomingTransactionDTO")

        return depotRepositoryImp.findCurrentDepotValueById(incomingTransactionDTO.depotId)
            .onItem().transformToUni { currentDepotValue ->
                val calculatedDepotValue: BigDecimal = when (incomingTransactionDTO.transactionType) {
                    "income" -> currentDepotValue.depositAmount.add(incomingTransactionDTO.moneyValue)
                    "outcome" -> currentDepotValue.depositAmount.subtract(incomingTransactionDTO.moneyValue)
                    else -> throw IllegalArgumentException("Unknown transaction type: ${incomingTransactionDTO.transactionType}")
                }
                println("New Depot-Value: $calculatedDepotValue")
                depotRepositoryImp.updateDepositValueByDepotById(
                    incomingTransactionDTO.depotId,
                    calculatedDepotValue
                )
            }
            .replaceWithVoid()
    }
}