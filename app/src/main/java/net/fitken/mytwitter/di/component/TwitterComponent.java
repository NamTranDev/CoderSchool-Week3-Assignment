package net.fitken.mytwitter.di.component;


import net.fitken.mytwitter.di.module.TwitterModule;
import net.fitken.mytwitter.di.scope.UserScope;
import net.fitken.mytwitter.ui.activity.MainActivity;

import dagger.Component;

/**
 * Created by Ken on 2/17/2017.
 */

@UserScope
@Component(modules = TwitterModule.class, dependencies = NetComponent.class)
public interface TwitterComponent {
    void inject(MainActivity mainActivity);


}