package de.fhe.cc.team4.aurumbanking.repositories

import de.fhe.cc.team4.aurumbanking.model.CustomerIdDTO
import de.fhe.cc.team4.aurumbanking.model.CustomerInformationEntityModel
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import io.quarkus.panache.common.Parameters
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class CustomerCredentialsRepository : PanacheRepository<CustomerInformationEntityModel> {
    fun checkCustomerCredentials(email: String, password: String): Uni<CustomerIdDTO> {
        return find("SELECT id FROM CustomerInformationEntityModel WHERE email = :email AND password = :password",
            Parameters.with("email", email).and("password", password))
            .project(CustomerIdDTO::class.java).firstResult()
    }
}