package hu.brazzo.andrd.app.api;

import hu.brazzo.andrd.login.model.SignInRequest;
import hu.brazzo.andrd.main.model.UserProfile;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface RetrofitInterface {

    @POST("auth/login")
    Observable<Response<UserProfile>> signIn(@Body SignInRequest request);

}
