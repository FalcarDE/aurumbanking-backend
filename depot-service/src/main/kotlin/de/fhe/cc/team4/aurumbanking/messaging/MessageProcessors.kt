package de.fhe.cc.team4.messaging

import de.fhe.cc.team4.aurumbanking.domain.IncomingTransactionDTO
import de.fhe.cc.team4.aurumbanking.model.repostories.DepotRepositoryImp
import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.eclipse.microprofile.reactive.messaging.Incoming

/**
 * This class handles new reviews by updating the corresponding product with the review information.
 * Review aggregates are received by Kafka messaging.
 */
@ApplicationScoped
class TransactionMessageProcessor {

    @Inject
    lateinit var depotRepositoryImp: DepotRepositoryImp


    @Incoming("update-depot-value")
    @WithSession
    fun handleUpdateDepotValueFromKafkaEmitterById(incomingTransactionDTO: IncomingTransactionDTO): Uni<Void> {
        return depotRepositoryImp.updateDepositValueByDepotById(
            incomingTransactionDTO.id,
            incomingTransactionDTO.transactionValue
        ).replaceWithVoid()
    }
}