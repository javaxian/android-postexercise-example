package com.javaxian.postexercise.domain.interactor.postlist;

import com.javaxian.postexercise.domain.base.BaseResponse;
import com.javaxian.postexercise.domain.base.BaseResponseList;
import com.javaxian.postexercise.domain.entity.PostEntity;

public interface IPostListInteractor {

    void getPostList(BaseResponseList<PostEntity> callback);
    void getPostListFromUser(String userId, BaseResponseList<PostEntity> callback);
    void createPostToUser(PostEntity postEntity, BaseResponse callback);
}
