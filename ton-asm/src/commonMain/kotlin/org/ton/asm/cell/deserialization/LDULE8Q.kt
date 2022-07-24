package org.ton.asm.cell.deserialization

import org.ton.asm.Instruction
import org.ton.cell.CellBuilder
import org.ton.cell.CellSlice
import org.ton.tlb.TlbConstructor
import org.ton.tlb.providers.TlbConstructorProvider

object LDULE8Q : Instruction, TlbConstructorProvider<LDULE8Q> by LDULE8QTlbConstructor {
    override fun toString(): String = "LDULE8Q"
}

private object LDULE8QTlbConstructor : TlbConstructor<LDULE8Q>(
    schema = "asm_ldule8q#d75b = LDULE8Q;",
    type = LDULE8Q::class
) {
    override fun storeTlb(cellBuilder: CellBuilder, value: LDULE8Q) {
    }

    override fun loadTlb(cellSlice: CellSlice): LDULE8Q = LDULE8Q
}