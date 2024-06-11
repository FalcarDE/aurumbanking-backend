package de.fhe.cc.team4.aurumbanking.data.repostories

import de.fhe.cc.team4.aurumbanking.data.entities.TransactionEntityModel
import de.fhe.cc.team4.aurumbanking.data.toDomain
import de.fhe.cc.team4.aurumbanking.data.toEntity
import de.fhe.cc.team4.aurumbanking.data.transactionEntityListToDomain
import de.fhe.cc.team4.aurumbanking.domain.TransactionDomainModel
import de.fhe.cc.team4.aurumbanking.domain.TransactionInterfaceRepository
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import io.quarkus.panache.common.Parameters
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped


@ApplicationScoped
class TransactionRepositoryImp : PanacheRepository<TransactionEntityModel>, TransactionInterfaceRepository {
    override fun findAllTransactionByCustomerId(id: Long): Uni<List<TransactionDomainModel>> =
        list("select * from transactions as t where t.customer_id = :id", Parameters.with("cid", "$id"))
            .onItem().transform { transactionEntityListToDomain(it) }


    override fun findAllTransactionByDepotId(id: Long): Uni<List<TransactionDomainModel>> {
        return list("depot.id", id)
            .onItem().transform { transactionEntityListToDomain(it) }
    }

    //override fun insertNewTransactionByDepotId(
    //    id: Long,
    //    transactionDomainModel: TransactionDomainModel
    //): Uni<TransactionDomainModel> =
    //    this.persistAndFlush(transactionDomainModel.toEntity())
    //        .onItem().ifNotNull().transform { it.toDomain() }
    //        .onItem().ifNull().fail()

    //override fun insertNewTransaction(
    //    transactionDomainModel: TransactionDomainModel
    //): Uni<TransactionDomainModel> =
    //    this.persistAndFlush(transactionDomainModel.toEntity())
    //        .onItem().ifNotNull().transform { it.toDomain() }
    //        .onItem().ifNull().fail()
}

/**
 * Repository for managing transactions attributes in the database.
 */
@ApplicationScoped
class TransactionRepository : PanacheRepository<TransactionEntityModel>

