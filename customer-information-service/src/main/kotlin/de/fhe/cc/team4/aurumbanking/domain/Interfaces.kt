package de.fhe.cc.team4.aurumbanking.domain

import io.smallrye.mutiny.Uni

interface CustomerInformationInterfaceRepository {

    fun findAllCustomerInformation(): Uni<List<CustomerInformationDomainModel>>
    fun findCustomerInformationById(id: Long): Uni<CustomerInformationDomainModel?>

    fun persistCustomerInformation(customerInformationDomainModel: CustomerInformationDomainModel): Uni<CustomerInformationDomainModel>

    fun deleteCustomerInformationById(id: Long): Uni<Void>
    fun deleteAllCustomerInformation(): Uni<Void>
}