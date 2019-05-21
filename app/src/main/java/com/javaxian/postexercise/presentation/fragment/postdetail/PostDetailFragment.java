package com.javaxian.postexercise.presentation.fragment.postdetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.javaxian.postexercise.R;
import com.javaxian.postexercise.domain.entity.CommentEntity;
import com.javaxian.postexercise.domain.entity.PostEntity;
import com.javaxian.postexercise.presentation.fragment.AttachFragment;
import com.javaxian.postexercise.presentation.fragment.postdetail.presenter.IPostDetailPresenter;
import com.javaxian.postexercise.presentation.fragment.postdetail.presenter.PostDetailPresenterImpl;
import com.javaxian.postexercise.presentation.view.adapter.CommentListRecycler;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostDetailFragment extends AttachFragment implements IPostDetailPresenter.View{

    private View rootView;
    private PostEntity postEntity;

    private PostDetailPresenterImpl presenter;

    @BindView(R.id.post_title)
    TextView postTitle;

    @BindView(R.id.post_body)
    TextView postBody;

    @BindView(R.id.recycler_comments)
    RecyclerView recyclerView;

    @BindView(R.id.progress_bar_comments)
    ProgressBar progressBar;

    private CommentListRecycler adapter;

    public static PostDetailFragment newInstance(PostEntity postEntity) {
        PostDetailFragment detailFragment = new PostDetailFragment();
        Bundle bundle = new Bundle();
        detailFragment.setArguments(bundle);
        detailFragment.setHasOptionsMenu(true);

        detailFragment.postEntity = postEntity;
        return detailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_detail,container,false);
        ButterKnife.bind(this, rootView);

        initResources();
        initPresenter();

        return rootView;
    }

    private void initResources(){
        LinearLayoutManager llmGral = new LinearLayoutManager(getActivity());
        llmGral.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(llmGral);
    }

    private void initPresenter(){
        presenter = new PostDetailPresenterImpl();
        presenter.setView(this);
        presenter.initialize();

    }

    @Override
    public void initView() {
        postTitle.setText(postEntity.getTitle());
        postBody.setText(postEntity.getBody());

        presenter.onGetComments(String.valueOf(postEntity.getId()));
    }

    @Override
    public void initViewOfComments(ArrayList<CommentEntity> comments) {
        recyclerView.setVisibility(View.VISIBLE);
        adapter = new CommentListRecycler(getActivity(),comments);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initNotAvailableViewOfComments() {

    }

    @Override
    public void updateViewOfComments() {

    }

    @Override
    public void showProgressBarOfComments() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBarOfComments() {
        progressBar.setVisibility(View.GONE);
    }
}
