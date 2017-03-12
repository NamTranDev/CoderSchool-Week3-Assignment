package net.fitken.mytwitter.ui.fragment;

import android.support.annotation.Nullable;
import android.view.View;

import net.fitken.mytwitter.R;
import net.fitken.mytwitter.databinding.FragmentMentionBinding;
import net.fitken.mytwitter.ui.base.BaseFragment;

/**
 * Created by Ken on 3/12/2017.
 */

public class MentionFragment extends BaseFragment<FragmentMentionBinding> {


    public static MentionFragment newInstance() {
        MentionFragment fragment = new MentionFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mention;
    }

    @Override
    protected void init(@Nullable View view) {

    }

    @Override
    protected void screenResume() {

    }
}
