package net.fitken.mytwitter.di.component;

import android.content.SharedPreferences;


import net.fitken.mytwitter.di.module.AppModule;
import net.fitken.mytwitter.di.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Ken on 2/17/2017.
 */


@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    // downstream components need these exposed
    Retrofit retrofit();

    OkHttpClient okHttpClient();

    SharedPreferences sharedPreferences();
}