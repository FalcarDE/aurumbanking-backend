package de.fhe.cc.team4.aurumbanking.domain

import io.smallrye.mutiny.Uni

interface DepotInterfaceRepository {

    fun findDepotByCustomerId(id: Long): Uni<Depot>
    fun findDepotById(id: Long): Uni<Depot?>

    fun persistNewDepotInformation(depot: Depot): Uni<Depot>

    fun deleteDepotById(id: Long): Uni<Void>
    fun deleteAllDepotInformation(): Uni<Void>
}



interface TransactionInterfaceRepository {

    //TODO: add stuff
}