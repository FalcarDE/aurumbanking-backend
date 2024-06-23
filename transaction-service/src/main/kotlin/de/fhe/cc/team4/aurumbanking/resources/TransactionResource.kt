package de.fhe.cc.team4.aurumbanking.resources

import de.fhe.cc.team4.aurumbanking.domain.GetAllTransactionsByDepotIdUc
import de.fhe.cc.team4.aurumbanking.domain.InsertNewTransactionsUc
import de.fhe.cc.team4.aurumbanking.domain.TransactionDomainModel
import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import io.smallrye.mutiny.Uni
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import org.jboss.resteasy.reactive.RestResponse
import java.net.URI


@Path("/transactions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class TransactionResource {

    @Inject
    lateinit var getALlTransactionsByDepotIdUc: GetAllTransactionsByDepotIdUc

    @Inject
    lateinit var insertNewTransactionsUc : InsertNewTransactionsUc

    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    @WithSession
    fun test() = Uni.createFrom().item("testo, ok!")

    @GET
    @Path("/getAllTransactionsByDepotId/{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    @WithSession
    fun getAllTransactionsByDepotId(@PathParam("id") id: Long) = getALlTransactionsByDepotIdUc(id)
        .onItem().ifNotNull().transform { RestResponse.ok(it) }
        .onItem().ifNull().continueWith(RestResponse.notFound())

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @WithTransaction
    fun insert(transactionDomainModel: TransactionDomainModel): Uni<RestResponse<Void>> {
        return insertNewTransactionsUc.invoke(transactionDomainModel).map {
            RestResponse.created(URI("/depot/${it.id}"))
        }
    }
}