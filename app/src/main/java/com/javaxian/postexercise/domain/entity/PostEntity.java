package com.javaxian.postexercise.domain.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PostEntity implements Serializable, Parcelable {

    private int userId;

    private int id;

    private String title;

    private String body;

    public PostEntity(){

    }

    protected PostEntity(Parcel in) {
        id = in.readInt();
        userId = in.readInt();
        title = in.readString();
        body = in.readString();

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(userId);
        dest.writeString(title);
        dest.writeString(body);
    }

    public static final Creator<PostEntity> CREATOR = new Creator<PostEntity>() {
        @Override
        public PostEntity createFromParcel(Parcel in) {
            return new PostEntity(in);
        }

        @Override
        public PostEntity[] newArray(int size) {
            return new PostEntity[size];
        }
    };
}
