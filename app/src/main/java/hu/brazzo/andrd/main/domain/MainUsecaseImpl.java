package hu.brazzo.andrd.main.domain;

import javax.inject.Inject;

import hu.brazzo.andrd.app.api.ApiSource;
import hu.brazzo.andrd.app.common.Globals;
import hu.brazzo.andrd.app.preferences.PreferencesHelper;
import hu.brazzo.andrd.app.util.RxTransformer;
import hu.brazzo.andrd.main.model.UserProfile;
import rx.Observable;

public class MainUsecaseImpl implements MainUsecase {

    private ApiSource apiSource;
    private PreferencesHelper<String> tokenPreferencesHelper;

    @Inject
    public MainUsecaseImpl(ApiSource apiSource, PreferencesHelper<String> tokenPreferencesHelper) {
        this.apiSource = apiSource;
        this.tokenPreferencesHelper = tokenPreferencesHelper;
    }

    @Override
    public Observable<String> getUserProfile() {
        return tokenPreferencesHelper.get(Globals.APP_USER_PROFILE, String.class)
                .compose(RxTransformer.applyComputationSchedulers());
    }

    @Override
    public Observable<String> saveUserProfile(String userProfile) {
        return tokenPreferencesHelper.save(Globals.APP_USER_PROFILE, userProfile)
                .compose(RxTransformer.applyComputationSchedulers());
    }

    @Override
    public Observable<String> getUserToken() {
        return tokenPreferencesHelper.get(Globals.APP_USER_TOKEN, String.class)
                .compose(RxTransformer.applyComputationSchedulers());
    }

    @Override
    public Observable<String> saveUserToken(String token) {
        return tokenPreferencesHelper.save(Globals.APP_USER_TOKEN, token)
                .compose(RxTransformer.applyComputationSchedulers());
    }

    @Override
    public Observable<Boolean> logOut() {
        return tokenPreferencesHelper.clear();
    }

}
