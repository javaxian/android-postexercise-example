package com.javaxian.postexercise.domain.component;

import com.javaxian.postexercise.domain.interactor.postdetail.PostDetailInteractorImpl;
import com.javaxian.postexercise.domain.interactor.postlist.PostListInteractorImpl;
import com.javaxian.postexercise.domain.module.RetrofitModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RetrofitModule.class})
public interface Components {

    void inject(PostListInteractorImpl target);
    void inject(PostDetailInteractorImpl target);
}
