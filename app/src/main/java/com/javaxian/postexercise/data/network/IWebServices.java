package com.javaxian.postexercise.data.network;

import com.javaxian.postexercise.data.network.request.RequestCreatePost;
import com.javaxian.postexercise.data.network.response.ResponseCommentList;
import com.javaxian.postexercise.data.network.response.ResponsePostList;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IWebServices {

    @GET(EndPointConf.GET_ALL_POSTS)
    Observable<ArrayList<ResponsePostList>> getAllPost();

    @GET(EndPointConf.GET_POST_BY_ID)
    Observable<ArrayList<ResponsePostList>> getPostById(@Path(value = "id", encoded = true) String id);

    @GET(EndPointConf.GET_COMMENTS_FROM_POST)
    Observable<ArrayList<ResponseCommentList>> getCommentsFromPost(@Query("postId") String postId);

    @GET(EndPointConf.GET_POSTS_FROM_USER)
    Observable<ArrayList<ResponsePostList>> getPostsFromUser(@Query("userId") String userId);

    @POST(EndPointConf.CREATE_POST_TO_USER)
    Observable<ResponsePostList> createPostToUser(@Body RequestCreatePost request);

    @PUT(EndPointConf.UPDATE_POST_TO_USER)
    Observable<ResponsePostList> updatePostToUser(@Body RequestCreatePost request);

}
