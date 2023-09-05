package com.mask_boy.test.mdialogchain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mask_boy.module.mdialogchain.MDialogChainHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MDialogChainHelper.run {
            addDialogChain("test_main")
                .builder
                .addNode(OneDialogNode().build(this@MainActivity, 0))
                .addNode(TwoDialogNode().build(this@MainActivity, 1))
            startDialogChain("test_main"    )
        }
    }
}