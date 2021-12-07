package com.ua.agar.io.hackathon.serialization

import io.ktor.utils.io.core.*
import io.rsocket.kotlin.metadata.*
import io.rsocket.kotlin.payload.*
import kotlinx.serialization.*
import kotlinx.serialization.protobuf.*
import kotlin.jvm.*

//just stub
val ConfiguredProtoBuf = ProtoBuf

@ExperimentalSerializationApi
inline fun <reified T> ProtoBuf.decodeFromPayload(payload: Payload): T = decodeFromByteArray(payload.data.readBytes())

@ExperimentalSerializationApi
inline fun <reified T> ProtoBuf.encodeToPayload(route: String, value: T): Payload = buildPayload {
    data(encodeToByteArray(value))
    metadata(RoutingMetadata(route))
}

@ExperimentalSerializationApi
inline fun <reified T> ProtoBuf.encodeToPayload(value: T): Payload = buildPayload {
    data(encodeToByteArray(value))
}

@ExperimentalSerializationApi
inline fun <reified I, reified O> ProtoBuf.decoding(payload: Payload, block: (I) -> O): Payload =
    encodeToPayload(decodeFromPayload<I>(payload).let(block))

@ExperimentalSerializationApi
@JvmName("decoding2")
inline fun <reified I> ProtoBuf.decoding(payload: Payload, block: (I) -> Unit): Payload {
    decodeFromPayload<I>(payload).let(block)
    return Payload.Empty
}
