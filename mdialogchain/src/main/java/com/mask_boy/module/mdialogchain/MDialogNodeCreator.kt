package com.mask_boy.module.mdialogchain

import android.app.Dialog
import android.content.Context

/**
 * Created by zbm on 2023/8/25.
 */
abstract class MDialogNodeCreator {
    protected var nodeDialog: Dialog? = null

    fun build(context: Context, dialogId: Int): DialogNode? {
        nodeDialog = createDialog(context)
        val node = DialogNode.create(dialogId) { node ->
            execute()
            nodeDialog?.setOnDismissListener {
                node.complete()
                nodeDialog = null
            }
        }
        return node
    }

    /**
     * 构造一个对话框
     */
    abstract fun createDialog(context: Context): Dialog

    /**
     * 在此执行业务弹窗逻辑
     */
    abstract fun execute()
}