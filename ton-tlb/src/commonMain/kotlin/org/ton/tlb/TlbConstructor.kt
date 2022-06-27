package org.ton.tlb

import org.intellij.lang.annotations.Language
import org.ton.bitstring.BitString
import org.ton.tlb.exception.ParseTlbException

abstract class AbstractTlbConstructor<T>(
    @Language("TL-B")
    val schema: String,
    id: BitString? = null
) {
    val id = id ?: calculateId(schema)
    override fun toString(): String = schema

    companion object {
        fun calculateId(@Language("TL-B") schema: String): BitString {
            if (schema.isEmpty()) return BitString(0)
            try {
                val prefix = schema.split(" ").first()
                if (prefix.contains('$')) {
                    val (_, id) = prefix.split('$')
                    if (id != "_") {
                        return BitString.binary(id)
                    }
                } else if (prefix.contains('#')) {
                    val (_, id) = prefix.split('#')
                    if (id != "_") {
                        return BitString(id)
                    }
                }
                return BitString(0)
            } catch (e: Exception) {
                throw ParseTlbException("Failed to calculate id for schema: `$schema`", e)
            }
        }
    }
}

abstract class TlbConstructor<T>(
    @Language("TL-B")
    schema: String,
    id: BitString? = null
) : AbstractTlbConstructor<T>(schema, id), TlbCodec<T> {
    fun asTlbCombinator(): TlbCombinator<T> = object : TlbCombinator<T>() {
        override val constructors: List<TlbConstructor<out T>> = listOf(this@TlbConstructor)

        override fun getConstructor(value: T): TlbConstructor<out T> = this@TlbConstructor
    }
}

abstract class TlbNegatedConstructor<T>(
    @Language("TL-B")
    schema: String,
    id: BitString? = null
) : AbstractTlbConstructor<T>(schema, id), TlbNegatedCodec<T>
