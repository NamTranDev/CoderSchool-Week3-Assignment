package net.fitken.mytwitter;

import android.app.Application;
import android.content.Context;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowManager;

import net.fitken.mytwitter.service.RestClient;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


/**
 * Created by Ken on 2/16/2017.
 */

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Omnes-Regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        FlowManager.init(new FlowConfig.Builder(this).build());
        FlowLog.setMinimumLoggingLevel(FlowLog.Level.V);
        MyApplication.context = this;
    }


    public static RestClient getRestClient() {
        return (RestClient) RestClient.getInstance(RestClient.class, MyApplication.context);
    }
}
