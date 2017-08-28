package hu.brazzo.andrd.app;

import android.app.Application;

import hu.brazzo.andrd.app.di.AppComponent;
import hu.brazzo.andrd.app.di.AppModule;
import hu.brazzo.andrd.app.di.DaggerAppComponent;
import hu.brazzo.andrd.app.di.NetworkModule;

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
