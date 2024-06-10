package de.fhe.cc.team4.aurumbanking.domain

import de.fhe.cc.team4.aurumbanking.model.entities.DepotDTO
import io.smallrye.mutiny.Uni

interface DepotInterfaceRepository {

    fun findDepotByCustomerId(id: Long): Uni<DepositDomainModel>
    fun findDepotById(id: Long): Uni<DepositDomainModel?>
    //fun findCurrentDepotValueById(id: Long): Uni<DepotDTO>
    fun findCurrentDepotValueById(id: Long):  Uni<DepotDTO>


    fun persistNewDepotInformation(depotDomainModel: DepositDomainModel): Uni<DepositDomainModel>

    fun deleteDepotById(id: Long): Uni<Void>
    fun deleteAllDepotInformation(): Uni<Void>

}
