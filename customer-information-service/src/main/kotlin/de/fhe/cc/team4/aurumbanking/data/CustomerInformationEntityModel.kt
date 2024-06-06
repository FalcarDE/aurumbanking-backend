package de.fhe.cc.team4.aurumbanking.data

import jakarta.persistence.*
import lombok.Data
import java.time.LocalDateTime
import java.util.*


@Data
@Entity
@Table(name = "CustomerInformation", schema = "public")
class CustomerInformationEntityModel() {

    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    var id: Long = 0

    @Column(length = 2500)
    lateinit var firstname: String
    lateinit var lastname: String
    lateinit var birthDate: Date
    lateinit var created: LocalDateTime
    lateinit var lastestLogin: LocalDateTime
    lateinit var streetName: String
    lateinit var housenumber: String
    lateinit var city: String
    lateinit var country: String
    lateinit var username: String
    lateinit var email: String
    lateinit var phoneNumber: String
    lateinit var password: String

    var profileImage: ByteArray? = null
}


