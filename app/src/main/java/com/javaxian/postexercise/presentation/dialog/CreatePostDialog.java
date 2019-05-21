package com.javaxian.postexercise.presentation.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.javaxian.postexercise.R;
import com.javaxian.postexercise.domain.base.BaseDialog;
import com.javaxian.postexercise.domain.entity.PostEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.javaxian.postexercise.domain.constant.Constants.DATA;
import static com.javaxian.postexercise.domain.constant.Constants.REQUEST_CREATE_POST;

public class CreatePostDialog extends BaseDialog implements View.OnClickListener {

    private View rootView;

    @BindView(R.id.create_title_text_input_layout)
    TextInputLayout createTitleTextInputLayout;
    @BindView(R.id.create_title_edit_text)
    EditText createTitleEditText;

    @BindView(R.id.create_body_text_input_layout)
    TextInputLayout createBodyTextInputLayout;
    @BindView(R.id.create_body_edit_text)
    EditText createBodyEditText;

    @BindView(R.id.create_post_button)
    Button createPostButton;

    @BindView(R.id.create_post_text_view)
    TextView createPostTextView;

    @BindView(R.id.dialog_content)
    View dialogContent;

    public static CreatePostDialog newInstance(Bundle bundle) {
        CreatePostDialog dialog = new CreatePostDialog();
        dialog.setHasOptionsMenu(true);
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new Dialog(getActivity(), getTheme()) {
            @Override
            public void onBackPressed() {
                dismissAndContentAnimation(dialogContent, R.anim.shrink);
            }
        };
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.dialog_create_post, container, false);
        ButterKnife.bind(this, rootView);
        initResources();
        return rootView;
    }

    private void initResources() {
        createPostButton.setOnClickListener(this);
        createTitleEditText.addTextChangedListener(getTextWatcher());
        createBodyEditText.addTextChangedListener(getTextWatcher());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_post_button:


                PostEntity postEntity = new PostEntity();
                postEntity.setTitle(createTitleEditText.getText().toString());
                postEntity.setBody(createBodyEditText.getText().toString());
                postEntity.setUserId(1); //Por recomendación en el documento del ejercicio se utiliza este id de usuario de forma predeterminada

                Bundle args = new Bundle();
                args.putParcelable(DATA, postEntity);
                result.onDialogResult(REQUEST_CREATE_POST, Activity.RESULT_OK, postEntity);
                dismissAndContentAnimation(dialogContent, R.anim.shrink);


                break;
        }
    }

    private TextWatcher getTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (createTitleEditText.getText().toString().trim().length() > 0 &&
                        createBodyEditText.getText().toString().trim().length() > 0) {
                    createPostButton.setEnabled(true);
                } else {
                    createPostButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }
}
