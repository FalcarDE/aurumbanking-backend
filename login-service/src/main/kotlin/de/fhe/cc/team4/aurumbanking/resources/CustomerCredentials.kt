package de.fhe.cc.team4.aurumbanking.resources

import de.fhe.cc.team4.aurumbanking.model.CustomerInformationEntityModel
import de.fhe.cc.team4.aurumbanking.repositories.CustomerCredentialsRepository
import io.quarkus.hibernate.reactive.rest.data.panache.PanacheRepositoryResource
import io.quarkus.rest.data.panache.ResourceProperties
import io.smallrye.mutiny.Uni
import jakarta.enterprise.inject.spi.CDI
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response


@ResourceProperties(hal = true, path = "login")
interface CustomerCredentials : PanacheRepositoryResource<CustomerCredentialsRepository, CustomerInformationEntityModel, Long? > {
    @POST
    @Path("check-credentials/{email}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    fun checkCustomerCredentials(
        @PathParam("email") email: String, @PathParam("password") password: String): Uni<Response> {
        val credentialsRepository = CDI.current().select(CustomerCredentialsRepository::class.java).get()
        return credentialsRepository.checkCustomerCredentials(email, password)
            .map { customerIdDTO ->
                if (customerIdDTO != null) {
                    Response.ok(customerIdDTO).build()
                } else {
                    Response.status(Response.Status.NOT_FOUND).build()
                }
            }
    }

    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    fun exaple () : Uni<String>? {
        return Uni.createFrom().item("ok")
    }

}