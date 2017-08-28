package hu.brazzo.andrd.login.domain;

import javax.inject.Inject;

import hu.brazzo.andrd.app.api.ApiSource;
import hu.brazzo.andrd.app.common.Globals;
import hu.brazzo.andrd.app.preferences.PreferencesHelper;
import hu.brazzo.andrd.app.util.RxTransformer;
import hu.brazzo.andrd.main.model.UserProfile;
import retrofit2.Response;
import rx.Observable;

public class LoginUsecaseImpl implements LoginUsecase {

    private ApiSource apiSource;
    private PreferencesHelper<String> tokenHelper;

    @Inject
    public LoginUsecaseImpl(ApiSource apiSource, PreferencesHelper<String> tokenHelper) {
        this.apiSource = apiSource;
        this.tokenHelper = tokenHelper;
    }

    @Override
    public Observable<String> saveUserToken(String token) {
        return tokenHelper.save(Globals.APP_USER_TOKEN, token)
                .compose(RxTransformer.applyComputationSchedulers());
    }

    @Override
    public Observable<String> saveUserProfile(String userProfile) {
        return tokenHelper.save(Globals.APP_USER_PROFILE, userProfile)
                .compose(RxTransformer.applyComputationSchedulers());
    }

    @Override
    public Observable<Response<UserProfile>> signIn(String username, String password) {
        return apiSource.signIn(username, password)
                .compose(RxTransformer.applyIOSchedulers());
    }

}
