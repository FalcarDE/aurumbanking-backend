package de.fhe.cc.team4.aurumbanking.resources

import de.fhe.cc.team4.aurumbanking.domain.*
import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import io.smallrye.mutiny.Uni
import io.smallrye.reactive.messaging.annotations.Channel
import io.smallrye.reactive.messaging.annotations.Emitter
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
    lateinit var insertNewTransactionsUc : InsertNewTransactionsUc

    @Inject
    lateinit var getThreeLastestTransactionByDepotIdUc : GetThreeLastestTransactionByDepotIdUc

    @Inject
    lateinit var updateTransactionsByIdUc: UpdateTransactionsByIdUc

    @Inject
    lateinit var getTransactionById: GetTransactionById

    @Channel("update-depot-value")
    lateinit var transactionKafkaDtoEmitter: Emitter<TransactinKafkaDTO>

    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    @WithSession
    fun test() = Uni.createFrom().item("testo, ok!")

    @GET
    @Path("/getAllTransactionsByDepotId/{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    @WithSession
    fun getAllTransactionsByDepotId(@PathParam("id") id: Long): Uni<RestResponse<List<TransactionDomainModel>>> = getAllTransactionsByDepotIdUc(id)
        .onItem().ifNotNull().transform { RestResponse.ok(it) }
        .onItem().ifNull().continueWith(RestResponse.notFound())

    @GET
    @Path("/getTransactionsId/{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    @WithSession
    fun getTransactionsById(@PathParam("id") id: Long): Uni<RestResponse<TransactionDomainModel>> = getTransactionById(id)
        .onItem().ifNotNull().transform { RestResponse.ok(it) }
        .onItem().ifNull().continueWith(RestResponse.notFound())

    @GET
    @Path("/getThreeLastestTransactionByDepotId/{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    @WithSession
    fun getThreeLastestTransactionByDepotId(@PathParam("id") id: Long)
    = getThreeLastestTransactionByDepotIdUc(id)
        .onItem().ifNotNull().transform { RestResponse.ok(it) }
        .onItem().ifNull().continueWith(RestResponse.notFound())

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @WithTransaction
    fun insert(transactionDomainModel: TransactionDomainModel): Uni<RestResponse<Void>> {
        return insertNewTransactionsUc.invoke(transactionDomainModel).map {
            RestResponse.created(URI("/transactions/${it.id}"))
            transactionKafkaDtoEmitter.send(TransactinKafkaDTO(transactionDomainModel.id, transactionDomainModel.moneyValue))
            RestResponse.created(URI("/depot/${it.id}"))
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updateTransactionById")
    @WithTransaction
    fun updateTransactionById(transactionDomainModel: TransactionDomainModel): Uni<RestResponse<Void>> {
        return getTransactionById.invoke(transactionDomainModel.id)
            .flatMap { transaction ->
                if (transaction != null) {
                    updateTransactionsByIdUc.invoke(transactionDomainModel)
                        .map { updatedTransaction ->
                            RestResponse.created<Void>(URI("/transactions/${updatedTransaction.id}"))
                        }
                } else {
                    Uni.createFrom().failure<RestResponse<Void>>(IllegalStateException("Transaction not found"))
                }
            }
    }



}