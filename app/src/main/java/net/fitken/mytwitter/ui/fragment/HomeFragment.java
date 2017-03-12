package net.fitken.mytwitter.ui.fragment;

import android.databinding.ViewDataBinding;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import net.fitken.mytwitter.MyApplication;
import net.fitken.mytwitter.R;
import net.fitken.mytwitter.databinding.FragmentHomeBinding;
import net.fitken.mytwitter.databinding.ItemTweetBinding;
import net.fitken.mytwitter.models.TweetModel;
import net.fitken.mytwitter.models.TweetModel_Table;
import net.fitken.mytwitter.ui.adapter.AbsBindingAdapter;
import net.fitken.mytwitter.ui.adapter.RecyclerViewClickListener;
import net.fitken.mytwitter.ui.base.BaseFragment;
import net.fitken.mytwitter.ui.widget.DividerItemDecoration;
import net.fitken.mytwitter.utils.AlertDialogUtils;
import net.fitken.mytwitter.utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Ken on 3/12/2017.
 */

public class HomeFragment extends BaseFragment<FragmentHomeBinding> {

    private List<TweetModel> mListTweet;
    private int mPage = 1;
    private LinearLayoutManager mLayoutManager;
    private boolean mIsLoading;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init(@Nullable View view) {
        mListTweet = new ArrayList<>();
        viewDataBinding.homeRvTweets.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        viewDataBinding.homeRvTweets.setAdapter(new AbsBindingAdapter<ItemTweetBinding>(new RecyclerViewClickListener() {
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
        viewDataBinding.homeSwipeContainer.setRefreshing(true);
        viewDataBinding.homeSwipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        viewDataBinding.homeSwipeContainer.setOnRefreshListener(() -> {
            mPage = 1;
            getTweets();
        });

        mLayoutManager = ((LinearLayoutManager) viewDataBinding.homeRvTweets.getLayoutManager());
        viewDataBinding.homeRvTweets.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

    @Override
    protected void screenResume() {

    }

    private void getTweets() {
        if (!NetworkUtils.isNetworkAvailable(getActivity())) {
            mIsLoading = false;
            viewDataBinding.homeSwipeContainer.setRefreshing(false);
            if (!mListTweet.isEmpty()) {
                Snackbar.make(viewDataBinding.getRoot(), "Network unavailable!", Snackbar.LENGTH_LONG).show();
                return;
            }
            mListTweet = SQLite.select().
                    from(TweetModel.class).orderBy(TweetModel_Table.id, false).queryList();
            viewDataBinding.homeRvTweets.getAdapter().notifyDataSetChanged();
            return;
        }
        MyApplication.getRestClient().getHomeTimeline(mPage, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                mIsLoading = false;
                viewDataBinding.homeSwipeContainer.setRefreshing(false);
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
                    viewDataBinding.homeRvTweets.getAdapter().notifyDataSetChanged();
                } else {
                    viewDataBinding.homeRvTweets.getAdapter().notifyItemInserted(mListTweet.size() - results.size());
                }
                //save to database
//                mListTweet.forEach(BaseModel::save);
                new AsyncSaveTweetToDb().execute(results);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                mIsLoading = false;
                viewDataBinding.homeSwipeContainer.setRefreshing(false);
                super.onFailure(statusCode, headers, throwable, errorResponse);
                AlertDialogUtils.showError(getActivity(), throwable.getMessage());
            }
        });
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
