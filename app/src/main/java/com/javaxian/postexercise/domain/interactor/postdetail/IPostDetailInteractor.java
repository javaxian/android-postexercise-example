package com.javaxian.postexercise.domain.interactor.postdetail;

import com.javaxian.postexercise.domain.base.BaseResponseList;
import com.javaxian.postexercise.domain.entity.CommentEntity;

public interface IPostDetailInteractor {

    void getCommentList(String postId, BaseResponseList<CommentEntity> callback);
}
