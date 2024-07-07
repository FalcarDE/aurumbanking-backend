package de.fhe.cc.team4.aurumbanking.domain

import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped




@ApplicationScoped
class GetSupportRequestById(var supportInterfaceRepository: SupportInterfaceRepository) {
    operator fun invoke(id: Long): Uni<SupportDomainModel?> =
        supportInterfaceRepository.findSupportRequestById(id)
}


@ApplicationScoped
class InsertNewSupportRequest(var supportInterfaceRepository: SupportInterfaceRepository) {
    operator fun invoke(supportDomainModel: SupportDomainModel): Uni<SupportDomainModel> =
        supportInterfaceRepository.persistNewSupportInformation(supportDomainModel)
}

@ApplicationScoped
class GetAllSupportRequestsByType(val supportInterfaceRepository: SupportInterfaceRepository) {
    operator fun invoke(type: String): Uni<List<SupportDomainModel>> =
        supportInterfaceRepository.getAllRequestsByType(type)
}

@ApplicationScoped
class GetSupportRequestByCustomerId(var supportInterfaceRepository: SupportInterfaceRepository) {
    operator fun invoke(customerId: Long): Uni<List<SupportDomainModel>> =
        supportInterfaceRepository.findSupportRequestByCustomerId(customerId)
}

@ApplicationScoped
class DeleteRequestByCustomerIdUc(var depotInterfaceRepository: SupportInterfaceRepository) {
    operator fun invoke(customerId: Long ): Uni<Long> =
        depotInterfaceRepository.deleteAllSupportRequestByCustomerId(customerId)
}
// TODO: GET and POST für Supportfunktionen + Erweiterungen der Usecases