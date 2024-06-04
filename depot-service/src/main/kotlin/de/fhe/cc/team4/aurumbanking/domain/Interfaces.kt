package de.fhe.cc.team4.aurumbanking.domain

import io.smallrye.mutiny.Uni

interface DepotInterfaceRepository {

    fun findDepotByCustomerId(id: Long): Uni<DepositDomainModel>
    fun findDepotById(id: Long): Uni<DepositDomainModel?>

    fun persistNewDepotInformation(depot: DepositDomainModel): Uni<DepositDomainModel>

    fun deleteDepotById(id: Long): Uni<Void>
    fun deleteAllDepotInformation(): Uni<Void>
}

interface TransactionInterfaceRepository {

    fun findTransactionByCustomerId(id: Long): Uni<TransactionDomainModel>
    fun findTransactionByDepotId(id: Long): Uni<DepositDomainModel?>
    fun findAllTransactions(): Uni<TransactionDomainModel>
    fun persistTransactionByDepotId(depot: DepositDomainModel): Uni<DepositDomainModel>

}