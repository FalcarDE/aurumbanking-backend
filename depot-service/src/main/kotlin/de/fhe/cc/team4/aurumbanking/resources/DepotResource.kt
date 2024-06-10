package de.fhe.cc.team4.aurumbanking.resources

import de.fhe.cc.team4.aurumbanking.core.DatabaseInitBean
import de.fhe.cc.team4.aurumbanking.domain.DepositDomainModel
import de.fhe.cc.team4.aurumbanking.domain.DepotInterfaceRepository
import de.fhe.cc.team4.aurumbanking.domain.GetDepotByIdUc
import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import io.smallrye.mutiny.Uni
import jakarta.enterprise.inject.Default
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import org.jboss.resteasy.reactive.RestResponse
import java.net.URI


@Path("/depot")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class DepotResource {


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
    lateinit var depotInterfaceRepository: DepotInterfaceRepository

    @Inject
    lateinit var getDepotByIdUc: GetDepotByIdUc


    @GET
    @Path("/{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    @WithSession
    fun getCustomerInformationById(@PathParam("id") id: Long) = getDepotByIdUc(id)
        .onItem().ifNotNull().transform { RestResponse.ok(it) }
        .onItem().ifNull().continueWith( RestResponse.notFound())



    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @WithTransaction
    fun insert(depositDomainModel: DepositDomainModel): Uni<RestResponse<Void>> {
        return depotInterfaceRepository.persistNewDepotInformation(depositDomainModel).map {
            RestResponse.created(URI("/depot/${it.id}"))
        }
    }

    // TODO: GET and POST für Transaktionen + Erweiterungen der Usecases





}