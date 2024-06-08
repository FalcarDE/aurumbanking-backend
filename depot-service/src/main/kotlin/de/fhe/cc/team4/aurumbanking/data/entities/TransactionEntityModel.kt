package de.fhe.cc.team4.aurumbanking.data.entities

import jakarta.persistence.*
import lombok.Data

@Data
@Entity
@Table(name = "Transaction", schema = "public")
class TransactionEntityModel() {

    @Id
    @GeneratedValue
    var id: Long = 0

    @ManyToOne
    @JoinColumn(name = "depot_id", nullable = false)
    lateinit var depot: DepotEntityModel

    @Enumerated(EnumType.STRING)
    @Column(length = 2500)
    lateinit var transactionClassification: TransactionClassification


}