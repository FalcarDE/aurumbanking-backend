package de.fhe.cc.team4.aurumbanking.domain

import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class GetCustomerInformationByIdUc(var customerInformationRepository: CustomerInformationInterfaceRepository) {
    operator fun invoke(id: Long): Uni<CustomerInformation?> =
        customerInformationRepository.findCustomerInformationById(id)
}
