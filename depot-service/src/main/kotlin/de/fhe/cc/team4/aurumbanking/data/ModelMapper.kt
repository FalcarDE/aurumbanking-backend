package de.fhe.cc.team4.aurumbanking.data

import de.fhe.cc.team4.aurumbanking.data.entities.DepotEntityModel
import de.fhe.cc.team4.aurumbanking.domain.Depot
import java.util.*

fun Depot.toEntity() =
    DepotEntityModel().apply {
        id = this@toEntity.id
        customerId = this@toEntity.customerId
        dateTime = this@toEntity.dateTime
        flag = this@toEntity.flag
        moneyValue = this@toEntity.moneyValue
        iban = this@toEntity.iban
        bic = this@toEntity.bic
        type = this@toEntity.type
    }


fun DepotEntityModel.toDomain() =
    Depot().apply {
        id          = this@toDomain.id
        customerId  = this@toDomain.customerId
        dateTime    = this@toDomain.dateTime
        flag        = this@toDomain.flag
        moneyValue  = this@toDomain.moneyValue
        iban        = this@toDomain.iban
        bic         = this@toDomain.bic
        type        = this@toDomain.type
    }
