package de.fhe.cc.team4.aurumbanking.domain

import java.time.LocalDateTime

data class SupportDomainModel(
    var id : Long = 0,
    var customerId : Long = 0,
    var dateTime: LocalDateTime,
    var type : String,
    var message: String
) {
    constructor() : this(
        id = 0,
        customerId = 0,
        dateTime = LocalDateTime.now(),
        type = "",
        message = ""
    )
}



