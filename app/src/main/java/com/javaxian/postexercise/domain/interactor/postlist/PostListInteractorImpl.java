package com.javaxian.postexercise.domain.interactor.postlist;

import android.util.Log;

import com.javaxian.postexercise.Application;
import com.javaxian.postexercise.data.network.IWebServices;
import com.javaxian.postexercise.data.network.request.RequestCreatePost;
import com.javaxian.postexercise.data.network.response.ResponsePostList;
import com.javaxian.postexercise.domain.base.BaseResponse;
import com.javaxian.postexercise.domain.base.BaseResponseList;
import com.javaxian.postexercise.domain.entity.PostEntity;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class PostListInteractorImpl implements IPostListInteractor {

    @Inject
    Retrofit retrofit;

    public PostListInteractorImpl() {
        Application.getInstance().getComponents().inject(this);
    }

    @Override
    public void getPostList(final BaseResponseList<PostEntity> callback) {


        Observable<ArrayList<ResponsePostList>> request = retrofit.create(IWebServices.class).getAllPost();
        request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<ResponsePostList>>() {

                    ArrayList<PostEntity> posts = new ArrayList<PostEntity>();

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArrayList<ResponsePostList> response) {

                        Log.i("NEXT: ", response.toString());

                        for (ResponsePostList item : response) {

                            PostEntity entity = new PostEntity();

                            entity.setId(item.getId());
                            entity.setBody(item.getBody());
                            entity.setTitle(item.getTitle());
                            entity.setUserId(item.getUserId());

                            posts.add(entity);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("FAILURE: ", e.getMessage());
                        callback.onError(new Exception(), e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i("COMPLETE: ", "READY!!!");

                        callback.onSuccess(posts);
                    }
                });
    }

    @Override
    public void getPostListFromUser(String userId, final BaseResponseList<PostEntity> callback) {
        Observable<ArrayList<ResponsePostList>> request = retrofit.create(IWebServices.class).getPostsFromUser(userId);
        request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<ResponsePostList>>() {

                    ArrayList<PostEntity> posts = new ArrayList<PostEntity>();

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArrayList<ResponsePostList> response) {

                        Log.i("NEXT: ", response.toString());

                        for (ResponsePostList item : response) {

                            PostEntity entity = new PostEntity();

                            entity.setId(item.getId());
                            entity.setBody(item.getBody());
                            entity.setTitle(item.getTitle());
                            entity.setUserId(item.getUserId());

                            posts.add(entity);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("FAILURE: ", e.getMessage());
                        callback.onError(new Exception(), e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i("COMPLETE: ", "READY!!!");

                        callback.onSuccess(posts);
                    }
                });
    }

    @Override
    public void createPostToUser(final PostEntity postEntity, final BaseResponse callback) {

        RequestCreatePost requestObject = new RequestCreatePost();
        requestObject.setUserId(postEntity.getUserId());
        requestObject.setBody(postEntity.getBody());
        requestObject.setTitle(postEntity.getTitle());


        Observable<ResponsePostList> request = retrofit.create(IWebServices.class).createPostToUser(requestObject);
        request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponsePostList>() {

                    PostEntity post = new PostEntity();

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponsePostList response) {

                        Log.i("NEXT: ", response.toString());
                        postEntity.setId(response.getId());
                        postEntity.setUserId(response.getUserId());
                        postEntity.setTitle(response.getTitle());
                        postEntity.setBody(response.getBody());

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("FAILURE: ", e.getMessage());
                        callback.onError(new Exception(e.getMessage()));
                    }

                    @Override
                    public void onComplete() {
                        Log.i("COMPLETE: ", "READY!!!");
                        if(postEntity!=null && postEntity.getUserId()>0){
                            callback.onSuccess();
                        }else {
                            callback.onError(new Exception("El Post no pudo ser creado, favor de intentar de nuevo más tarde"));
                        }

                    }
                });


    }
}
