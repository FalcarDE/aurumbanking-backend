package de.fhe.cc.team4.aurumbanking.domain

import de.fhe.cc.team4.aurumbanking.model.CustomerLoginDTO
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import jakarta.validation.constraints.Email

@ApplicationScoped
class GetCustomerInformationByIdUc(var customerInformationRepository: CustomerInformationInterfaceRepository) {
    operator fun invoke(id: Long): Uni<CustomerInformationDomainModel?> =
        customerInformationRepository.findCustomerInformationById(id)
}

@ApplicationScoped
class AddNewCustomerUc(var customerInformationRepository: CustomerInformationInterfaceRepository) {
    operator fun invoke(customerInformationDomainModel: CustomerInformationDomainModel): Uni<CustomerInformationDomainModel> =
        customerInformationRepository.persistCustomerInformation(customerInformationDomainModel)
}

@ApplicationScoped
class UpdateCustomerEmailUc(var customerInformationRepository: CustomerInformationInterfaceRepository) {
    operator fun invoke(id: Long , email: String): Uni<CustomerLoginDTO> =
        customerInformationRepository.updateCustomerEmailById(id, email)
}

@ApplicationScoped
class UpdateCustomerPasswordUc(var customerInformationRepository: CustomerInformationInterfaceRepository) {
    operator fun invoke(id: Long , password : String): Uni<CustomerLoginDTO> =
        customerInformationRepository.updateCustomerPasswordById(id, password)
}


@ApplicationScoped
class DeleteCustomerInformationUc(var customerInformationRepository: CustomerInformationInterfaceRepository) {
    operator fun invoke(id: Long): Uni<Long> =
        customerInformationRepository.deleteCustomerInformationById(id)
}

