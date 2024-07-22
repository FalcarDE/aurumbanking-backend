package de.fhe.cc.team4.aurumbanking.util

import de.fhe.cc.team4.aurumbanking.model.CustomerInformationEntityModel
import java.time.LocalDateTime
import java.util.*

fun createCustomerInformation() =
    CustomerInformationEntityModel(
        "John",
        "Doe",
        birthDate = Date(1990, 1, 1),
        created = LocalDateTime.now(),
        lastestLogin = LocalDateTime.now(),
        "Main Street",
        "123",
        "Sample City",
        "Sample Country",
        "DE17538",
        "johndoe",
        "johndoe@example.com",
        "+1234567890",
        "securepassword",
        Base64.getDecoder()
            .decode("iVBORw0KGgoAAAANSUhEUgAAAGQAAABkCAIAAAD/gAIDAAAAEklEQVR4nO3BMQEAAADCoPdPbQ43oAAAAABJRU5ErkJggg==")
    )
