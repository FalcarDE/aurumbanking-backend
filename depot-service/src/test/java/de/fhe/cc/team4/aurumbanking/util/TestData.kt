package de.fhe.cc.team4.aurumbanking.util

import de.fhe.cc.team4.aurumbanking.model.entities.DepotEntityModel
import java.math.BigDecimal

fun createDepot(): DepotEntityModel {
    var testDataDepot = DepotEntityModel()

    testDataDepot.customerId = 1
    testDataDepot.currencyArea = "EUR"
    testDataDepot.depositAmount = BigDecimal(2500.00)
    testDataDepot.fallbackDepositAmount = BigDecimal(1000.00)

    return testDataDepot
}
