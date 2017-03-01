package net.fitken.mytwitter;

import android.app.Application;

import net.fitken.mytwitter.common.Constant;
import net.fitken.mytwitter.di.component.DaggerNetComponent;
import net.fitken.mytwitter.di.component.DaggerTwitterComponent;
import net.fitken.mytwitter.di.component.NetComponent;
import net.fitken.mytwitter.di.component.TwitterComponent;
import net.fitken.mytwitter.di.module.AppModule;
import net.fitken.mytwitter.di.module.NetModule;
import net.fitken.mytwitter.di.module.TwitterModule;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Ken on 2/16/2017.
 */

public class MyApplication extends Application {
    private TwitterComponent mTwitterComponent;
    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mNetComponent = DaggerNetComponent.builder().appModule(new AppModule(this))
                .netModule(new NetModule(Constant.URL))
                .build();

        mTwitterComponent = DaggerTwitterComponent.builder()
                .netComponent(mNetComponent)
                .twitterModule(new TwitterModule())
                .build();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Omnes/Omnes-Regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

    }

    public TwitterComponent getTwitterComponent() {
        return mTwitterComponent;
    }
}
