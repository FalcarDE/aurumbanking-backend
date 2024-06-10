package de.fhe.cc.team4.aurumbanking.model.entities

import jakarta.persistence.*
import lombok.Data
import java.math.BigDecimal

@Data
@Entity
@Table(name = "Depot", schema = "public")
class DepotEntityModel {

    @Id
    @GeneratedValue
    @Column(name = "depot_id", nullable = false)
    var id: Long = 0

    @OneToMany(mappedBy = "depot", fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    var transactions: MutableList<TransactionEntityModel> = mutableListOf()


    @Column(length = 2500)
    lateinit var currencyArea: String
    lateinit var depositAmount: BigDecimal
    lateinit var fallbackDepositAmount: BigDecimal
}

@JvmRecord
data class DepotDTO(val depositAmount: BigDecimal )


