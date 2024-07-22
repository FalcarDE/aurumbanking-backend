package de.fhe.cc.team4.aurumbanking.domain

import de.fhe.cc.team4.aurumbanking.model.entities.DepotDTO
import de.fhe.cc.team4.aurumbanking.model.entities.FallbackDepositAmountDTO
import io.smallrye.mutiny.Uni
import java.math.BigDecimal

interface DepotInterfaceRepository {

    fun findDepotByCustomerId(id: Long): Uni<DepositDomainModel>

    fun findDepotById(id: Long): Uni<DepositDomainModel?>

    fun findCurrentDepotValueById(id: Long): Uni<DepotDTO>

    fun findCurrentDepotFallBackValueById(id: Long): Uni<FallbackDepositAmountDTO>

    fun persistNewDepotInformation(depotDomainModel: DepositDomainModel): Uni<DepositDomainModel>

    fun updateDepositValueByDepotId(id: Long, value: BigDecimal): Uni<DepotDTO>

    fun updateFallbackDepositAmount(id: Long): Uni<FallbackDepositAmountDTO>

    fun deleteDepotById(id: Long): Uni<Long>


}
