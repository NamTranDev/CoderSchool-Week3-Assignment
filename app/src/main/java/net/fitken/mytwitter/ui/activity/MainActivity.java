package net.fitken.mytwitter.ui.activity;

import android.databinding.ViewDataBinding;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;

import net.fitken.mytwitter.MyApplication;
import net.fitken.mytwitter.R;
import net.fitken.mytwitter.databinding.ActivityMainBinding;
import net.fitken.mytwitter.databinding.ItemTweetBinding;
import net.fitken.mytwitter.models.TweetModel;
import net.fitken.mytwitter.service.RestClient;
import net.fitken.mytwitter.ui.adapter.AbsBindingAdapter;
import net.fitken.mytwitter.ui.adapter.RecyclerViewClickListener;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends BaseActivity<ActivityMainBinding> {


    private List<TweetModel> mListTweet;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void inject() {
    }

    @Override
    protected void init() {
        mListTweet = new ArrayList<>();
        viewDataBinding.mainRvTweets.setAdapter(new AbsBindingAdapter<ItemTweetBinding>(new RecyclerViewClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }

            @Override
            public void onItemLongClick(View v, int position) {

            }
        }) {
            @Override
            protected int getLayoutResourceId(int viewType) {
                return R.layout.item_tweet;
            }

            @Override
            public void updateBinding(ViewDataBinding binding, int position) {

            }

            @Override
            public int getItemCount() {
                return mListTweet.size();
            }
        });
        RestClient client = MyApplication.getRestClient();
        client.getHomeTimeline(1, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                // Response is automatically parsed into a JSONArray
                Gson gson = new Gson();
                Type listType = new TypeToken<List<TweetModel>>() {
                }.getType();
                mListTweet = gson.fromJson(json.toString(), listType);
                viewDataBinding.mainRvTweets.getAdapter().notifyDataSetChanged();
            }
        });
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
