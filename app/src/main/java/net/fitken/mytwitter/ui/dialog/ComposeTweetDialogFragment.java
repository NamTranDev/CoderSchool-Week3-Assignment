package net.fitken.mytwitter.ui.dialog;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import net.fitken.mytwitter.R;
import net.fitken.mytwitter.databinding.FragmentComposeTweetBinding;

import butterknife.ButterKnife;

/**
 * Created by Ken on 3/5/2017.
 */

public class ComposeTweetDialogFragment extends DialogFragment {
    private FragmentComposeTweetBinding binding;

    public ComposeTweetDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }


    public static ComposeTweetDialogFragment newInstance() {
        ComposeTweetDialogFragment frag = new ComposeTweetDialogFragment();
        Bundle args = new Bundle();
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_compose_tweet, container, false);
        ButterKnife.bind(this, binding.getRoot());
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
