package de.fhe.cc.team4.aurumbanking.resources

import de.fhe.cc.team4.aurumbanking.domain.*
import de.fhe.cc.team4.aurumbanking.model.entities.DepotDTO
import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import io.smallrye.mutiny.Uni
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import org.jboss.resteasy.reactive.RestResponse
import java.math.BigDecimal
import java.net.URI


@Path("/depot")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class DepotResource {
    @Inject
    lateinit var getDepotByIdUc: GetDepotByIdUc

    @Inject
    lateinit var getCurrentDepotValueByCustomerIdUc: GetCurrentDepotValueByCustomerIdUc

    @Inject
    lateinit var getDepotByCustomerIdUc: GetDepotByCustomerIdUc

    @Inject
    lateinit var insertNewDepotUc: InsertNewDepotUc

    @Inject
    lateinit var updateDepositValueByIdUc: UpdateDepositValueByIdUc

    @Inject
    lateinit var deleteDepotByIdUc: DeleteDepotByIdUc

    @GET
    @Path("/{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    @WithSession
    fun getDepotById(@PathParam("id") id: Long) = getDepotByIdUc(id)
        .onItem().ifNotNull().transform { RestResponse.ok(it) }
        .onItem().ifNull().continueWith(RestResponse.notFound())


    @GET
    @Path("/getDepotByCustomerId/{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    @WithSession
    fun getDepotByCustomerId(@PathParam("id") id: Long) = getDepotByCustomerIdUc(id)
        .onItem().ifNotNull().transform { RestResponse.ok(it) }
        .onItem().ifNull().continueWith(RestResponse.notFound())

    @GET
    @Path("/getCurrentDepotValueByCustomerId/{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    @WithSession
    fun getCurrentDepotByCustomerId(@PathParam("id") id: Long) = getCurrentDepotValueByCustomerIdUc(id)
        .onItem().ifNotNull().transform { RestResponse.ok(it) }
        .onItem().ifNull().continueWith(RestResponse.notFound())


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @WithTransaction
    fun insert(depositDomainModel: DepositDomainModel): Uni<RestResponse<Void>> {
        return insertNewDepotUc(depositDomainModel).map {
            RestResponse.created(URI("/depot/${it.id}"))
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/insertNewDepositValueById/{id:\\d+}/{value:\\d+([,\\.]\\d+)}")
    @WithTransaction
    fun insertNewDepositValueById(
        @PathParam("id") id: Long,
        @PathParam("value") value: BigDecimal
    ): Uni<RestResponse<DepotDTO>>? {
        return updateDepositValueByIdUc(id, value)
            .onItem().ifNotNull().transform { RestResponse.ok(it) }
            .onItem().ifNull().continueWith(RestResponse.notFound())
    }

    @DELETE
    @Path("/deleteDepotBy/{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    @WithTransaction
    fun deleteById(@PathParam("id") id: Long): Uni<RestResponse<Void>> {
        return deleteDepotByIdUc(id).replaceWith { RestResponse.ok() }
    }
}
