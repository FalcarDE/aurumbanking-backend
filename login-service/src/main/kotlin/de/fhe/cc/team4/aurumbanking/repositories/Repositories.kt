package de.fhe.cc.team4.aurumbanking.repositories

import de.fhe.cc.team4.aurumbanking.model.CustomerIdDTO
import de.fhe.cc.team4.aurumbanking.model.CustomerInformationEntityModel
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class CustomerCredentialsRepository : PanacheRepository<CustomerInformationEntityModel> {
    fun checkCustomerCredentials(email: String, password: String): Uni<CustomerIdDTO> {
        return find("SELECT id FROM CustomerInformationEntityModel WHERE email = ?1 AND password = ?2", email, password)
            .project(CustomerIdDTO::class.java).firstResult()
    }
}