package com.javaxian.postexercise.presentation.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.javaxian.postexercise.R;
import com.javaxian.postexercise.domain.callback.OnFragmentChangedListener;
import com.javaxian.postexercise.presentation.fragment.postlist.PostListFragment;

public class PostListActivity extends AppCompatActivity implements OnFragmentChangedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setTitle("Lista de Posts");
        initFragment();
    }


    @Override
    public void onFragmentChanged(Fragment fragment, int container) {
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(container, fragment, fragment.getClass().getName())
                .addToBackStack(fragment.getClass().getName())
                .commit();
    }

    private void initFragment(){

        Fragment fragment = PostListFragment.newInstance();

        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container, fragment)
                .commit();
    }
}
