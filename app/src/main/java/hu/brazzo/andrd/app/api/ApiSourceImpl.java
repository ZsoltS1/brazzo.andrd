package hu.brazzo.andrd.app.api;

import hu.brazzo.andrd.login.model.SignInRequest;
import hu.brazzo.andrd.main.model.UserProfile;
import retrofit2.Retrofit;
import rx.Observable;

public class ApiSourceImpl implements ApiSource {

    RetrofitInterface retrofitInterface;

    public ApiSourceImpl(Retrofit retrofit) {
        retrofitInterface = retrofit.create(RetrofitInterface.class);
    }

    @Override
    public Observable<retrofit2.Response<UserProfile>> signIn(String email, String password) {
        return retrofitInterface.signIn(new SignInRequest(email, password));
    }

}
