package de.fhe.cc.team4.aurumbanking.model.repostories

import de.fhe.cc.team4.aurumbanking.model.entities.DepotDTO
import de.fhe.cc.team4.aurumbanking.model.entities.DepotEntityModel
import de.fhe.cc.team4.aurumbanking.model.toDomain
import de.fhe.cc.team4.aurumbanking.model.toEntity
import de.fhe.cc.team4.aurumbanking.domain.DepositDomainModel
import de.fhe.cc.team4.aurumbanking.domain.DepotInterfaceRepository
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import java.math.BigDecimal


@ApplicationScoped
class DepotRepositoryImp : PanacheRepository<DepotEntityModel>, DepotInterfaceRepository {
    override fun findDepotByCustomerId(id: Long): Uni<DepositDomainModel> {
        TODO("not implemented yet")
    }

    override fun findDepotById(id: Long): Uni<DepositDomainModel?> {
        return this.findById(id).onItem().ifNotNull().transform { it.toDomain() }
    }

    // TODO: Implemtierung DTO-Mapper + Usecase
    override fun findCurrentDepotValueById(id: Long): Uni<DepotDTO> {
        return find("select depositAmount from DepotEntityModel p where p.id = ?1", id)
            .project(DepotDTO::class.java).firstResult()
    }


    override fun persistNewDepotInformation(depotDomainModel: DepositDomainModel): Uni<DepositDomainModel> {
        return this.persistAndFlush(depotDomainModel.toEntity())
            .onItem().ifNotNull().transform { it.toDomain() }
            .onItem().ifNull().fail()
    }

    override fun updateDepositValueByDepot(id: Long, value: BigDecimal): Uni<DepotDTO> {
        val result = update("UPDATE DepotEntityModel SET depositAmount = ?1 where id = '1'", value)
        return findCurrentDepotValueById(id)
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

