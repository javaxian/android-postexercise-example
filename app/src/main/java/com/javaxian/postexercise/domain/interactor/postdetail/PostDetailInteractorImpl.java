package com.javaxian.postexercise.domain.interactor.postdetail;

import android.util.Log;

import com.javaxian.postexercise.Application;
import com.javaxian.postexercise.data.network.IWebServices;
import com.javaxian.postexercise.data.network.response.ResponseCommentList;
import com.javaxian.postexercise.domain.base.BaseResponseList;
import com.javaxian.postexercise.domain.entity.CommentEntity;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class PostDetailInteractorImpl implements IPostDetailInteractor {

    @Inject
    Retrofit retrofit;

    public PostDetailInteractorImpl(){
        Application.getInstance().getComponents().inject(this);
    }

    @Override
    public void getCommentList(String postId,  final BaseResponseList<CommentEntity> callback) {

        Observable<ArrayList<ResponseCommentList>> request = retrofit.create(IWebServices.class).getCommentsFromPost(postId);
        request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<ResponseCommentList>>() {

                    ArrayList<CommentEntity> comments = new ArrayList<CommentEntity>();
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(ArrayList<ResponseCommentList> response) {

                        Log.i("NEXT: ",response.toString());

                        for (ResponseCommentList item: response) {

                            CommentEntity entity = new CommentEntity();

                            entity.setId(item.getId());
                            entity.setBody(item.getBody());
                            entity.setEmail(item.getEmail());
                            entity.setPostId(item.getPostId());
                            entity.setName(item.getName());

                            comments.add(entity);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.i("FAILURE: ",e.getMessage());
                        callback.onError(new Exception(),e.getMessage());
                    }
                    @Override
                    public void onComplete() {
                        Log.i("COMPLETE: ","READY!!!");

                        callback.onSuccess(comments);
                    }
                });

    }
}
