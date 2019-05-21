package com.javaxian.postexercise.presentation.fragment.postdetail.presenter;

import com.javaxian.postexercise.domain.base.BasePresenter;
import com.javaxian.postexercise.domain.base.BaseResponseList;
import com.javaxian.postexercise.domain.entity.CommentEntity;
import com.javaxian.postexercise.domain.interactor.postdetail.IPostDetailInteractor;
import com.javaxian.postexercise.domain.interactor.postdetail.PostDetailInteractorImpl;

import java.util.ArrayList;

public class PostDetailPresenterImpl extends BasePresenter<IPostDetailPresenter.View> implements IPostDetailPresenter {

    private IPostDetailInteractor interactor;

    @Override
    public void initialize() {
        super.initialize();
        interactor = new PostDetailInteractorImpl();
        getView().initView();
    }

    @Override
    public void onGetComments(String postId) {

        interactor.getCommentList(postId, new BaseResponseList<CommentEntity>() {
            @Override
            public void onSuccess(ArrayList<CommentEntity> objList) {

                if(objList!=null){
                    if(objList.size()>0){
                        getView().hideProgressBarOfComments();
                        getView().initViewOfComments(objList);
                    }else{
                        getView().hideProgressBarOfComments();
                        getView().initNotAvailableViewOfComments();
                    }
                }else{
                    getView().hideProgressBarOfComments();
                    getView().initNotAvailableViewOfComments();
                }

            }

            @Override
            public void onError(Exception e, String msg) {
                getView().hideProgressBarOfComments();
                getView().initNotAvailableViewOfComments();
            }
        });

    }
}
