package de.fhe.cc.team4.aurumbanking.domain

import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class GetDepotByIdUc(var depotInterfaceRepository: DepotInterfaceRepository) {
    operator fun invoke(id: Long): Uni<Depot?> =
        depotInterfaceRepository.findDepotById(id)
}


@ApplicationScoped
class GetDepotByCustomerIdUc(var depotInterfaceRepository: DepotInterfaceRepository) {
    operator fun invoke(id: Long): Uni<Depot?> =
        depotInterfaceRepository.findDepotById(id)
}
