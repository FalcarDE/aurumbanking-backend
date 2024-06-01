package de.fhe.cc.team4.aurumbanking.domain

import io.smallrye.mutiny.Uni

interface CustomerInformationInterfaceRepository {

    fun findAllCustomerInformation(): Uni<List<CustomerInformation>>
    fun findCustomerInformationById(id: Long): Uni<CustomerInformation?>

    fun persistCustomerInformation(customerInformation: CustomerInformation): Uni<CustomerInformation>

    fun deleteCustomerInformationById(id: Long): Uni<Void>
    fun deleteAllCustomerInformation(): Uni<Void>
}