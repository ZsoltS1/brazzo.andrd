package hu.brazzo.andrd.login.viewmodel;

import android.view.View;

import hu.brazzo.andrd.app.common.Globals;
import hu.brazzo.andrd.login.domain.LoginUsecase;
import hu.brazzo.andrd.main.model.UserProfile;
import okhttp3.Headers;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class SignInViewModel {

    private CompositeSubscription subscription;
    private LoginUsecase usecase;
    private SignInViewModel.SignInListener listener;

    public SignInViewModel(LoginUsecase usecase, SignInViewModel.SignInListener listener) {
        this.usecase = usecase;
        this.listener = listener;
        subscription = new CompositeSubscription();
    }

    public void stop() {
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }

    public void onSignInClicked(View view) {
        listener.onSignInClicked();
    }

    public void onResetPasswordClicked(View view) {
        listener.onResetPasswordClicked();
    }

    public void doSignIn(String username, String password) {
        subscription.add(usecase.signIn(username, password)
                .observeOn(Schedulers.io())
                .doOnNext(response -> {
                    if (response.isSuccessful()) {
                       saveUserProfile(response.body());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> {
                    Headers headers = response.headers();
                    return headers.get(Globals.HTTP_HEADER_AUTHORIZATION);
                })
                .subscribe(listener::onSignInSuccess, listener::onError));
    }

    public void saveUserToken(String activeToken) {
        subscription.add(usecase.saveUserToken(activeToken)
                .doOnError(throwable -> listener.onError(throwable))
                .subscribe(s -> listener.navigateToMain()));
    }

    public void saveUserProfile(UserProfile userProfile) {
        subscription.add(usecase.saveUserProfile(UserProfile.toJson(userProfile))
                .doOnError(throwable -> listener.onError(throwable))
                .subscribe(s -> listener.navigateToMain()));
    }


    public interface SignInListener {

        void onSignInClicked();

        void onSignInSuccess(String response);

        void onError(Throwable throwable);

        void navigateToMain();

        void onResetPasswordClicked();

        void onProfileSuccess(UserProfile userProfile);
    }
}
