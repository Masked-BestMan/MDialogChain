package com.mask_boy.module.mdialogchain;


import android.util.Log;

/**
 * Created by zbm on 2023/08/25
 */
public class ChainException extends RuntimeException {
    public ChainException(String message) {
        super(message);
        Log.e("zbm111", "ChainException" + message);
    }
}
