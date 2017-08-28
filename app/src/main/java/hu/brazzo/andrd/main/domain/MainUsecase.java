package hu.brazzo.andrd.main.domain;

import rx.Observable;

public interface MainUsecase {

    Observable<String> getUserProfile();

    Observable<String> saveUserProfile(String userProfile);

    Observable<String> getUserToken();

    Observable<String> saveUserToken(String token);

    Observable<Boolean> logOut();

}
