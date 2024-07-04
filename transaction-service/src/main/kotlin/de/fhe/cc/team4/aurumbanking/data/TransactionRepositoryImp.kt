package de.fhe.cc.team4.aurumbanking.data.repostories

import de.fhe.cc.team4.aurumbanking.core.TransactionClassification
import de.fhe.cc.team4.aurumbanking.core.TransactionType
import de.fhe.cc.team4.aurumbanking.data.entities.TransactionEntityModel
import de.fhe.cc.team4.aurumbanking.data.toDomain
import de.fhe.cc.team4.aurumbanking.data.toEntity
import de.fhe.cc.team4.aurumbanking.data.transactionListToDomain
import de.fhe.cc.team4.aurumbanking.domain.TransactionDomainModel
import de.fhe.cc.team4.aurumbanking.domain.TransactionInterfaceRepository
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase
import io.quarkus.panache.common.Parameters
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

    override fun updateTransactionById(transactionDomainModel: TransactionDomainModel): Uni<TransactionDomainModel> {
        return this.update(
            """
             UPDATE TransactionEntityModel
             SET
                 depotId = :depotId,
                 country = :country,
                 recipient = :recipient,
                 iban = :iban,
                 bic = :bic,
                 moneyValue = :moneyValue,
                 purposeOfUse = :purposeOfUse,
                 dateTimeOfExecution = :dateTimeOfExecution,
                 standingOrder = :standingOrder,
                 transactionType = :transactionType,
                 transactionClassification = :transactionClassification,
                 dateTimeOfFirstExecution = :dateTimeOfFirstExecution,
                 dateTimeOfLastExecution = :dateTimeOfLastExecution
             WHERE id = :id
             """,
            Parameters.with("id", transactionDomainModel.id)
                .and("depotId", transactionDomainModel.depotId)
                .and("country", transactionDomainModel.country)
                .and("recipient", transactionDomainModel.recipient)
                .and("iban", transactionDomainModel.iban)
                .and("bic", transactionDomainModel.bic)
                .and("moneyValue", transactionDomainModel.moneyValue)
                .and("purposeOfUse", transactionDomainModel.purposeOfUse)
                .and("dateTimeOfExecution", transactionDomainModel.dateTimeOfExecution)
                .and("standingOrder", transactionDomainModel.standingOrder)
                .and("transactionType", classifyTransactionType(transactionDomainModel.transactionType))
                .and("transactionClassification", classifyTransaction(transactionDomainModel.transactionClassification))
                .and("dateTimeOfFirstExecution", transactionDomainModel.dateTimeOfFirstExecution)
                .and("dateTimeOfLastExecution", transactionDomainModel.dateTimeOfLastExecution)
        ).flatMap {
            getTransactionById(transactionDomainModel.id)
        }

    }

    override fun getTransactionById(id: Long): Uni<TransactionDomainModel> {
        return this.findById(id).onItem().ifNotNull().transform { it.toDomain() }
    }

    private fun classifyTransaction(name: String): TransactionClassification? {
        for (classification in TransactionClassification.entries) {
            if (classification.displayName == name) {
                return classification
            }
        }
        return null
    }

    private fun classifyTransactionType(name: String): TransactionType? {
        for (type in TransactionType.entries) {
            if (type.displayName == name) {
                return type
            }
        }
        return null
    }
}


@ApplicationScoped
class TransactionRepository : PanacheRepository<TransactionEntityModel>

