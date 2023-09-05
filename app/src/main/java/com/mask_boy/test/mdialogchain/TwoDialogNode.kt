package com.mask_boy.test.mdialogchain

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.WindowManager.LayoutParams
import com.mask_boy.module.mdialogchain.MDialogNodeCreator

/**
 * Created by zbm on 2023/8/29.
 */
class TwoDialogNode: MDialogNodeCreator() {

    override fun createDialog(context: Context): Dialog {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_two)
        dialog.findViewById<View>(R.id.tv_title)?.setOnClickListener {
            dialog.dismiss()
        }
        dialog.window?.setLayout(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        return dialog
    }

    override fun execute() {
        nodeDialog?.show()
    }
}