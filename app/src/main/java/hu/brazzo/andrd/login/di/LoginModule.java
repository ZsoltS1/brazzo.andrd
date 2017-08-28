package hu.brazzo.andrd.login.di;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import hu.brazzo.andrd.app.Activity;
import hu.brazzo.andrd.app.api.ApiSource;
import hu.brazzo.andrd.app.preferences.PreferencesHelper;
import hu.brazzo.andrd.app.preferences.PreferencesHelperImpl;
import hu.brazzo.andrd.login.domain.LoginUsecase;
import hu.brazzo.andrd.login.domain.LoginUsecaseImpl;
import hu.brazzo.andrd.login.viewmodel.LoginViewModel;

@Module
public class LoginModule {

    LoginViewModel.LoginListener loginListener;

    public LoginModule(LoginViewModel.LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    @Provides
    @Activity
    PreferencesHelper<String> provideTokenPreferencesHelper(SharedPreferences sharedPreferences, Gson gson) {
        return new PreferencesHelperImpl<>(sharedPreferences, gson);
    }

    @Provides
    @Activity
    LoginUsecase provideLoginUsecase(ApiSource apiSource, PreferencesHelper<String> tokenPreferencesHelper) {
        return new LoginUsecaseImpl(apiSource, tokenPreferencesHelper);
    }

    @Provides
    @Activity
    LoginViewModel provideLoginViewModel(LoginUsecase loginUsecase) {
        return new LoginViewModel(loginUsecase, loginListener);
    }

}
