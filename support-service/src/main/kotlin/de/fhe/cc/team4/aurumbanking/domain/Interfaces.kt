package de.fhe.cc.team4.aurumbanking.domain

import io.smallrye.mutiny.Uni

interface SupportInterfaceRepository {

    fun findSupportRequestByCustomerId(id: Long): Uni<SupportDomainModel>
    fun findSupportRequestById(id: Long): Uni<SupportDomainModel?>

    fun persistNewSupportInformation(Support: SupportDomainModel): Uni<SupportDomainModel>

    fun deleteSupportRequestById(id: Long): Uni<Void>
    fun deleteAllSupportRequestByCustomerId(): Uni<Void>
}
