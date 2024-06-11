package de.fhe.cc.team4.aurumbanking.domain

import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class GetAllTransactionsByDepotIdUc(var transactionInterfaceRepository: TransactionInterfaceRepository) {
    operator fun invoke(id: Long): Uni<List<TransactionDomainModel>> =
        transactionInterfaceRepository.findAllTransactionByDepotId(id)
}


@ApplicationScoped
class GetAllTransactionsByCustomerIdUc(var transactionInterfaceRepository: TransactionInterfaceRepository) {
    operator fun invoke(id: Long): Uni<List<TransactionDomainModel>> =
        transactionInterfaceRepository.findAllTransactionByCustomerId(id)
}


// TODO: GET and POST für Transaktionen + Erweiterungen der Usecases