package hu.brazzo.andrd.login.viewmodel;

import android.view.View;

import hu.brazzo.andrd.login.domain.LoginUsecase;
import rx.subscriptions.CompositeSubscription;

public class LoginViewModel {

    private CompositeSubscription subscription;
    private LoginUsecase usecase;
    private LoginViewModel.LoginListener listener;

    public LoginViewModel(LoginUsecase usecase, LoginViewModel.LoginListener listener) {
        this.usecase = usecase;
        this.listener = listener;
        subscription = new CompositeSubscription();
    }

    public void stop() {
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }

    public void onLoginClicked(View view) {
        listener.onLoginClicked();
    }

    public void saveUserToken(String token) {
        subscription.add(usecase.saveUserToken(token)
                .subscribe(listener::onSuccess, listener::onError));
    }

    public interface LoginListener {

        void onError(Throwable throwable);

        void onSuccess(String response);

        void showProgress(boolean show);

        void onLoginClicked();

    }

}
