package hu.brazzo.andrd.main.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import hu.brazzo.andrd.app.Activity;
import hu.brazzo.andrd.app.api.ApiSource;
import hu.brazzo.andrd.app.preferences.PreferencesHelper;
import hu.brazzo.andrd.app.preferences.PreferencesHelperImpl;
import hu.brazzo.andrd.app.util.RxBus;
import hu.brazzo.andrd.main.domain.MainUsecase;
import hu.brazzo.andrd.main.domain.MainUsecaseImpl;
import hu.brazzo.andrd.main.viewmodel.MainViewModel;

@Module
public class MainModule {

    Context context;
    MainViewModel.MainListener mainListener;

    public MainModule(Context context, MainViewModel.MainListener mainListener) {
        this.context = context;
        this.mainListener = mainListener;
    }

    @Provides
    @Activity
    PreferencesHelper<String> provideTokenPreferencesHelper(SharedPreferences sharedPreferences, Gson gson) {
        return new PreferencesHelperImpl<>(sharedPreferences, gson);
    }

    @Provides
    @Activity
    MainUsecase provideMainUsecase(ApiSource apiSource, PreferencesHelper<String> tokenPreferencesHelper) {
        return new MainUsecaseImpl(apiSource, tokenPreferencesHelper);
    }

    @Provides
    @Activity
    MainViewModel provideMainViewModel(RxBus rxBus, MainUsecase mainUsecase) {
        return new MainViewModel(context, rxBus, mainUsecase, mainListener);
    }
}
