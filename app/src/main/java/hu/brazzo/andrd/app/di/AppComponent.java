package hu.brazzo.andrd.app.di;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import hu.brazzo.andrd.app.api.ApiSource;
import hu.brazzo.andrd.app.util.RxBus;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    ApiSource apiSource();

    SharedPreferences sharedPreferences();

    Gson gson();

    RxBus bus();

}

