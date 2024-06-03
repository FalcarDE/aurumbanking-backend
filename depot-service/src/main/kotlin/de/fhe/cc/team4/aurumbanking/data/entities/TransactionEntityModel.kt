package de.fhe.cc.team4.aurumbanking.data.entities

import jakarta.persistence.*
import lombok.Data
import java.math.BigDecimal
import java.util.*

@Data
@Entity
@Table(name = "Transaction", schema = "public")
class TransactionEntityModel() {

    @Id
    @GeneratedValue
    var id: Long = 0

    @ManyToOne
    @JoinColumn(name = "depot_id")
    lateinit var depotId: DepotEntityModel

    @Column(length = 2500)
    lateinit var currencyArea: Date
    lateinit var depositAmount: BigDecimal
    lateinit var fallbackDepositAmount: Date

}