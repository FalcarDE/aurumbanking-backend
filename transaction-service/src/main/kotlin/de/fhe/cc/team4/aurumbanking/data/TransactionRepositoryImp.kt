package de.fhe.cc.team4.aurumbanking.data.repostories

import de.fhe.cc.team4.aurumbanking.data.entities.TransactionEntityModel
import de.fhe.cc.team4.aurumbanking.data.toDomain
import de.fhe.cc.team4.aurumbanking.data.toEntity
import de.fhe.cc.team4.aurumbanking.data.transactionListToDomain
import de.fhe.cc.team4.aurumbanking.domain.TransactionDomainModel
import de.fhe.cc.team4.aurumbanking.domain.TransactionInterfaceRepository
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped


@ApplicationScoped
class TransactionRepositoryImp : PanacheRepository<TransactionEntityModel>, TransactionInterfaceRepository {

    override fun findAllTransactionByDepotId(id: Long): Uni<List<TransactionDomainModel>> {
        return list("depotId", id)
            .onItem().transform { transactionListToDomain(it) }
    }

    override fun insertNewTransaction(transactionDomainModel: TransactionDomainModel): Uni<TransactionDomainModel> {
        return this.persistAndFlush(transactionDomainModel.toEntity())
            .onItem().ifNotNull().transform { it.toDomain() }
            .onItem().ifNull().fail()
    }
}


@ApplicationScoped
class TransactionRepository : PanacheRepository<TransactionEntityModel>

