package de.fhe.cc.team4.aurumbanking.model.entities

import de.fhe.cc.team4.aurumbanking.model.entities.core.TransactionClassification
import de.fhe.cc.team4.aurumbanking.model.entities.core.TransactionType
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
    @JoinColumn(name = "depot_id", nullable = false)
    lateinit var depot: DepotEntityModel


    @Column(length = 2500)
    lateinit var country : String
    lateinit var recipient: String
    lateinit var iban: String
    lateinit var bic: String
    lateinit var moneyValue : BigDecimal
    lateinit var purposeOfUse : String
    lateinit var dateTimeOfExecution: Date
    var standingOrder: Boolean? = null
    @Enumerated(EnumType.STRING)
    lateinit var transactionType: TransactionType
    @Enumerated(EnumType.STRING)
    lateinit var transactionClassification: TransactionClassification
    lateinit var dateTimeOfFirstExecution: Date
    lateinit var dateTimeOfLastExecution: Date
}