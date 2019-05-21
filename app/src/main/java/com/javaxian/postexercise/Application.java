package com.javaxian.postexercise;

import android.support.multidex.MultiDexApplication;

import com.javaxian.postexercise.domain.component.Components;
import com.javaxian.postexercise.domain.component.DaggerComponents;
import com.javaxian.postexercise.domain.module.RetrofitModule;

public class Application extends MultiDexApplication {

    private static Application instance;
    private Components components;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //RealmConfig.getInstance().initRealm(this);
        components = DaggerComponents.builder()
                .retrofitModule(new RetrofitModule())
                .build();
    }


    public static Application getInstance() {
        return instance;
    }

    public Components getComponents() {
        return components;
    }
}
