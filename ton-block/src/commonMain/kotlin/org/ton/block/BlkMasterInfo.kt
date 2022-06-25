package org.ton.block

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.ton.cell.CellBuilder
import org.ton.cell.CellSlice
import org.ton.tlb.TlbConstructor
import org.ton.tlb.loadTlb
import org.ton.tlb.storeTlb

@SerialName("master_info")
@Serializable
data class BlkMasterInfo(
    val master: ExtBlkRef
) {
    companion object {
        @JvmStatic
        fun tlbCodec(): TlbConstructor<BlkMasterInfo> = BlkMasterInfoTlbConstructor
    }
}

private object BlkMasterInfoTlbConstructor : TlbConstructor<BlkMasterInfo>(
    schema = "master_info\$_ master:ExtBlkRef = BlkMasterInfo;"
) {
    val extBlkRef by lazy { ExtBlkRef.tlbCodec() }

    override fun storeTlb(
        cellBuilder: CellBuilder,
        value: BlkMasterInfo
    ) = cellBuilder {
        storeTlb(extBlkRef, value.master)
    }

    override fun loadTlb(
        cellSlice: CellSlice
    ): BlkMasterInfo = cellSlice {
        val master = loadTlb(extBlkRef)
        BlkMasterInfo(master)
    }
}