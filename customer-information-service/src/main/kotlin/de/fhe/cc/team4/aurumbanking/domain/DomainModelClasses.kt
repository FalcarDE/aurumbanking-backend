package de.fhe.cc.team4.aurumbanking.domain
import java.time.LocalDateTime
import java.util.*


data class CustomerInformationDomainModel(
    var id:          Long = 0,
    var firstname:   String,
    var lastname:    String,
    var birthDate:   Date,
    var created:     LocalDateTime,
    var lastestLogin:LocalDateTime,
    var streetName:  String,
    var housenumber: String,
    var city:        String,
    var country:     String,
    var username:    String,
    var email:       String,
    var phoneNumber: String,
    var password:    String,
    var profileImage: ByteArray? = null
){
    constructor():
            this(firstname = "testFirstName",
                lastname = "testLastname",
                birthDate= Date(1990, 1,1),
                created=LocalDateTime.now(),
                lastestLogin=LocalDateTime.now(),
                streetName="testStreetName",
                housenumber="1",
                city="testCity",
                country="testCountry",
                username = "testUsername",
                email = "testEmail",
                phoneNumber = "testPhoneNumber",
                password = "testPassword",
                profileImage = null
            )
}


