package de.fhe.cc.team4.aurumbanking.domain

import de.fhe.cc.team4.aurumbanking.model.CustomerLoginDTO
import io.smallrye.mutiny.Uni

interface CustomerInformationInterfaceRepository {

    fun findCustomerInformationById(id: Long): Uni<CustomerInformationDomainModel?>

    fun persistCustomerInformation(customerInformationDomainModel: CustomerInformationDomainModel): Uni<CustomerInformationDomainModel>

    fun updateCustomerEmailById(id: Long, email: String): Uni<CustomerLoginDTO>

    fun updateCustomerPasswordById(id: Long, password: String): Uni<CustomerLoginDTO>

    fun deleteCustomerInformationById(id: Long): Uni<Long>
}