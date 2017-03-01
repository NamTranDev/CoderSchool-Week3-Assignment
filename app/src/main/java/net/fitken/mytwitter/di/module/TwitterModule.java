package net.fitken.mytwitter.di.module;


import net.fitken.mytwitter.di.scope.UserScope;
import net.fitken.mytwitter.service.TwitterApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Ken on 2/16/2017.
 */

@Module
public class TwitterModule {
    @Provides
    @UserScope
    public TwitterApi provideTheMovieApi(Retrofit retrofit) {
        return retrofit.create(TwitterApi.class);
    }
}
