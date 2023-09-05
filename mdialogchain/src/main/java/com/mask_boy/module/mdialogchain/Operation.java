package com.mask_boy.module.mdialogchain;

import androidx.annotation.NonNull;

/**
 * Created by zbm on 2023/08/25
 */
public interface Operation {
    State.INIT INIT = new State.INIT();
    State.PROGRESS PROGRESS = new State.PROGRESS();
    State.COMPLETE COMPLETE = new State.COMPLETE();

    abstract class State {
        State() {
        }

        public static final class INIT extends State {
            private INIT() {
                super();
            }

            @Override
            @NonNull
            public String toString() {
                return "INIT";
            }
        }

        public static final class PROGRESS extends State {
            private PROGRESS() {
                super();
            }

            @Override
            @NonNull
            public String toString() {
                return "PROGRESS";
            }
        }

        public static final class COMPLETE extends State {
            private COMPLETE() {
                super();
            }

            @Override
            @NonNull
            public String toString() {
                return "COMPLETE";
            }
        }

        public static final class ERROR extends State {

            private final Throwable mThrowable;

            public ERROR(@NonNull Throwable exception) {
                super();
                mThrowable = exception;
            }

            @NonNull
            public Throwable getThrowable() {
                return mThrowable;
            }

            @Override
            @NonNull
            public String toString() {
                return String.format("ERROR (%s)", mThrowable.getMessage());
            }
        }
    }
}
