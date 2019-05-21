package com.javaxian.postexercise.domain.base;

import java.util.ArrayList;

public abstract class BaseResponseList<T> {

    public abstract void onSuccess(ArrayList<T> objList);

    public void onError(Exception e, String msg) {
    }
}
