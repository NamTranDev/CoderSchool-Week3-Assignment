package net.fitken.mytwitter.ui.fragment;

import android.databinding.ViewDataBinding;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.raizlabs.android.dbflow.structure.BaseModel;

import net.fitken.mytwitter.MyApplication;
import net.fitken.mytwitter.R;
import net.fitken.mytwitter.databinding.FragmentMentionBinding;
import net.fitken.mytwitter.databinding.ItemTweetBinding;
import net.fitken.mytwitter.models.TweetModel;
import net.fitken.mytwitter.ui.adapter.AbsBindingAdapter;
import net.fitken.mytwitter.ui.adapter.RecyclerViewClickListener;
import net.fitken.mytwitter.ui.base.BaseFragment;
import net.fitken.mytwitter.ui.widget.DividerItemDecoration;
import net.fitken.mytwitter.utils.AlertDialogUtils;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Ken on 3/12/2017.
 */

public class MentionFragment extends BaseFragment<FragmentMentionBinding> {

    private List<TweetModel> mListMention;
    private int mPage = 1;
    private LinearLayoutManager mLayoutManager;
    private boolean mIsLoading;

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
        mListMention = new ArrayList<>();
        viewDataBinding.mentionRvMentions.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        viewDataBinding.mentionRvMentions.setAdapter(new AbsBindingAdapter<ItemTweetBinding>(new RecyclerViewClickListener() {
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
                    ((ItemTweetBinding) binding).setTweet(mListMention.get(position));
                }
            }

            @Override
            public int getItemCount() {
                return mListMention.size();
            }

            @Override
            public long getItemId(int position) {
                return position;
            }
        });
        viewDataBinding.mentionSwipeContainer.setRefreshing(true);
        viewDataBinding.mentionSwipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        viewDataBinding.mentionSwipeContainer.setOnRefreshListener(() -> {
            mPage = 1;
            getMentions();
        });

        mLayoutManager = ((LinearLayoutManager) viewDataBinding.mentionRvMentions.getLayoutManager());
        viewDataBinding.mentionRvMentions.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mIsLoading)
                    return;
                if (mLayoutManager.findLastCompletelyVisibleItemPosition() == mListMention.size() - 4) {
                    mPage++;
                    getMentions();
                    mIsLoading = true;
                }
            }
        });

        getMentions();
    }

    private void getMentions() {
        MyApplication.getRestClient().getMentions(mPage, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                mIsLoading = false;
                viewDataBinding.mentionSwipeContainer.setRefreshing(false);
                if (mPage == 1) {
                    mListMention.clear();
                }
                // Response is automatically parsed into a JSONArray
                Gson gson = new Gson();
                Type listType = new TypeToken<List<TweetModel>>() {
                }.getType();
                List<TweetModel> results = gson.fromJson(response.toString(), listType);
                mListMention.addAll(results);
                if (mPage == 1) {
                    viewDataBinding.mentionRvMentions.getAdapter().notifyDataSetChanged();
                } else {
                    viewDataBinding.mentionRvMentions.getAdapter().notifyItemInserted(mListMention.size() - results.size());
                }
                //save to database
//                mListTweet.forEach(BaseModel::save);
                new AsyncSaveTweetToDb().execute(results);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                mIsLoading = false;
                viewDataBinding.mentionSwipeContainer.setRefreshing(false);
                AlertDialogUtils.showError(getActivity(), throwable.getMessage());
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

    @Override
    protected void screenResume() {

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
