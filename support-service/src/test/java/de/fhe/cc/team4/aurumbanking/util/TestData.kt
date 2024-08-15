package de.fhe.cc.team4.aurumbanking.util

import de.fhe.cc.team4.aurumbanking.model.SupportEntityModel
import java.time.LocalDateTime

fun createSupportEntry(): SupportEntityModel {
    val newSupportEntry = SupportEntityModel() // Wenn Domain
    newSupportEntry.customerId = 12345
    newSupportEntry.dateTime = LocalDateTime.now()
    newSupportEntry.type = "Konto"
    newSupportEntry.message = "Wie kann ich mein Konto erstellen?"

    return newSupportEntry
}

fun createSupportEntry1(): SupportEntityModel {
    val newSupportEntry = SupportEntityModel()
    newSupportEntry.customerId = 12345
    newSupportEntry.dateTime = LocalDateTime.now()
    newSupportEntry.type = "Konto"
    newSupportEntry.message = "Wie kann ich mein Konto schließen?"

    return newSupportEntry
}

fun createSupportEntry2(): SupportEntityModel {
    val newSupportEntry = SupportEntityModel()
    newSupportEntry.customerId = 67890
    newSupportEntry.dateTime = LocalDateTime.now()
    newSupportEntry.type = "Technischer Support"
    newSupportEntry.message = "Ich kann mich nicht in mein Konto einloggen."

    return newSupportEntry
}

fun createSupportEntry3(): SupportEntityModel {
    val newSupportEntry = SupportEntityModel()
    newSupportEntry.customerId = 12345
    newSupportEntry.dateTime = LocalDateTime.now()
    newSupportEntry.type = "Zahlungen"
    newSupportEntry.message = "Wie kann ich eine Überweisung durchführen?"

    return newSupportEntry
}

fun createSupportEntry4(): SupportEntityModel {
    val newSupportEntry = SupportEntityModel()
    newSupportEntry.customerId = 67890
    newSupportEntry.dateTime = LocalDateTime.now()
    newSupportEntry.type = "Konto"
    newSupportEntry.message = "Welche Dokumente benötige ich, um ein Konto zu eröffnen?"

    return newSupportEntry
}

fun createSupportEntry5(): SupportEntityModel {
    val newSupportEntry = SupportEntityModel()
    newSupportEntry.customerId = 13579
    newSupportEntry.dateTime = LocalDateTime.now()
    newSupportEntry.type = "Kundendienst"
    newSupportEntry.message = "Wie kann ich meine persönlichen Daten aktualisieren?"

    return newSupportEntry
}

fun createAllSupportEntries(): List<SupportEntityModel> {
    val supportEntry0 = createSupportEntry()
    val supportEntry1 = createSupportEntry1()
    val supportEntry2 = createSupportEntry2()
    val supportEntry3 = createSupportEntry3()
    val supportEntry4 = createSupportEntry4()
    val supportEntry5 = createSupportEntry5()

    return listOf(supportEntry0, supportEntry1, supportEntry2, supportEntry3, supportEntry4, supportEntry5)
}