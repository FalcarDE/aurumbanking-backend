package de.fhe.cc.team4.aurumbanking.data.entities

enum class TransactionClassification (val displayName: String) {
    STANDARD_UEBERWEISUNG("Standard Überweisung"),
    INTERNATIONALE_UEBERWEISUNG("Internationale Überweisung"),
    DAUERAUFTRAG("Dauerauftrag")
}