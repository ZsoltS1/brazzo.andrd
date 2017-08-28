package hu.brazzo.andrd.app.api;

import hu.brazzo.andrd.main.model.UserProfile;
import retrofit2.Response;
import rx.Observable;

public interface ApiSource {

    Observable<Response<UserProfile>> signIn(String email, String password);

}
