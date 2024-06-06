package de.fhe.cc.team4.aurumbanking.data.repostories

import de.fhe.cc.team4.aurumbanking.data.entities.DepotEntityModel
import de.fhe.cc.team4.aurumbanking.data.toDomain
import de.fhe.cc.team4.aurumbanking.data.toEntity
import de.fhe.cc.team4.aurumbanking.domain.DepositDomainModel
import de.fhe.cc.team4.aurumbanking.domain.DepotInterfaceRepository
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped


@ApplicationScoped
class DepotRepositoryImp : PanacheRepository<DepotEntityModel>, DepotInterfaceRepository {
    override fun findDepotByCustomerId(id: Long): Uni<DepositDomainModel> {
        TODO("not implemented yet")
    }

    override fun findDepotById(id: Long): Uni<DepositDomainModel?> {
        return this.findById(id).onItem().ifNotNull().transform{ it.toDomain()}
    }

    override fun persistNewDepotInformation(depot: DepositDomainModel): Uni<DepositDomainModel> {
        return this.persistAndFlush(depot.toEntity())
            .onItem().ifNotNull().transform{ it.toDomain() }
            .onItem().ifNull().fail()
    }

    override fun deleteDepotById(id: Long): Uni<Void> {
        TODO("Not yet implemented")
    }

    override fun deleteAllDepotInformation(): Uni<Void> {
        TODO("Not yet implemented")
    }
}

/**
 * Repository for managing customer information attributes in the database.
 */
@ApplicationScoped
class DepotRepository : PanacheRepository<DepotEntityModel>

