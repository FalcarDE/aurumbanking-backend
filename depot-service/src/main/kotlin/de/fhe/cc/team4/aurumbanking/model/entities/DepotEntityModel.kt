package de.fhe.cc.team4.aurumbanking.model.entities

import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.persistence.*
import lombok.Data
import java.math.BigDecimal

@Data
@Entity
@Table(name = "Depot", schema = "public")
//@NamedQuery(name = "Deposit.findCurrentDepotValueById", query = "select depositAmount from DepotEntityModel p where p.id = :id")
//@NamedQuery(name = "Deposit.findCurrentDepotValueById", query = "UPDATE DepotEntityModel p SET depositAmount = :moneyvalue where p.id = :id")
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

@RegisterForReflection
data class DepotDTO(val depositAmount: BigDecimal )


