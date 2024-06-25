package de.fhe.cc.team4.aurumbanking.domain

import io.smallrye.mutiny.Uni

interface SupportInterfaceRepository {

    fun findSupportRequestByCustomerId(customerId: Long): Uni<List<SupportDomainModel>>
    fun findSupportRequestById(id: Long): Uni<SupportDomainModel?>

    fun persistNewSupportInformation(Support: SupportDomainModel): Uni<SupportDomainModel>

    fun deleteSupportRequestById(id: Long): Uni<Void>
    fun deleteAllSupportRequestByCustomerId(): Uni<Void>

    fun getAllRequestsByType(type: String): Uni<List<SupportDomainModel>>

}
