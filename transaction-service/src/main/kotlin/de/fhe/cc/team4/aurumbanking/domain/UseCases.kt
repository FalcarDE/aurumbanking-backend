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
class GetThreeLatestTransactionByDepotIdUc(var transactionInterfaceRepository: TransactionInterfaceRepository) {
    operator fun invoke(id: Long) =
        transactionInterfaceRepository.getThreeLatestTransactionByDepotId(id)
}

@ApplicationScoped
class UpdateTransactionsByIdUc(var transactionInterfaceRepository: TransactionInterfaceRepository) {
    operator fun invoke(transactionDomainModel: TransactionDomainModel): Uni<TransactionDomainModel>
    = transactionInterfaceRepository.updateTransactionById(transactionDomainModel)
}

@ApplicationScoped
class GetTransactionById(var transactionInterfaceRepository: TransactionInterfaceRepository) {
    operator fun invoke(id: Long): Uni<TransactionDomainModel>
            = transactionInterfaceRepository.getTransactionById(id)
}

@ApplicationScoped
class GetTransactionsByIdUc(var transactionInterfaceRepository: TransactionInterfaceRepository) {
    operator fun invoke(id: Long): Uni<TransactionDomainModel> =
        transactionInterfaceRepository.getTransactionById(id)
}
