package com.javaxian.postexercise.presentation.fragment.postlist.presenter;

import com.javaxian.postexercise.domain.base.BasePresenter;
import com.javaxian.postexercise.domain.entity.PostEntity;

import java.util.ArrayList;

public interface IPostListPresenter {

    void onGetPosts();

    void onGetPostsFromUser(String userId);

    void onCreatePostToUser(PostEntity postEntity);


    interface View extends BasePresenter.View {

        void initView(ArrayList<PostEntity> posts);

        void initNotAvailableView();

        void updateView();

        void showProgressBar();

        void hideProgressBar();
    }
}
