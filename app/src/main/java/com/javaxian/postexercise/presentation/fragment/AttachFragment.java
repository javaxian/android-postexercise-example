package com.javaxian.postexercise.presentation.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.javaxian.postexercise.domain.callback.OnFragmentChangedListener;

public class AttachFragment extends Fragment {

    public OnFragmentChangedListener callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (OnFragmentChangedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement FragmentListener");
        }
    }
}
