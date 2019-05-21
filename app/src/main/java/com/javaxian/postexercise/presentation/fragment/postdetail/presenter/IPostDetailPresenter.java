package com.javaxian.postexercise.presentation.fragment.postdetail.presenter;

import com.javaxian.postexercise.domain.base.BasePresenter;
import com.javaxian.postexercise.domain.entity.CommentEntity;

import java.util.ArrayList;

public interface IPostDetailPresenter {

    void onGetComments(String postId);


    interface View extends BasePresenter.View {

        void initView();

        void initViewOfComments(ArrayList<CommentEntity> comments);

        void initNotAvailableViewOfComments();

        void updateViewOfComments();

        void showProgressBarOfComments();

        void hideProgressBarOfComments();
    }
}
