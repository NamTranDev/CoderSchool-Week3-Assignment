package net.fitken.mytwitter.ui.activity;

import android.databinding.ViewDataBinding;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import net.fitken.mytwitter.MyApplication;
import net.fitken.mytwitter.R;
import net.fitken.mytwitter.databinding.ActivityMainBinding;
import net.fitken.mytwitter.databinding.ItemTweetBinding;
import net.fitken.mytwitter.models.TweetModel;
import net.fitken.mytwitter.models.TweetModel_Table;
import net.fitken.mytwitter.ui.adapter.AbsBindingAdapter;
import net.fitken.mytwitter.ui.adapter.RecyclerViewClickListener;
import net.fitken.mytwitter.ui.dialog.ComposeTweetDialogFragment;
import net.fitken.mytwitter.ui.widget.DividerItemDecoration;
import net.fitken.mytwitter.utils.AlertDialogUtils;
import net.fitken.mytwitter.utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;


public class MainActivity extends BaseActivity<ActivityMainBinding> {


    private List<TweetModel> mListTweet;
    private int mPage = 1;
    private LinearLayoutManager mLayoutManager;
    private boolean mIsLoading;
    private ComposeTweet handler;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void inject() {
    }

    @Override
    protected void init() {
        handler = () -> {
            viewDataBinding.mainSwipeContainer.setRefreshing(true);
            mPage = 1;
            getTweets();
        };
        mListTweet = new ArrayList<>();
        viewDataBinding.mainRvTweets.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
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
                if (binding instanceof ItemTweetBinding) {
                    ((ItemTweetBinding) binding).setTweet(mListTweet.get(position));
                }
            }

            @Override
            public int getItemCount() {
                return mListTweet.size();
            }

            @Override
            public long getItemId(int position) {
                return mListTweet.get(position).getId();
            }
        });
        viewDataBinding.mainSwipeContainer.setRefreshing(true);
        viewDataBinding.mainSwipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        viewDataBinding.mainSwipeContainer.setOnRefreshListener(() -> {
            mPage = 1;
            getTweets();
        });

        mLayoutManager = ((LinearLayoutManager) viewDataBinding.mainRvTweets.getLayoutManager());
        viewDataBinding.mainRvTweets.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mIsLoading)
                    return;
                if (mLayoutManager.findLastCompletelyVisibleItemPosition() == mListTweet.size() - 4) {
                    mPage++;
                    getTweets();
                    mIsLoading = true;
                }
            }
        });

        getTweets();
    }

    private void getTweets() {
        if (!NetworkUtils.isNetworkAvailable(this)) {
            mIsLoading = false;
            viewDataBinding.mainSwipeContainer.setRefreshing(false);
            if (!mListTweet.isEmpty()) {
                Snackbar.make(viewDataBinding.getRoot(), "Network unavailable!", Snackbar.LENGTH_LONG).show();
                return;
            }
            mListTweet = SQLite.select().
                    from(TweetModel.class).orderBy(TweetModel_Table.id, false).queryList();
            viewDataBinding.mainRvTweets.getAdapter().notifyDataSetChanged();
            return;
        }
        MyApplication.getRestClient().getHomeTimeline(mPage, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                mIsLoading = false;
                viewDataBinding.mainSwipeContainer.setRefreshing(false);
                if (mPage == 1) {
                    mListTweet.clear();
                }
                // Response is automatically parsed into a JSONArray
                Gson gson = new Gson();
                Type listType = new TypeToken<List<TweetModel>>() {
                }.getType();
                List<TweetModel> results = gson.fromJson(json.toString(), listType);
                mListTweet.addAll(results);
                if (mPage == 1) {
                    viewDataBinding.mainRvTweets.getAdapter().notifyDataSetChanged();
                } else {
                    viewDataBinding.mainRvTweets.getAdapter().notifyItemInserted(mListTweet.size() - results.size());
                }
                //save to database
//                mListTweet.forEach(BaseModel::save);
                new AsyncSaveTweetToDb().execute(results);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                mIsLoading = false;
                viewDataBinding.mainSwipeContainer.setRefreshing(false);
                super.onFailure(statusCode, headers, throwable, errorResponse);
                AlertDialogUtils.showError(MainActivity.this, throwable.getMessage());
            }
        });
    }

    @OnClick(R.id.fab_compose_tweet)
    public void composeTweet(View view) {
        FragmentManager fm = getSupportFragmentManager();
        ComposeTweetDialogFragment composeTweetDialogFragment = ComposeTweetDialogFragment.newInstance("");
        composeTweetDialogFragment.show(fm, ComposeTweetDialogFragment.class.getSimpleName());
        composeTweetDialogFragment.setHandler(handler);
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

    public class AsyncSaveTweetToDb extends AsyncTask<List<TweetModel>, Void, Void> {

        @SafeVarargs
        @Override
        protected final Void doInBackground(List<TweetModel>... params) {
            (params[0]).forEach(BaseModel::save);
            return null;
        }
    }
}
