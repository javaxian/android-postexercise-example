package com.javaxian.postexercise.presentation.fragment.postlist.presenter;

import com.javaxian.postexercise.domain.base.BasePresenter;
import com.javaxian.postexercise.domain.base.BaseResponse;
import com.javaxian.postexercise.domain.base.BaseResponseList;
import com.javaxian.postexercise.domain.entity.PostEntity;
import com.javaxian.postexercise.domain.interactor.postlist.IPostListInteractor;
import com.javaxian.postexercise.domain.interactor.postlist.PostListInteractorImpl;

import java.util.ArrayList;

public class PostListPresenterImpl extends BasePresenter<IPostListPresenter.View> implements IPostListPresenter{

    private IPostListInteractor interactor;

    @Override
    public void initialize() {
        super.initialize();
        interactor = new PostListInteractorImpl();
    }

    @Override
    public void onGetPosts() {
        interactor.getPostList( new BaseResponseList<PostEntity>() {
            @Override
            public void onSuccess(ArrayList<PostEntity> objList) {

                if(objList!=null){
                    if(objList.size()>0){
                        getView().hideProgressBar();
                        getView().initView(objList);
                    }else{
                        getView().hideProgressBar();
                        getView().initNotAvailableView();
                    }
                }else{
                    getView().hideProgressBar();
                    getView().initNotAvailableView();
                }

            }

            @Override
            public void onError(Exception e, String msg) {
                getView().hideProgressBar();
                getView().initNotAvailableView();
            }
        });
    }

    @Override
    public void onGetPostsFromUser(String userId) {

        getView().showProgressBar();

        interactor.getPostListFromUser(userId, new BaseResponseList<PostEntity>() {
            @Override
            public void onSuccess(ArrayList<PostEntity> objList) {

                if(objList!=null){
                    if(objList.size()>0){
                        getView().hideProgressBar();
                        getView().initView(objList);
                    }else{
                        getView().hideProgressBar();
                        getView().initNotAvailableView();
                    }
                }else{
                    getView().hideProgressBar();
                    getView().initNotAvailableView();
                }

            }

            @Override
            public void onError(Exception e, String msg) {
                getView().hideProgressBar();
                getView().initNotAvailableView();
            }
        });
    }

    @Override
    public void onCreatePostToUser(PostEntity postEntity) {

        getView().showProgressBar();

        interactor.createPostToUser(postEntity, new BaseResponse() {
            @Override
            public void onSuccess() {

                getView().hideProgressBar();
                getView().updateView();
            }

            @Override
            public void onError(Exception e) {
                getView().hideProgressBar();
                getView().initNotAvailableView();
            }
        });
    }
}
