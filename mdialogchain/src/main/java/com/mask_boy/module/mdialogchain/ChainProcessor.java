package com.mask_boy.module.mdialogchain;

import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;

import java.util.UUID;


/**
 * Created by zbm on 2023/08/25
 */
public class ChainProcessor {
    private final SparseArray<DialogNode> nodeArrays;
    private final Builder builder;

    private ChainProcessor(SparseArray<DialogNode> nodeArrays, Builder builder) {
        this.nodeArrays = nodeArrays;
        this.builder = builder;
    }

    public void start() {
        if (nodeArrays == null || nodeArrays.size() <= 0) {
            Log.e("zbm111", "nodeArrays == null || nodeArrays.size <= 0");
            return;
        }
        startNode(nodeArrays.keyAt(0));
    }

    private void startNode(int nodeId) {
        int index = nodeArrays.indexOfKey(nodeId);
        DialogNode node = nodeArrays.valueAt(index);
        if (node != null && node.getState() == Operation.INIT) {
            node.setState(Operation.PROGRESS);
            node.process(new DialogNode.CallBack() {
                @Override
                public void onComplete() {
                    nextNode(index);
                }

                @Override
                public void onError(ChainException e) {
                    cancel();
                }
            });
        }
    }

    private void nextNode(int index) {
        //移除执行过的第一个
        removeNode(index);
        if (nodeArrays != null && nodeArrays.size() > 0) {
            startNode(nodeArrays.keyAt(0));
        }
    }

    private void removeNode(int index) {
        if (nodeArrays != null && nodeArrays.size() > 0) {
            nodeArrays.removeAt(index);
        }
    }

    private void cancel() {
        if (nodeArrays != null && nodeArrays.size() > 0) {
            nodeArrays.clear();
        }
    }

    public Builder getBuilder() {
        return builder;
    }

    public static class Builder {
        private final SparseArray<DialogNode> nodeArrays;
        private String tag;

        public Builder() {
            this.nodeArrays = new SparseArray<>();
        }

        public Builder addNode(DialogNode node) {
            if (node != null) {
                nodeArrays.append(node.getId(), node);
                if (TextUtils.isEmpty(tag)) {
                    tag = UUID.randomUUID().toString();
                }
                node.setTag(tag);
            }
            return this;
        }

        public Builder addNodes(DialogNode... nodes) {
            if (nodes != null && nodes.length > 0) {
                for (DialogNode node : nodes) {
                    addNode(node);
                }
            }
            return this;
        }

        public Builder addTag(String tag) {
            this.tag = tag;
            return this;
        }

        public ChainProcessor build() {
            checkTag();
            return new ChainProcessor(nodeArrays, this);
        }

        private void checkTag() {
            if (nodeArrays.size() > 0) {
                if (TextUtils.isEmpty(tag)) {
                    tag = UUID.randomUUID().toString();
                }
                for (int i = 0; i < nodeArrays.size(); i++) {
                    nodeArrays.get(nodeArrays.keyAt(i)).setTag(tag);
                }
            }
        }

        public String getTag() {
            return tag;
        }

        public SparseArray<DialogNode> getNodes() {
            return nodeArrays;
        }
    }
}
