package de.fhe.cc.team4.aurumbanking.data

import de.fhe.cc.team4.aurumbanking.domain.CustomerInformationDomainModel
import de.fhe.cc.team4.aurumbanking.domain.CustomerInformationInterfaceRepository
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped


@ApplicationScoped
class CustomerInformationRepositoryImpl : PanacheRepository<CustomerInformationEntityModel>, CustomerInformationInterfaceRepository {

    override fun findAllCustomerInformation(): Uni<List<CustomerInformationDomainModel>> {
        TODO("Not yet implemented")
    }

    override fun findCustomerInformationById(id: Long): Uni<CustomerInformationDomainModel?> {
        return this.findById(id).onItem().ifNotNull().transform{ it.toDomain()}
    }

    override fun persistCustomerInformation(customerInformationDomainModel: CustomerInformationDomainModel): Uni<CustomerInformationDomainModel> {
        return this.persistAndFlush(customerInformationDomainModel.toEntity())
            .onItem().ifNotNull().transform{ it.toDomain() }
            .onItem().ifNull().fail()
    }

    override fun deleteCustomerInformationById(id: Long): Uni<Void> {
        TODO("Not yet implemented")
    }

    override fun deleteAllCustomerInformation(): Uni<Void> {
        TODO("Not yet implemented")
    }
}

/**
 * Repository for managing customer information attributes in the database.
 */
@ApplicationScoped
class CustomerInformationRepository : PanacheRepository<CustomerInformationEntityModel>

