package de.fhe.cc.team4.aurumbanking.data.repostories

import de.fhe.cc.team4.aurumbanking.data.entities.TransactionEntityModel
import de.fhe.cc.team4.aurumbanking.domain.DepositDomainModel
import de.fhe.cc.team4.aurumbanking.domain.TransactionDomainModel
import de.fhe.cc.team4.aurumbanking.domain.TransactionInterfaceRepository
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped


@ApplicationScoped
class TransactionRepositoryImp : PanacheRepository<TransactionEntityModel>, TransactionInterfaceRepository  {
    override fun findTransactionByCustomerId(id: Long): Uni<TransactionDomainModel> {
        TODO("Not yet implemented")
    }

    override fun findTransactionByDepotId(id: Long): Uni<DepositDomainModel?> {
        TODO("Not yet implemented")
    }

    override fun findAllTransactions(): Uni<TransactionDomainModel> {
        TODO("Not yet implemented")
    }

    override fun persistTransactionByDepotId(depot: DepositDomainModel): Uni<DepositDomainModel> {
        TODO("Not yet implemented")
    }

}

/**
 * Repository for managing customer information attributes in the database.
 */
@ApplicationScoped
class TransactionRepository : PanacheRepository<TransactionEntityModel>

