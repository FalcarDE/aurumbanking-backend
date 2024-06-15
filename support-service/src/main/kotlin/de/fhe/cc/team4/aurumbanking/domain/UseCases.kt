package de.fhe.cc.team4.aurumbanking.domain

import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class GetSupportRequestByCustomerId(var supportInterfaceRepository: SupportInterfaceRepository) {
    operator fun invoke(id: Long): Uni<SupportDomainModel> =
        supportInterfaceRepository.findSupportRequestByCustomerId(id)
}


@ApplicationScoped
class GetSupportRequestById(var supportInterfaceRepository: SupportInterfaceRepository) {
    operator fun invoke(id: Long): Uni<SupportDomainModel?> =
        supportInterfaceRepository.findSupportRequestById(id)
}


@ApplicationScoped
class InsertNewSupportRequest(var supportInterfaceRepository: SupportInterfaceRepository) {
    operator fun invoke(id: Long): Uni<SupportDomainModel?> =
        supportInterfaceRepository.findSupportRequestById(id)
}


// TODO: GET and POST für Supportfunktionen + Erweiterungen der Usecases