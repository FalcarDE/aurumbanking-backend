package de.fhe.cc.team4.aurumbanking.domain

import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class GetAllTransactionsByDepotIdUc(var transactionInterfaceRepository: TransactionInterfaceRepository) {
    operator fun invoke(id: Long): Uni<List<TransactionDomainModel>> =
        transactionInterfaceRepository.findAllTransactionByDepotId(id)
}

@ApplicationScoped
class InsertNewTransactionsUc(var transactionInterfaceRepository: TransactionInterfaceRepository) {
    operator fun invoke(transactionDomainModel: TransactionDomainModel): Uni<TransactionDomainModel> =
        transactionInterfaceRepository.insertNewTransaction(transactionDomainModel)
}

@ApplicationScoped
class GetThreeLastestTransactionByDepotIdUc(var transactionInterfaceRepository: TransactionInterfaceRepository) {
    operator fun invoke(id: Long) =
        transactionInterfaceRepository.getThreeLastestTransactionByDepotId(id)
}

