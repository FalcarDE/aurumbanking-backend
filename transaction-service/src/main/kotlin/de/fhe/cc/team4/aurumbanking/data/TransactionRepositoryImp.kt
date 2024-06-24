package de.fhe.cc.team4.aurumbanking.data.repostories

import de.fhe.cc.team4.aurumbanking.data.entities.TransactionEntityModel
import de.fhe.cc.team4.aurumbanking.data.toDomain
import de.fhe.cc.team4.aurumbanking.data.toEntity
import de.fhe.cc.team4.aurumbanking.data.transactionListToDomain
import de.fhe.cc.team4.aurumbanking.domain.TransactionDomainModel
import de.fhe.cc.team4.aurumbanking.domain.TransactionInterfaceRepository
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase
import io.quarkus.panache.common.Sort
import io.quarkus.panache.common.Sort.Direction
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped


@ApplicationScoped
class TransactionRepositoryImp : PanacheRepositoryBase<TransactionEntityModel, Long>, TransactionInterfaceRepository {

    override fun findAllTransactionByDepotId(id: Long): Uni<List<TransactionDomainModel>> {
        return list("depotId", id)
            .onItem().transform { transactionListToDomain(it) }
    }

    override fun insertNewTransaction(transactionDomainModel: TransactionDomainModel): Uni<TransactionDomainModel> {
        return this.persistAndFlush(transactionDomainModel.toEntity())
            .onItem().ifNotNull().transform { it.toDomain() }
            .onItem().ifNull().fail()
    }

    override fun getThreeLastestTransactionByDepotId(depotId: Long): Uni<List<TransactionDomainModel>> {
        val internalSortOrder = Direction.Descending
        val sortColumn = "dateTimeOfExecution"
        val internalSort = Sort.by(sortColumn, internalSortOrder)

        return list("depotId", internalSort, depotId)
            .onItem().transform { transactionList ->
                transactionList.take(3).map { it.toDomain() }
            }


    }
}


@ApplicationScoped
class TransactionRepository : PanacheRepository<TransactionEntityModel>

