package de.fhe.cc.team4.aurumbanking.resources

import de.fhe.cc.team4.aurumbanking.data.TransactionRepository
import de.fhe.cc.team4.aurumbanking.model.TransactionEntityModel
import io.quarkus.hibernate.reactive.rest.data.panache.PanacheRepositoryResource
import io.quarkus.rest.data.panache.ResourceProperties
import jakarta.enterprise.inject.spi.CDI
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType


@ResourceProperties(hal = true, path = "transaction")
interface TransactionResource : PanacheRepositoryResource<TransactionRepository, TransactionEntityModel, Long> {

    @GET
    @Path("/findAllTransactionByDepotId/{depotId}")
    @Produces(MediaType.APPLICATION_JSON)
    fun findTransactionByDepotId(
        @QueryParam("depotId") @DefaultValue("1") depotId: Long
    ) = CDI.current().select(TransactionRepository::class.java).get()
        .findAllTransactionByDepotId(depotId)

    @GET
    @Path("/findTransactionById/{transactionId}")
    @Produces(MediaType.APPLICATION_JSON)
    fun findTransactionById(
        @QueryParam("transactionId") transactionId: Long
    ) = CDI.current().select(TransactionRepository::class.java).get()
        .findTransactionById(transactionId)


}