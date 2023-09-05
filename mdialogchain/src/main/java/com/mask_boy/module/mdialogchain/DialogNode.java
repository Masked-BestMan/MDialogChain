package com.mask_boy.module.mdialogchain;

/**
 * Created by zbm on 2023/08/25
 */
public class DialogNode implements Node {
    private static final String TAG = "ChainNode";
    private int id;
    private String tag;
    private Operation.State state = Operation.INIT;
    private Executor executor;
    private CallBack callBack;

    private DialogNode(int id, String tag, Executor executor) {
        this.id = id;
        this.tag = tag;
        this.executor = executor;
    }

    public static DialogNode create(int id, Executor executor) {
        return create(id, TAG + id, executor);
    }

    public static DialogNode create(int id, String tag, Executor executor) {
        return new DialogNode(id, tag, executor);
    }

    @Override
    public void complete() {
        setState(Operation.COMPLETE);
        if (callBack != null) {
            callBack.onComplete();
        }
    }

    @Override
    public void error(ChainException e) {
        setState(Operation.COMPLETE);
        if (callBack != null) {
            callBack.onError(e);
        }
    }

    public void process(CallBack callBack) {
        this.callBack = callBack;
        if (executor != null) {
            executor.execute(this);
        }
    }

    public static String getTAG() {
        return TAG;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Executor getExecutor() {
        return executor;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public CallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public Operation.State getState() {
        return state;
    }

    public void setState(Operation.State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ChainNode{" +
                "id=" + id +
                ", tag='" + tag + '\'' +
                ", state=" + state +
                ", executor=" + executor +
                ", callBack=" + callBack +
                '}';
    }

    public interface CallBack {
        void onComplete();

        void onError(ChainException e);
    }
}
