package de.fhe.cc.team4.aurumbanking.data

import de.fhe.cc.team4.aurumbanking.domain.CustomerInformationDomainModel

fun CustomerInformationDomainModel.toEntity() =
    CustomerInformationEntityModel().apply {
        id = this@toEntity.id
        firstname = this@toEntity.firstname
        lastname = this@toEntity.lastname
        birthDate = this@toEntity.birthDate
        created = this@toEntity.created
        lastestLogin = this@toEntity.lastestLogin
        streetName = this@toEntity.streetName
        housenumber = this@toEntity.housenumber
        city = this@toEntity.city
        country = this@toEntity.country
        username = this@toEntity.username
        email = this@toEntity.email
        phoneNumber = this@toEntity.phoneNumber
        password = this@toEntity.password
        profileImage = this@toEntity.profileImage
    }

fun CustomerInformationEntityModel.toDomain() =
    CustomerInformationDomainModel().apply {
        id = this@toDomain.id
        firstname = this@toDomain.firstname
        lastname = this@toDomain.lastname
        birthDate = this@toDomain.birthDate
        created = this@toDomain.created
        lastestLogin = this@toDomain.lastestLogin
        streetName = this@toDomain.streetName
        housenumber = this@toDomain.housenumber
        city = this@toDomain.city
        country = this@toDomain.country
        username = this@toDomain.username
        email = this@toDomain.email
        phoneNumber = this@toDomain.phoneNumber
        password = this@toDomain.password
        profileImage = this@toDomain.profileImage
    }

