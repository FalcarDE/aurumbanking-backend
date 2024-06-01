package de.fhe.cc.team4.aurumbanking.resources

import de.fhe.cc.team4.aurumbanking.core.DatabaseInitBean
import de.fhe.cc.team4.aurumbanking.domain.CustomerInformation
import de.fhe.cc.team4.aurumbanking.domain.CustomerInformationInterfaceRepository
import de.fhe.cc.team4.aurumbanking.domain.GetCustomerInformationByIdUc
import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import io.smallrye.mutiny.Uni
import jakarta.enterprise.inject.Default
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import org.jboss.resteasy.reactive.RestResponse
import java.net.URI


@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class CustomerInformationResource {


    /**
     * Represents the bean responsible for initializing the database with customer information.
     */
    @Inject
    lateinit var initDatabase: DatabaseInitBean

    /**
     * Represents a repository for managing customer information.
     */
    @Inject
    @Default
    lateinit var customerInformationRepo: CustomerInformationInterfaceRepository

    @Inject
    lateinit var getCustomerInformationByIdUc: GetCustomerInformationByIdUc


    @GET
    @Path("/{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    @WithSession
    fun getCustomerInformationById(@PathParam("id") id: Long) = getCustomerInformationByIdUc(id)
        .onItem().ifNotNull().transform { RestResponse.ok(it) }
        .onItem().ifNull().continueWith( RestResponse.notFound())


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @WithTransaction
    fun insert(customerInformation: CustomerInformation): Uni<RestResponse<Void>> {
        return customerInformationRepo.persistCustomerInformation(customerInformation).map {
            RestResponse.created(URI("/customers/${it.id}"))
        }
    }

    /*
    @PUT
    @Path("/{id}")
    fun update(@PathParam("id") id: Long, customer: CustomerInformationEntityModel): Response {
        val existingCustomer = service.findById(id)
        return if (existingCustomer != null) {
            val updatedCustomer = service.update(customer)
            Response.ok(updatedCustomer).build()
        } else {
            Response.status(Response.Status.NOT_FOUND).build()
        }
    }
     */


}