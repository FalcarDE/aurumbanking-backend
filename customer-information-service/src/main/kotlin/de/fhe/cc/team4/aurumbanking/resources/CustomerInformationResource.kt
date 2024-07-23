package de.fhe.cc.team4.aurumbanking.resources

import de.fhe.cc.team4.aurumbanking.core.Application
import de.fhe.cc.team4.aurumbanking.domain.*
import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import io.smallrye.mutiny.Uni
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.Context
import jakarta.ws.rs.core.HttpHeaders
import jakarta.ws.rs.core.MediaType
import org.jboss.resteasy.reactive.RestResponse
import java.net.URI
import java.util.*


@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class CustomerInformationResource {

    @Inject
    lateinit var getCustomerInformationByIdUc: GetCustomerInformationByIdUc

    @Inject
    lateinit var addNewCustomerUc: AddNewCustomerUc

    @Inject
    lateinit var updateCustomerEmailUc: UpdateCustomerEmailUc

    @Inject
    lateinit var updateCustomerPasswordUc: UpdateCustomerPasswordUc

    @Inject
    lateinit var deleteCustomerInformationUc: DeleteCustomerInformationUc

    @GET
    @Path("/{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    @WithSession
    fun getCustomerInformationById(@PathParam("id") id: Long) = getCustomerInformationByIdUc(id)
        .onItem().ifNotNull().transform { RestResponse.ok(it) }
        .onItem().ifNull().continueWith(RestResponse.notFound())

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @WithTransaction
    fun insert(customerInformationDomainModel: CustomerInformationDomainModel): Uni<RestResponse<Void>> {
        return addNewCustomerUc(customerInformationDomainModel).map {
            RestResponse.created(URI("/customers/${it.id}"))
        }
    }

    @PUT
    @WithTransaction
    @Path("/updateCustomerEmailBy/{id}/{email}")
    fun updateCustomerEmailBy(@PathParam("id") id: Long, @PathParam("email") email: String) =
        updateCustomerEmailUc(id, email)
            .onItem().ifNotNull().transform { RestResponse.ok(it) }
            .onItem().ifNull().continueWith(RestResponse.notFound())

    @PUT
    @WithTransaction
    @Path("/updateCustomerPasswordBy/{id}/{password}")
    fun updateCustomerPasswordBy(@PathParam("id") id: Long, @PathParam("password") password: String) =
        updateCustomerPasswordUc(id, password)
            .onItem().ifNotNull().transform { RestResponse.ok(it) }
            .onItem().ifNull().continueWith(RestResponse.notFound())


    @DELETE
    @Path("/deleteCustomerInformationBy/{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    @WithTransaction
    fun deleteCustomerInformationBy(
        @PathParam("id") id: Long,
        @Context headers: HttpHeaders
    ): Uni<RestResponse<Long>>? {
        val authHeaders = headers.getRequestHeader(HttpHeaders.AUTHORIZATION)
        if (authHeaders.size == 1 && authHeaders[0].startsWith("Basic ")) {
            val plainAuthHeader = Base64.getDecoder().decode(authHeaders[0].substringAfter(" "))
            val authFields = String(plainAuthHeader).split(":")
            val user = Application.findAuthUser(authFields[0])
            user?.let {
                val passwordOk = (authFields[1] == it.password)
                if (passwordOk) {
                    return deleteCustomerInformationUc(id)
                        .onItem().ifNotNull().transform { RestResponse.ok(it) }
                        .onItem().ifNull().continueWith(RestResponse.notFound())
                }
            }
        }
        return Uni.createFrom().item(RestResponse.status(RestResponse.Status.UNAUTHORIZED))
    }
}