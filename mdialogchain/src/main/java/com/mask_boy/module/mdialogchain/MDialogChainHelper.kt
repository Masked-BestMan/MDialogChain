package com.mask_boy.module.mdialogchain


/**
 * Created by zbm on 2023/08/25
 */
object MDialogChainHelper {
    private val chainNodeMap = mutableMapOf<String, ChainProcessor>()

    private fun build(chainProcessor: ChainProcessor) {
        if (chainNodeMap.containsKey(chainProcessor.builder.tag)) {
            chainNodeMap.remove(chainProcessor.builder.tag)
        }
        chainNodeMap[chainProcessor.builder.tag] = chainProcessor
    }

    fun startDialogChain(tag: String) {
        chainNodeMap[tag]?.start()
    }

    private fun clearAllChain() {
        chainNodeMap.clear()
    }

    private fun clearDialogChain(tag: String): ChainProcessor? {
        return chainNodeMap.remove(tag)
    }

    fun addDialogChain(tag: String): ChainProcessor {
        val chainProcessor = ChainProcessor.Builder().addTag(tag).build()
        if (chainNodeMap.containsKey(chainProcessor.builder.tag)) {
            chainNodeMap.remove(chainProcessor.builder.tag)
        }
        chainNodeMap[chainProcessor.builder.tag] = chainProcessor
        return chainProcessor
    }

    fun getDialogChain(tag: String): ChainProcessor? {
        return chainNodeMap[tag]
    }

}