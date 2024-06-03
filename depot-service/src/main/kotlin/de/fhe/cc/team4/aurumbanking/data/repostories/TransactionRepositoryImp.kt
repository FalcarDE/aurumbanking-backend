package de.fhe.cc.team4.aurumbanking.data.repostories

import de.fhe.cc.team4.aurumbanking.data.entities.TransactionEntityModel
import de.fhe.cc.team4.aurumbanking.domain.TransactionInterfaceRepository
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped


@ApplicationScoped
class TransactionRepositoryImp : PanacheRepository<TransactionEntityModel>, TransactionInterfaceRepository  {

}

/**
 * Repository for managing customer information attributes in the database.
 */
@ApplicationScoped
class TransactionRepository : PanacheRepository<TransactionEntityModel>

