package de.fhe.cc.team4.aurumbanking.model

import de.fhe.cc.team4.aurumbanking.domain.CustomerInformationDomainModel
import de.fhe.cc.team4.aurumbanking.domain.CustomerInformationInterfaceRepository
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import io.quarkus.panache.common.Parameters
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

/**
 * Repository class for managing customer information entities.
 *
 * This class provides the necessary operations for querying and manipulating customer information entities in the database.
 */
@ApplicationScoped
class CustomerInformationRepositoryImpl : PanacheRepository<CustomerInformationEntityModel>,
    CustomerInformationInterfaceRepository {


    override fun findCustomerInformationById(id: Long): Uni<CustomerInformationDomainModel?> {
        return this.findById(id).onItem().ifNotNull().transform { it.toDomain() }
    }

    fun findCustomerLoginInformationById(id: Long): Uni<CustomerLoginDTO> {
        return find("select email, password from CustomerInformationEntityModel c where c.id = ?1", id)
            .project(CustomerLoginDTO::class.java).firstResult()
    }

    override fun persistCustomerInformation(customerInformationDomainModel: CustomerInformationDomainModel): Uni<CustomerInformationDomainModel> {
        return this.persistAndFlush(customerInformationDomainModel.toEntity())
            .onItem().ifNotNull().transform { it.toDomain() }
            .onItem().ifNull().fail()
    }

    override fun updateCustomerEmailById(id: Long, email: String): Uni<CustomerLoginDTO> {
        return this.update(
            "update CustomerInformationEntityModel set email = :email where id = :id",
            Parameters.with("id", id).and("email", email)
        ).flatMap {
            findCustomerLoginInformationById(id)
        }
    }


    override fun updateCustomerPasswordById(id: Long, password: String): Uni<CustomerLoginDTO> {
        return this.update(
            "update CustomerInformationEntityModel set password = :password where id = :id",
            Parameters.with("id", id).and("password", password)
        ).flatMap {
            findCustomerLoginInformationById(id)
        }
    }

    override fun deleteCustomerInformationById(id: Long): Uni<Long> {
        return this.delete("delete from CustomerInformationEntityModel c where c.id = ?1", id)
    }

}

/**
 * Repository for managing customer information attributes in the database.
 */
@ApplicationScoped
class CustomerInformationRepository : PanacheRepository<CustomerInformationEntityModel>

