@file:Suppress("OPT_IN_USAGE")

package org.ton.api.dht

import io.ktor.utils.io.core.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator
import org.ton.tl.TlCombinator
import org.ton.tl.TlConstructor
import org.ton.tl.readTl
import org.ton.tl.writeTl

@JsonClassDiscriminator("@type")
interface DhtValueResult {
    companion object : TlCombinator<DhtValueResult>(
        DhtValueNotFound,
        DhtValueFound
    )
}

@SerialName("dht.valueNotFound")
@Serializable
data class DhtValueNotFound(
    val nodes: DhtNodes
) : DhtValueResult {
    companion object : TlConstructor<DhtValueNotFound>(
        type = DhtValueNotFound::class,
        schema = "dht.valueNotFound nodes:dht.nodes = dht.ValueResult"
    ) {
        override fun encode(output: Output, value: DhtValueNotFound) {
            output.writeTl(DhtNodes, value.nodes)
        }

        override fun decode(input: Input): DhtValueNotFound {
            val nodes = input.readTl(DhtNodes)
            return DhtValueNotFound(nodes)
        }
    }
}

@SerialName("dht.valueFound")
@Serializable
data class DhtValueFound(
    val value: DhtValue
) : DhtValueResult {
    companion object : TlConstructor<DhtValueFound>(
        type = DhtValueFound::class,
        schema = "dht.valueFound value:dht.Value = dht.ValueResult"
    ) {
        override fun encode(output: Output, value: DhtValueFound) {
            output.writeTl(DhtValue, value.value)
        }

        override fun decode(input: Input): DhtValueFound {
            val value = input.readTl(DhtValue)
            return DhtValueFound(value)
        }
    }
}
