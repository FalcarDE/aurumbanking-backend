package de.fhe.cc.team4.aurumbanking.data.entities

import jakarta.persistence.*
import lombok.Data
import java.math.BigDecimal
import java.util.*

@Data
@Entity
@Table(name = "transactions", schema = "public")
class TransactionEntityModel() {

    @Id
    @GeneratedValue
    var id: Long = 0

    @Column(name = "customer_id")
    var customerId: Long = 0

    @Column(length = 2500)
    lateinit var dateTime: Date
    var flag: Boolean? = null
    lateinit var moneyValue : BigDecimal
    lateinit var iban: String
    lateinit var bic: String
    lateinit var type: String


}