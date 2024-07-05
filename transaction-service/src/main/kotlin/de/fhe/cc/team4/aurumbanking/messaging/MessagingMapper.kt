package de.fhe.cc.team4.aurumbanking.messaging

import de.fhe.cc.team4.aurumbanking.data.entities.TransactionKafkaDTO
import io.quarkus.kafka.client.serialization.ObjectMapperSerializer

class TransactionSerializer : ObjectMapperSerializer<TransactionKafkaDTO>()