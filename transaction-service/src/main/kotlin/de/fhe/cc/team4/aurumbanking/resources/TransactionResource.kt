package de.fhe.cc.team4.aurumbanking.resources

import de.fhe.cc.team4.aurumbanking.data.entities.TransactionKafkaDTO
import de.fhe.cc.team4.aurumbanking.domain.*
import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import io.smallrye.mutiny.Uni
import org.eclipse.microprofile.reactive.messaging.Channel
import org.eclipse.microprofile.reactive.messaging.Emitter
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
    lateinit var getAllTransactionsByDepotIdUc: GetAllTransactionsByDepotIdUc

    @Inject
    lateinit var insertNewTransactionsUc: InsertNewTransactionsUc

    @Inject
    lateinit var getThreeLatestTransactionByDepotIdUc: GetThreeLatestTransactionByDepotIdUc

    @Inject
    lateinit var updateTransactionsByIdUc: UpdateTransactionsByIdUc

    @Inject
    lateinit var getTransactionByIdUc: GetTransactionsByIdUc

    @Inject
    lateinit var deleteTransactionByIdUc: DeleteTransactionByIdUc

    @Channel("update-depot-value")
    lateinit var transactionKafkaDtoEmitter: Emitter<TransactionKafkaDTO>

    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    @WithSession
    fun test() = Uni.createFrom().item("testo, ok!")

    @GET
    @Path("/getAllTransactionsByDepotId/{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    @WithSession
    fun getAllTransactionsByDepotId(@PathParam("id") id: Long): Uni<RestResponse<List<TransactionDomainModel>>> =
        getAllTransactionsByDepotIdUc(id)
            .onItem().ifNotNull().transform { RestResponse.ok(it) }
            .onItem().ifNull().continueWith(RestResponse.notFound())

    @GET
    @Path("/getTransactionById/{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    @WithSession
    fun getTransactionsById(@PathParam("id") id: Long): Uni<RestResponse<TransactionDomainModel>> =
        getTransactionByIdUc(id)
            .onItem().ifNotNull().transform { RestResponse.ok(it) }
            .onItem().ifNull().continueWith(RestResponse.notFound())

    @GET
    @Path("/getThreeLatestTransactionByDepotId/{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    @WithSession
    fun getThreeLastestTransactionByDepotId(@PathParam("id") id: Long) = getThreeLatestTransactionByDepotIdUc(id)
        .onItem().ifNotNull().transform { RestResponse.ok(it) }
        .onItem().ifNull().continueWith(RestResponse.notFound())

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @WithTransaction
    fun insert(transactionDomainModel: TransactionDomainModel): Uni<RestResponse<Void>> {
        return insertNewTransactionsUc.invoke(transactionDomainModel).map {
            transactionKafkaDtoEmitter.send(
                TransactionKafkaDTO(
                    transactionDomainModel.depotId,
                    transactionDomainModel.moneyValue,
                    transactionDomainModel.transactionType
                )
            )
            RestResponse.created(URI("/transactions/${it.id}"))
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updateTransactionById")
    @WithTransaction
    fun updateTransactionById(transactionDomainModel: TransactionDomainModel): Uni<RestResponse<Void>> {
        return getTransactionByIdUc.invoke(transactionDomainModel.id)
            .flatMap { transaction ->
                if (transaction != null) {
                    updateTransactionsByIdUc.invoke(transactionDomainModel)
                        .map { updatedTransaction ->
                            RestResponse.created(URI("/transactions/${updatedTransaction.id}"))
                        }
                } else {
                    Uni.createFrom().failure(IllegalStateException("Transaction not found"))
                }
            }
    }

    @DELETE
    @Path("/deleteTransactionBy/{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    @WithTransaction
    fun deleteById(@PathParam("id") id: Long): Uni<RestResponse<Void>> {
        return deleteTransactionByIdUc(id).replaceWith{ RestResponse.ok()}
    }

}