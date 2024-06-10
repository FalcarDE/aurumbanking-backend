package de.fhe.cc.team4.aurumbanking.model

import jakarta.persistence.*
import lombok.Data
import java.time.LocalDateTime

@Data
@Entity
@Table(name = "Support", schema = "public")
class SupportEntityModel() {

    @Id
    @GeneratedValue
    var id: Long = 0

    @Column(name = "customer_id")
    var customerId : Long = 0

    @Column(length = 2500)
    lateinit var dateTime: LocalDateTime
    lateinit var type : String
    lateinit var message: String
}