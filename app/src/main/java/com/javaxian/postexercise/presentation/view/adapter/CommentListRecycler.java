package com.javaxian.postexercise.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.javaxian.postexercise.R;
import com.javaxian.postexercise.domain.base.BaseHolder;
import com.javaxian.postexercise.domain.callback.OnItemRecyclerClickListener;
import com.javaxian.postexercise.domain.entity.CommentEntity;
import com.javaxian.postexercise.domain.entity.PostEntity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentListRecycler extends RecyclerView.Adapter<BaseHolder>{

    private Context context;
    private ArrayList<CommentEntity> data;
    private OnItemRecyclerClickListener listener;

    public CommentListRecycler(Context context, ArrayList<CommentEntity> data) {

        this.context = context;
        this.data = data;
    }



    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_comment_item, parent, false);

        return new CommentListRecycler.ContentHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {

        CommentListRecycler.ContentHolder contentHolder = (CommentListRecycler.ContentHolder) holder;
        CommentEntity entity = data.get(position);

        contentHolder.itemName.setText("Nombre: "+ entity.getName());
        contentHolder.itemEmail.setText("Correo: " + entity.getEmail());
        contentHolder.itemBody.setText(entity.getBody());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnItemRecyclerClickListener(
            OnItemRecyclerClickListener<PostEntity> listener) {
        this.listener = listener;
    }

    protected class ContentHolder extends BaseHolder{

        @BindView(R.id.recycler_comment_item_name)
        TextView itemName;
        @BindView(R.id.recycler_comment_item_email)
        TextView itemEmail;
        @BindView(R.id.recycler_comment_item_body)
        TextView itemBody;


        public ContentHolder(View view) {

            super(view);

            ButterKnife.bind(this, view);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.comment_view_item:
                    if (listener != null) {
                        CommentEntity entity = data.get(getAdapterPosition());
                        listener.onItemRecyclerClick(v, getAdapterPosition(), entity);

                    }
                default:
            }
        }
    }


}
