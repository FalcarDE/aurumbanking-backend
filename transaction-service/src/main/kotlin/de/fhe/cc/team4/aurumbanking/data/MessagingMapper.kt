package de.fhe.cc.team4.aurumbanking.data

import de.fhe.cc.team4.aurumbanking.domain.TransactinKafkaDTO
import io.quarkus.kafka.client.serialization.ObjectMapperSerializer

class ReviewAggregateSerializer : ObjectMapperSerializer<TransactinKafkaDTO>()