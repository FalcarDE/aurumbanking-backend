package de.fhe.cc.team4.aurumbanking.data.repostories

import de.fhe.cc.team4.aurumbanking.data.entities.SupportEntityModel
import de.fhe.cc.team4.aurumbanking.data.toDomain
import de.fhe.cc.team4.aurumbanking.data.toEntity
import de.fhe.cc.team4.aurumbanking.domain.SupportDomainModel
import de.fhe.cc.team4.aurumbanking.domain.SupportInterfaceRepository
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped


@ApplicationScoped
class SupportRepositoryImp : PanacheRepository<SupportEntityModel>, SupportInterfaceRepository {


    override fun findSupportRequestByCustomerId(id: Long): Uni<SupportDomainModel> {
        TODO("Not yet implemented")
    }

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

    override fun deleteAllSupportRequestByCustomerId(): Uni<Void> {
        TODO("Not yet implemented")
    }
}

/**
 * Repository for managing customer information attributes in the database.
 */
@ApplicationScoped
class SupportRepository : PanacheRepository<SupportEntityModel>

