package de.fhe.cc.team4.aurumbanking.resources

import de.fhe.cc.team4.aurumbanking.core.DatabaseInitBean
import de.fhe.cc.team4.aurumbanking.domain.*
import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import io.smallrye.mutiny.Uni
import jakarta.enterprise.inject.Default
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import org.jboss.resteasy.reactive.RestResponse
import java.net.URI


@Path("/support")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class SupportResource {


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
    lateinit var supportInterfaceRepository: SupportInterfaceRepository

    @Inject
    lateinit var supportRequestByIdUc: GetSupportRequestById

    @Inject
    lateinit var supportRequestByCustomerId: GetSupportRequestByCustomerId

    @Inject
    lateinit var insertNewSupportRequest: InsertNewSupportRequest


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
        .onItem().ifNull().continueWith( RestResponse.notFound())



    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @WithTransaction
    fun insertNewSupportRequest(supportDomainModel: SupportDomainModel): Uni<RestResponse<Void>> {
        return supportInterfaceRepository.persistNewSupportInformation(supportDomainModel).map {
            RestResponse.created(URI("/support/${it.id}"))
        }
    }

    // TODO: GET and POST für Supportfunktionen + Erweiterungen der Usecases



}