package de.fhe.cc.team4.aurumbanking.repositories

import de.fhe.cc.team4.aurumbanking.model.CustomerCredentials
import de.fhe.cc.team4.aurumbanking.model.CustomerInformationEntityModel
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class CustomerCredentialsRepository : PanacheRepository<CustomerInformationEntityModel> {
    fun checkCustomerCredentials(id: Long,  email: String, password: String): Uni<String> {

        var check = find("SELECT email, password FROM CustomerInformationEntityModel WHERE id = ?1", id )
            .project(CustomerCredentials::class.java)

        if (check != null) {
            return Uni.createFrom().item("ok")
        }

        return Uni.createFrom().item("not ok")
    }

}