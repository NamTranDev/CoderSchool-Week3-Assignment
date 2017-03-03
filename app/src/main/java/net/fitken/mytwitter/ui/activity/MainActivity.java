package net.fitken.mytwitter.ui.activity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import net.fitken.mytwitter.R;
import net.fitken.mytwitter.databinding.ActivityMainBinding;


public class MainActivity extends BaseActivity<ActivityMainBinding> {



    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void inject(){
    }

    @Override
    protected void init() {
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


}
