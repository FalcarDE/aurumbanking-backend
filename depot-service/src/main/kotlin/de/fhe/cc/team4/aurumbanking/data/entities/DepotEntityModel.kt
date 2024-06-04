package de.fhe.cc.team4.aurumbanking.data.entities

import jakarta.persistence.*
import lombok.Data
import java.math.BigDecimal
import java.util.*

@Data
@Entity
@Table(name = "Depot", schema = "public")
class DepotEntityModel() {

    @Id
    @GeneratedValue
    var id: Long = 0

    @OneToMany
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    var transactions: MutableList<TransactionEntityModel> = mutableListOf()


    @Column(length = 2500)
    lateinit var currencyArea: String
    lateinit var depositAmount: BigDecimal
    lateinit var fallbackDepositAmount: BigDecimal
}