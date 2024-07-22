package de.fhe.cc.team4.aurumbanking.model

import io.quarkus.runtime.annotations.RegisterForReflection
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
    lateinit var zipcode: String
    lateinit var username: String
    lateinit var email: String
    lateinit var phoneNumber: String
    lateinit var password: String

    var profileImage: ByteArray? = null

    constructor(
        firstname: String,
        lastname: String,
        birthDate: Date,
        created: LocalDateTime,
        lastestLogin: LocalDateTime,
        streetName: String,
        housenumber: String,
        city: String,
        country: String,
        zipcode: String,
        username: String,
        email: String,
        phoneNumber: String,
        password: String,
        profileImage: ByteArray,
    ) : this() {
        this.firstname = firstname
        this.lastname = lastname
        this.birthDate = birthDate
        this.created = created
        this.lastestLogin = lastestLogin
        this.streetName = streetName
        this.housenumber = housenumber
        this.city = city
        this.country = country
        this.zipcode = zipcode
        this.username = username
        this.email = email
        this.phoneNumber = phoneNumber
        this.password = password
        this.profileImage = profileImage
    }
}

@RegisterForReflection
data class CustomerLoginDTO(val email: String, val password: String)


