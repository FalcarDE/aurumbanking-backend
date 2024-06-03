package de.fhe.cc.team4.aurumbanking.domain

import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.time.LocalDateTime

import java.util.*

data class Depot(
    var id: Long = 0,
    var customerId: Long = 0,
    var dateTime: Date,
    var flag: Boolean? = null,
    var moneyValue: BigDecimal,
    var iban: String,
    var bic: String,
    var type: String
){
   constructor() : this(
    id = 0,
    customerId = 0,
    dateTime = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse("2024-06-03T12:00:00Z"),
    flag = true,
    moneyValue = BigDecimal("1500.75"),
    iban = "DE89370400440532013000",
    bic = "COBADEFFXXX",
    type = "Deposit"
    )
}


