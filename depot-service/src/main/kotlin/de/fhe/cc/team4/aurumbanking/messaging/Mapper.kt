package de.fhe.cc.team4.messaging

import de.fhe.cc.team4.aurumbanking.domain.IncomingTransactionDTO
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer


class KafkaTransactionDeserializer : ObjectMapperDeserializer<IncomingTransactionDTO>(IncomingTransactionDTO::class.java)