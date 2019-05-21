package com.javaxian.postexercise.data.network;

public class EndPointConf {

    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    public static final String GET_ALL_POSTS = "posts";
    public static final String GET_POST_BY_ID = "posts/{id}";
    public static final String GET_ALL_COMMENTS = "posts/1/comments";
    public static final String GET_COMMENTS_FROM_POST = "comments";
    public static final String GET_POSTS_FROM_USER = "posts";
    public static final String CREATE_POST_TO_USER = "posts";
    public static final String UPDATE_POST_TO_USER = "posts/1";
}
