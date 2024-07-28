package de.fhe.cc.team4.aurumbanking.core

enum class TransactionClassification(val displayName: String) {
    STANDARD_UEBERWEISUNG("Standard Überweisung"),
    INTERNATIONALE_UEBERWEISUNG("Internationale Überweisung"),
    DAUERAUFTRAG("Dauerauftrag")
}