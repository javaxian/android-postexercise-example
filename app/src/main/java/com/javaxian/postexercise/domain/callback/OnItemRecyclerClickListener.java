package com.javaxian.postexercise.domain.callback;

import android.view.View;

public interface OnItemRecyclerClickListener<T> {

    /**
     * Interface definition for a callback to be invoked when a view is clicked.
     *
     * @param view the view that was clicked
     * @param position The position of the view that was clicked
     * @param item The object in the position of the view
     */
    void onItemRecyclerClick(View view, int position, T item);
}
