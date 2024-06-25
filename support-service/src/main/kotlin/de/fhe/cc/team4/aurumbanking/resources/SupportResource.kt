package de.fhe.cc.team4.aurumbanking.resources

import de.fhe.cc.team4.aurumbanking.domain.*
import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import io.smallrye.mutiny.Uni
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import org.jboss.resteasy.reactive.RestResponse
import java.net.URI


@Path("/support")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class SupportResource {

    @Inject
    lateinit var supportRequestByIdUc: GetSupportRequestById

    @Inject
    lateinit var getSupportRequestByCustomerIdUc: GetSupportRequestByCustomerId

    @Inject
    lateinit var insertNewSupportRequestUc: InsertNewSupportRequest

    @Inject
    lateinit var getSupportRequestsByTypeUc: GetAllSupportRequestsByType


    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    @WithSession
    fun test() = Uni.createFrom().item("testo")

    @GET
    @Path("/{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    @WithSession
    fun getSupportRequestById(@PathParam("id") id: Long) = supportRequestByIdUc(id)
        .onItem().ifNotNull().transform { RestResponse.ok(it) }
        .onItem().ifNull().continueWith(RestResponse.notFound())


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @WithTransaction
    fun insertNewSupportRequest(supportDomainModel: SupportDomainModel): Uni<RestResponse<Void>> {
        return insertNewSupportRequestUc(supportDomainModel).map {
            RestResponse.created(URI("/support/${it.id}"))
        }
    }

    @GET
    @Path("/get-all-support-reqeuests-by-type/{requestType}")
    @Produces(MediaType.APPLICATION_JSON)
    @WithSession
    fun getAllSupportRequestsByType(@PathParam("requestType") requestType: String): Uni<RestResponse<List<SupportDomainModel>>> {
        return getSupportRequestsByTypeUc(requestType)
            .onItem().transform { RestResponse.ok(it) }
            .onFailure().recoverWithItem(RestResponse.serverError())
    }

}