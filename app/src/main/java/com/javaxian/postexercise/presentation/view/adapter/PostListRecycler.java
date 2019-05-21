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
import com.javaxian.postexercise.domain.entity.PostEntity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostListRecycler extends RecyclerView.Adapter<BaseHolder>{

    private Context context;
    private ArrayList<PostEntity> data;
    private ArrayList<PostEntity> dataBackup;
    private OnItemRecyclerClickListener listener;

    public PostListRecycler(Context context, ArrayList<PostEntity> data) {

        this.context = context;
        this.data = data;
        this.dataBackup = new ArrayList<PostEntity>();
        this.dataBackup.addAll(data);
    }



    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_post_item, parent, false);

        return new PostListRecycler.ContentHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {

        ContentHolder contentHolder = (ContentHolder) holder;
        PostEntity entity = data.get(position);

        contentHolder.itemTitle.setText(entity.getTitle());
        contentHolder.itemUserID.setText("User ID: " + entity.getUserId());
        contentHolder.itemID.setText("ID: " + entity.getId());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnItemRecyclerClickListener(
            OnItemRecyclerClickListener<PostEntity> listener) {
        this.listener = listener;
    }

    public void filterTitle(String title){

        data.clear();

        if(title.isEmpty()){
            data.addAll(dataBackup);
        }else{
            title = title.toLowerCase();

            for (PostEntity postEntity:dataBackup) {
                if(postEntity.getTitle().toLowerCase().contains(title)){
                    data.add(postEntity);
                }
            }
        }

        notifyDataSetChanged();
    }

    protected class ContentHolder extends BaseHolder{

        @BindView(R.id.recycler_post_item_title)
        TextView itemTitle;
        @BindView(R.id.recycler_post_item_body)
        TextView itemBody;
        @BindView(R.id.recycler_post_item_user_id)
        TextView itemUserID;
        @BindView(R.id.recycler_post_item_id)
        TextView itemID;

        public ContentHolder(View view) {

            super(view);

            ButterKnife.bind(this, view);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.post_view_item:
                    if (listener != null) {
                        PostEntity entity = data.get(getAdapterPosition());
                        listener.onItemRecyclerClick(v, getAdapterPosition(), entity);

                    }
                default:
            }
        }
    }
}
