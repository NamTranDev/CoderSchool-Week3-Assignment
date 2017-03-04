package net.fitken.mytwitter.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.codepath.oauth.OAuthLoginActionBarActivity;

import net.fitken.mytwitter.R;
import net.fitken.mytwitter.service.RestClient;

public class LoginActivity extends OAuthLoginActionBarActivity<RestClient> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    @Override
    public void onLoginSuccess() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onLoginFailure(Exception e) {
        e.printStackTrace();
    }

    public void login(View v) {
        getClient().connect();
    }
}
