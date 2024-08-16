package de.fhe.cc.team4.aurumbanking.model.entities

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase
import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.persistence.*
import lombok.Data
import java.math.BigDecimal

@Data
@Entity
@Table(name = "Depot", schema = "public")
class DepotEntityModel : PanacheEntityBase() {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long = 0

    @Column(length = 2500)
    var customerId: Long = 0
    lateinit var currencyArea: String
    lateinit var depositAmount: BigDecimal
    lateinit var fallbackDepositAmount: BigDecimal
}

@RegisterForReflection
data class DepotDTO(val depositAmount: BigDecimal)

@RegisterForReflection
data class FallbackDepositAmountDTO(val fallbackDepositAmount: BigDecimal)


@RegisterForReflection
data class DepotDomainModelDTO(
    val id: Long,
    val customerId: Long,
    val currencyArea: String,
    val depositAmount: BigDecimal,
    val fallbackDepositAmount: BigDecimal
)





