package net.fitken.mytwitter.ui.dialog;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.loopj.android.http.AsyncHttpResponseHandler;

import net.fitken.mytwitter.MyApplication;
import net.fitken.mytwitter.R;
import net.fitken.mytwitter.databinding.FragmentComposeTweetBinding;
import net.fitken.mytwitter.ui.activity.MainActivity;
import net.fitken.mytwitter.utils.AlertDialogUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Ken on 3/5/2017.
 */

public class ComposeTweetDialogFragment extends DialogFragment {
    public static final String IMG_URL_PROFILE = "imgUrlProfile";
    private FragmentComposeTweetBinding binding;
    private MainActivity.ComposeTweet handler;

    public ComposeTweetDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }


    public static ComposeTweetDialogFragment newInstance(String imgUrlProfile) {
        ComposeTweetDialogFragment frag = new ComposeTweetDialogFragment();
        Bundle args = new Bundle();
        args.putString(IMG_URL_PROFILE, imgUrlProfile);
        frag.setArguments(args);
        return frag;
    }

    public void setHandler(MainActivity.ComposeTweet handler) {
        this.handler = handler;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_compose_tweet, container, false);
        ButterKnife.bind(this, binding.getRoot());
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        binding.setImgUrlProfile(getArguments().getString(IMG_URL_PROFILE));
        getDialog().setCancelable(false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.setCancelable(false);
        }
    }

    @OnClick(R.id.img_close)
    public void close(View view) {
        dismiss();
    }

    @OnClick(R.id.btn_submit_tweet)
    public void submitTweet(View view) {
        MyApplication.getRestClient().postTweet(binding.etTweetContent.getText().toString(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                dismiss();
                if (handler != null) {
                    handler.onPostedNewTweet();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                AlertDialogUtils.showError(getActivity(), error.getMessage());
            }
        });
    }
}
