package hu.brazzo.andrd.login.domain;

import hu.brazzo.andrd.main.model.UserProfile;
import retrofit2.Response;
import rx.Observable;

public interface LoginUsecase {

    Observable<String> saveUserToken(String token);

    Observable<Response<UserProfile>> signIn(String username, String password);

    Observable<String> saveUserProfile(String userProfile);
}
