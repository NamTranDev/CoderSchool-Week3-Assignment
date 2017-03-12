package net.fitken.mytwitter.ui.activity;

import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import net.fitken.mytwitter.R;
import net.fitken.mytwitter.databinding.ActivityMainBinding;
import net.fitken.mytwitter.ui.adapter.MainTabsAdapter;
import net.fitken.mytwitter.ui.base.BaseActivity;
import net.fitken.mytwitter.ui.dialog.ComposeTweetDialogFragment;

import butterknife.OnClick;


public class MainActivity extends BaseActivity<ActivityMainBinding> {
    private MainTabsAdapter mHomeAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void inject() {
    }

    @Override
    protected void init() {
        mHomeAdapter = new MainTabsAdapter(getSupportFragmentManager());
        viewDataBinding.mainViewpager.setAdapter(mHomeAdapter);
        viewDataBinding.mainViewpager.setOffscreenPageLimit(mHomeAdapter.getCount());
        viewDataBinding.mainSlidingTabs.setupWithViewPager(viewDataBinding.mainViewpager);
    }


    @OnClick(R.id.fab_compose_tweet)
    public void composeTweet(View view) {
        FragmentManager fm = getSupportFragmentManager();
        ComposeTweetDialogFragment composeTweetDialogFragment = ComposeTweetDialogFragment.newInstance("");
        composeTweetDialogFragment.show(fm, ComposeTweetDialogFragment.class.getSimpleName());
//        composeTweetDialogFragment.setHandler(handler);
    }


    @Override
    protected void startScreen() {

    }

    @Override
    protected void resumeScreen() {
    }

    @Override
    protected void pauseScreen() {

    }

    @Override
    protected void destroyScreen() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    public interface ComposeTweet {
        void onPostedNewTweet();
    }


}
