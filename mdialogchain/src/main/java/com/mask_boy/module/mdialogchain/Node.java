package com.mask_boy.module.mdialogchain;

/**
 * Created by zbm on 2023/08/25
 */
public interface Node {
    int getId();

    String getTag();

    void complete();

    void error(ChainException e);
}
