package de.fhe.cc.team4.aurumbanking.resources

import de.fhe.cc.team4.aurumbanking.domain.GetAllTransactionsByDepotIdUc
import de.fhe.cc.team4.aurumbanking.domain.TransactionInterfaceRepository
import io.quarkus.hibernate.reactive.panache.common.WithSession
import jakarta.enterprise.inject.Default
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import org.jboss.resteasy.reactive.RestResponse


@Path("/transactions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class TransactionResource {
    /**
     * Represents a repository for managing transactions.
     */
    @Inject
    @Default
    lateinit var transactionInterfaceRepository: TransactionInterfaceRepository

    @Inject
    lateinit var getALlTransactionsByDepotIdUc: GetAllTransactionsByDepotIdUc

    @GET
    @Path("/getAllTransactionsByDepotId/{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    @WithSession
    fun getAllTransactionsByDepotId(@PathParam("id") id: Long) = getALlTransactionsByDepotIdUc(id)
        .onItem().ifNotNull().transform { RestResponse.ok(it) }
        .onItem().ifNull().continueWith( RestResponse.notFound())



    //@POST
    //@Consumes(MediaType.APPLICATION_JSON)
    //@WithTransaction
    //fun insert(transactionDomainModel: TransactionDomainModel): Uni<RestResponse<Void>> {
    //    return this.transactionInterfaceRepository.insertNewTransaction(transactionDomainModel).map {
    //        RestResponse.created(URI("/depot/${it.id}"))
    //    }
    //}

    // TODO: GET and POST für Transaktionen + Erweiterungen der Usecases





}