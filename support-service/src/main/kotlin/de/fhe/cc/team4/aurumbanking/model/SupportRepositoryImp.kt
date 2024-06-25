package de.fhe.cc.team4.aurumbanking.model

import de.fhe.cc.team4.aurumbanking.domain.SupportDomainModel
import de.fhe.cc.team4.aurumbanking.domain.SupportInterfaceRepository
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped


@ApplicationScoped
class SupportRepositoryImp : PanacheRepository<SupportEntityModel>, SupportInterfaceRepository {





    override fun findSupportRequestById(id: Long): Uni<SupportDomainModel?> {
        return this.findById(id).onItem().ifNotNull().transform{ it.toDomain()}
    }

    override fun persistNewSupportInformation(Support: SupportDomainModel): Uni<SupportDomainModel> {
        return this.persistAndFlush(Support.toEntity())
            .onItem().ifNotNull().transform{ it.toDomain() }
            .onItem().ifNull().fail()
    }

    override fun deleteSupportRequestById(id: Long): Uni<Void> {
        TODO("Not yet implemented")
    }

    override fun deleteAllSupportRequestByCustomerId(customerId: Long): Uni<Long> =
        this.delete("delete from SupportEntityModel p where p.customerId = ?1", customerId)


    override fun getAllRequestsByType(type: String): Uni<List<SupportDomainModel>> {
        return this.find("type", type).list<SupportEntityModel>()
            .onItem().transform { entities -> entities.map { it.toDomain() } }
    }

    override fun findSupportRequestByCustomerId(customerId: Long): Uni<List<SupportDomainModel>> {
        return this.find("customerId", customerId).list<SupportEntityModel>()
            .onItem().transform { entities -> entities.map { it.toDomain() } }

    }
}

/**
 * Repository for managing customer information attributes in the database.
 */
@ApplicationScoped
class SupportRepository : PanacheRepository<SupportEntityModel>

