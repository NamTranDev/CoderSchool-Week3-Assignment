package net.fitken.mytwitter.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import net.fitken.mytwitter.R;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


/**
 * Created by Ken on 2/17/2017.
 */

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {

    protected T viewDataBinding;

    /**
     * setup content layout
     *
     * @return layout id
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * inject dependencies
     */
    protected abstract void inject();

    /**
     * init for data
     */
    protected abstract void init();


    /**
     * start screen
     */
    protected abstract void startScreen();

    /**
     * resume screen
     */
    protected abstract void resumeScreen();

    /**
     * pause screen
     */
    protected abstract void pauseScreen();

    /**
     * destroy screen
     */
    protected abstract void destroyScreen();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        ButterKnife.bind(this, viewDataBinding.getRoot());

        // Find the view_toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the view_toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        inject();
        init();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        startScreen();
    }


    @Override
    protected void onStart() {
        super.onStart();

    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.gc();
        System.runFinalization();
        resumeScreen();

    }

    @Override
    protected void onPause() {
        super.onPause();
        pauseScreen();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.gc();
        System.runFinalization();
        destroyScreen();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        View v = getCurrentFocus();
        if (v instanceof EditText) {
            int[] scoops = new int[2];
            v.getLocationOnScreen(scoops);
            float x = event.getRawX() + v.getLeft() - scoops[0];
            float y = event.getRawY() + v.getTop() - scoops[1];

            if (event.getAction() == MotionEvent.ACTION_UP
                    && (x < v.getLeft() || x >= v.getRight() || y < v.getTop() || y > v
                    .getBottom())) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v
                        .getWindowToken(), 0);
            }
        }
        return super.dispatchTouchEvent(event);
    }

    /**
     * A dialog showing a progress indicator and an optional text message or
     * view.
     */
    protected ProgressDialog mProgressDialog;
    /**
     * Showing a quick little message for the user. It's will be cancel when
     * finish activity.
     */
    private Toast mToast;

    /**
     * show progress dialog.
     *
     * @param msgResId
     */
    public void showProgress(int msgResId) {
        showProgress(msgResId, null);
    }

    /**
     *
     */
    public void showProgress() {
        showProgress(R.string.loading, null);
    }


    /**
     * @param msgResId
     * @param keyListener
     */
    public void showProgress(int msgResId,
                             DialogInterface.OnKeyListener keyListener) {

        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            return;
        }
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);

        if (msgResId != 0) {
            mProgressDialog.setMessage(getResources().getString(msgResId));
        }

        if (keyListener != null) {
            mProgressDialog.setOnKeyListener(keyListener);

        } else {
            mProgressDialog.setCancelable(false);
        }
        mProgressDialog.show();
    }


    /**
     * @param isCancel
     */
    public void setCancelableProgress(boolean isCancel) {
        if (mProgressDialog != null) {
            mProgressDialog.setCancelable(true);
        }
    }

    /**
     * cancel progress dialog.
     */
    public void dismissProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    /**
     * Show message by the Toast object, it will be canceled when finish this
     * activity.
     *
     * @param msg message want to show
     */
    public final void showToastMessage(final CharSequence msg) {
        if (isFinishing())
            return;
        if (mToast == null) {
            mToast = Toast.makeText(getApplicationContext(), "",
                    Toast.LENGTH_LONG);
        }

        if (mToast != null) {
            // Cancel last message toast
            if (mToast.getView().isShown()) {
                mToast.cancel();
            }
            mToast.setText(msg);
            mToast.show();
        }
    }

    /**
     * Show message by the Toast object, it will be canceled when finish this
     * activity.
     *
     * @param resId id of message wants to show
     */
    public final void showToastMessage(final int resId) {
        if (mToast == null) {
            mToast = Toast.makeText(getApplicationContext(), "",
                    Toast.LENGTH_LONG);
        }

        if (mToast != null) {
            if (mToast.getView().isShown()) {
                mToast.cancel();
            }
            mToast.setText(getString(resId));
            mToast.show();
        }
    }

    /**
     * Cancel toast.
     */
    public final void cancelToast() {
        if (mToast != null && mToast.getView().isShown()) {
            mToast.cancel();
        }
    }

    /**
     * Show toast message, the message is not canceled when finish this
     * activity.
     *
     * @param msg message wants to show
     */
    public final void showToastMessageNotRelease(final CharSequence msg) {
        Toast toast = Toast.makeText(getApplicationContext(), "",
                Toast.LENGTH_LONG);
        if (toast != null) {
            // Cancel last message toast
            if (toast.getView().isShown()) {
                toast.cancel();
            }
            toast.setText(msg);
            toast.show();
        }
    }

    /**
     * Show toast message, the message is not canceled when finish this
     * activity.
     *
     * @param resId message wants to show
     */
    public final void showToastMessageNotRelease(final int resId) {
        Toast toast = Toast.makeText(getApplicationContext(), "",
                Toast.LENGTH_LONG);
        if (toast != null) {
            if (!toast.getView().isShown()) {
                toast.cancel();
            }
            toast.setText(getString(resId));
            toast.show();
        }
    }
}
