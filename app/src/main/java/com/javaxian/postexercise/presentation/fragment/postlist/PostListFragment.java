package com.javaxian.postexercise.presentation.fragment.postlist;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.javaxian.postexercise.R;
import com.javaxian.postexercise.domain.callback.OnDialogResultListener;
import com.javaxian.postexercise.domain.callback.OnItemRecyclerClickListener;
import com.javaxian.postexercise.domain.entity.PostEntity;
import com.javaxian.postexercise.presentation.dialog.CreatePostDialog;
import com.javaxian.postexercise.presentation.fragment.AttachFragment;
import com.javaxian.postexercise.presentation.fragment.postdetail.PostDetailFragment;
import com.javaxian.postexercise.presentation.fragment.postlist.presenter.IPostListPresenter;
import com.javaxian.postexercise.presentation.fragment.postlist.presenter.PostListPresenterImpl;
import com.javaxian.postexercise.presentation.view.adapter.PostListRecycler;
import com.javaxian.postexercise.utils.DebugUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.javaxian.postexercise.domain.constant.Constants.REQUEST_CREATE_POST;


public class PostListFragment extends AttachFragment implements IPostListPresenter.View,
        OnItemRecyclerClickListener<PostEntity>, View.OnClickListener, OnDialogResultListener {

    private View rootView;


    @BindView(R.id.layout_recycler)
    View layoutRecycler;
    @BindView(R.id.main_recycler_posts)
    RecyclerView recyclerView;

    @BindView(R.id.layout_progress_bar)
    View layoutProgessBar;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.search_view)
    SearchView searchView;

    @BindView(R.id.floating_add_button)
    FloatingActionButton floatingAddButton;

    private PostListRecycler adapter;

    private PostListPresenterImpl presenter;

    private ArrayList<PostEntity> posts;


    public static PostListFragment newInstance() {
        PostListFragment listFragment = new PostListFragment();
        Bundle bundle = new Bundle();
        listFragment.setArguments(bundle);
        listFragment.setHasOptionsMenu(true);
        return listFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_post_list, container, false);
        ButterKnife.bind(this, rootView);

        initResources();
        initPresenter();

        return rootView;
    }


    private void initResources() {

        searchView.onActionViewExpanded();
        searchView.setIconified(false);
        SearchView.SearchAutoComplete theTextArea = (SearchView.SearchAutoComplete) searchView.findViewById(R.id.search_src_text);
        theTextArea.setTypeface(Typeface.SANS_SERIF);
        theTextArea.setCursorVisible(true);
        theTextArea.setBackground(null);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                try {
                    Integer.parseInt(query);
                    presenter.onGetPostsFromUser(query);
                } catch (NumberFormatException | NullPointerException nfe) {
                    adapter.filterTitle(query);
                }

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.filterTitle(newText);

                return true;
            }
        });

        LinearLayoutManager llmGral = new LinearLayoutManager(getActivity());
        llmGral.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(llmGral);
        recyclerView.requestFocus();

        floatingAddButton.setOnClickListener(this);

    }

    private void initPresenter() {

        presenter = new PostListPresenterImpl();
        presenter.setView(this);
        presenter.initialize();
        presenter.onGetPosts();

    }

    @Override
    public void initView(ArrayList<PostEntity> posts) {
        layoutRecycler.setVisibility(View.VISIBLE);
        adapter = new PostListRecycler(getActivity(), posts);
        adapter.setOnItemRecyclerClickListener(this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void initNotAvailableView() {

    }

    @Override
    public void updateView() {
        presenter.onGetPosts();
    }

    @Override
    public void showProgressBar() {
        layoutProgessBar.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        layoutProgessBar.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemRecyclerClick(View view, int position, PostEntity item) {
        callback.onFragmentChanged(PostDetailFragment.newInstance(item), R.id.container);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.floating_add_button:
                DebugUtils.logDebug("PRESSED BUTTON");
                showCretePostDialog();
                break;
        }
    }

    private void showCretePostDialog() {
        CreatePostDialog dialog = CreatePostDialog.newInstance(null);
        dialog.setOnDialogListener(this);
        dialog.show(getActivity().getSupportFragmentManager(), "dialog_create_post");
    }

    @Override
    public void onDialogResult(int requestCode, int resultCode, Object data) {
        if (resultCode == Activity.RESULT_OK){
            switch (requestCode) {

                case REQUEST_CREATE_POST:

                    PostEntity postEntity = (PostEntity) data;

                    if(postEntity!=null){
                        presenter.onCreatePostToUser(postEntity);
                    }

                default:
            }

        }
    }
}
