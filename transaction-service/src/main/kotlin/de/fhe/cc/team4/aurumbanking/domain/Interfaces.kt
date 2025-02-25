package de.fhe.cc.team4.aurumbanking.domain

import io.smallrye.mutiny.Uni

interface TransactionInterfaceRepository {
    fun findAllTransactionByDepotId(id: Long): Uni<List<TransactionDomainModel>>
    fun insertNewTransaction(transactionDomainModel: TransactionDomainModel) : Uni<TransactionDomainModel>
    fun getThreeLatestTransactionByDepotId(depotId: Long): Uni<List<TransactionDomainModel>>
    fun updateTransactionById(transactionDomainModel: TransactionDomainModel): Uni<TransactionDomainModel>
    fun getTransactionById(id: Long): Uni<TransactionDomainModel>
    fun deleteTransactionById(id: Long): Uni<Long>
}