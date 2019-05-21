package com.javaxian.postexercise.presentation.fragment.splash.presenter;

import android.os.Build;

import com.javaxian.postexercise.presentation.activity.PostListActivity;
import com.javaxian.postexercise.domain.base.BasePresenter;

public class SplashPresenterImpl extends BasePresenter<ISplashPresenter.View> implements ISplashPresenter{


    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void selectActivity() {
        Class cls = PostListActivity.class;

        if (getView() != null)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //if need to request permission set here

                getView().startNextActivity(cls);
            } else {

                getView().startNextActivity(cls);
            }
    }
}
