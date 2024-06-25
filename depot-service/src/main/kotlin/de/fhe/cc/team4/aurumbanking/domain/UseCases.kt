package de.fhe.cc.team4.aurumbanking.domain

import de.fhe.cc.team4.aurumbanking.model.entities.DepotDTO
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import java.math.BigDecimal

@ApplicationScoped
class GetDepotByIdUc(var depotInterfaceRepository: DepotInterfaceRepository) {
    operator fun invoke(id: Long): Uni<DepositDomainModel?> =
        depotInterfaceRepository.findDepotById(id)
}


@ApplicationScoped
class GetCurrentDepotByCustomerIdUc(var depotInterfaceRepository: DepotInterfaceRepository) {
    operator fun invoke(id: Long): Uni<DepotDTO> =
        depotInterfaceRepository.findCurrentDepotValueById(id)
}

@ApplicationScoped
class InsertNewDepotUc(var depotInterfaceRepository: DepotInterfaceRepository) {
    operator fun invoke(depositDomainModel : DepositDomainModel): Uni<DepositDomainModel> =
        depotInterfaceRepository.persistNewDepotInformation(depositDomainModel)
}


@ApplicationScoped
class UpdateDepositValueByIdUc(var depotInterfaceRepository: DepotInterfaceRepository) {
    operator fun invoke(id: Long, depositValue : BigDecimal ): Uni<DepotDTO> =
        depotInterfaceRepository.updateDepositValueByDepot(id, depositValue)
}


@ApplicationScoped
class DeleteDepotById(var depotInterfaceRepository: DepotInterfaceRepository) {
    operator fun invoke(id: Long ): Uni<Long> =
        depotInterfaceRepository.deleteDepotById(id)
}

