package com.javaxian.postexercise.presentation.fragment.splash.presenter;

import com.javaxian.postexercise.domain.base.BasePresenter;

public interface ISplashPresenter {

    void selectActivity();

    interface View extends BasePresenter.View {


        void startNextActivity(Class<?> cls);


    }
}
