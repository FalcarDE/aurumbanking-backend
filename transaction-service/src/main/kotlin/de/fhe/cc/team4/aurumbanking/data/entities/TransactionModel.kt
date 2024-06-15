package de.fhe.cc.team4.aurumbanking.data.entities

import de.fhe.cc.team4.aurumbanking.core.TransactionClassification
import de.fhe.cc.team4.aurumbanking.core.TransactionType
import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.persistence.*
import java.math.BigDecimal
import java.util.*

@Entity
@Table(name = "Transaction", schema = "public")
//@NamedQuery(name = "TransactionEntityModel.findAllTransactionByDepotId", query = "select TransactionEntityModel FROM TransactionEntityModel t where t.depot.id = ?1")
//@NamedQuery(name = "TransactionEntityModel.findTransactionById", query = "select TransactionEntityModel FROM TransactionEntityModel t where t.id = ?1")
class TransactionEntityModel{

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

@RegisterForReflection
data class TransactionDTO(
    val id: Long,
    val customerId: Long,
    val dateTime: Date,
    val flag: Boolean? = null,
    val moneyValue: BigDecimal,
    val iban: String,
    val bic: String,
    val type: String
)

