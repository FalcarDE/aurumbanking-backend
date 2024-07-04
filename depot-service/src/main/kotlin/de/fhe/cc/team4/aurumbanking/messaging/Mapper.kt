package de.fhe.cc.team4.messaging

import de.fhe.cc.team4.aurumbanking.model.entities.DepotUpdateDTO
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer


class KafkaTransactionDeserializer : ObjectMapperDeserializer<DepotUpdateDTO>(DepotUpdateDTO::class.java)