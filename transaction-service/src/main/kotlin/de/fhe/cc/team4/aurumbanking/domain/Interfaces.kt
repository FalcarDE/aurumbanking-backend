package de.fhe.cc.team4.aurumbanking.domain

import io.smallrye.mutiny.Uni

interface TransactionInterfaceRepository {

    fun findAllTransactionByCustomerId(id: Long): Uni<List<TransactionDomainModel>>
    fun findAllTransactionByDepotId(id: Long): Uni<List<TransactionDomainModel>>
    fun insertNewTransactionByDepotId(id: Long, transactionDomainModel: TransactionDomainModel): Uni<TransactionDomainModel>

}