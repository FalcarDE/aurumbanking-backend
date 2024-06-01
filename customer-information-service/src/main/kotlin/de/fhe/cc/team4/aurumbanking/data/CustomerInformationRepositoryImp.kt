package de.fhe.cc.team4.aurumbanking.data

import de.fhe.cc.team4.aurumbanking.domain.CustomerInformation
import de.fhe.cc.team4.aurumbanking.domain.CustomerInformationInterfaceRepository
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped


@ApplicationScoped
class CustomerInformationRepositoryImpl : PanacheRepository<CustomerInformationEntityModel>, CustomerInformationInterfaceRepository {

    override fun findAllCustomerInformation(): Uni<List<CustomerInformation>> {
        TODO("Not yet implemented")
    }

    override fun findCustomerInformationById(id: Long): Uni<CustomerInformation?> {
        return this.findById(id).onItem().ifNotNull().transform{ it.toDomain()}
    }

    override fun persistCustomerInformation(customerInformation: CustomerInformation): Uni<CustomerInformation> {
        return this.persistAndFlush(customerInformation.toEntity())
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

