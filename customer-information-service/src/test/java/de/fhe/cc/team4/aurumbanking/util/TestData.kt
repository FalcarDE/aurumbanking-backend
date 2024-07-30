package de.fhe.cc.team4.aurumbanking.util

import de.fhe.cc.team4.aurumbanking.model.CustomerInformationEntityModel
import java.time.LocalDateTime
import java.util.*

fun createCustomerInformation(): CustomerInformationEntityModel {
    val newCustomer = CustomerInformationEntityModel()
    newCustomer.firstname = "John"
    newCustomer.lastname = "Doe"
    newCustomer.birthDate = Date(1990, 1, 1)
    newCustomer.created = LocalDateTime.now()
    newCustomer.lastestLogin = LocalDateTime.now()
    newCustomer.streetName   = "Main Street"
    newCustomer.housenumber = "123"
    newCustomer.city = "Sample City"
    newCustomer.country = "Sample Country"
    newCustomer.zipcode = "DE17538"
    newCustomer.username = "johndoe"
    newCustomer.email = "johndoe@example.com"
    newCustomer.phoneNumber = "+1234567890"
    newCustomer.password = "123"
    newCustomer.profileImage = Base64.getDecoder()
        .decode("iVBORw0KGgoAAAANSUhEUgAAAGQAAABkCAIAAAD/gAIDAAAAEklEQVR4nO3BMQEAAADCoPdPbQ43oAAAAABJRU5ErkJggg==")

    return newCustomer
}


