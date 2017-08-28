package hu.brazzo.andrd.main.viewmodel;

import android.content.Context;

import hu.brazzo.andrd.app.common.LogOutClickEvent;
import hu.brazzo.andrd.app.util.RxBus;
import hu.brazzo.andrd.main.domain.MainUsecase;
import hu.brazzo.andrd.main.model.UserProfile;
import rx.subscriptions.CompositeSubscription;

public class MainViewModel {

    private RxBus rxBus;
    private Context context;
    private CompositeSubscription subscription;
    private MainUsecase usecase;
    private MainListener listener;

    public MainViewModel(Context context, RxBus rxBus, MainUsecase usecase, MainListener listener) {
        this.context = context;
        this.rxBus = rxBus;
        this.usecase = usecase;
        this.listener = listener;
        subscription = new CompositeSubscription();

        subscription.add(rxBus.toObserverable()
                .filter(o -> o instanceof LogOutClickEvent)
                .map(o -> (LogOutClickEvent) o)
                .subscribe(listener::onLogOutClicked));
    }

    public void stop() {
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }

    public void getUserToken() {
        subscription.add(usecase.getUserToken()
                .subscribe(listener::onSuccessUserToken, listener::onError));
    }

    public void logOut() {
        subscription.add(usecase.logOut()
                .subscribe(listener::onSuccessLogOut, listener::onError));
    }

    public void getUserProfile() {
        subscription.add(usecase.getUserProfile()
                .map(s -> UserProfile.fromJson(s))
                .subscribe(listener::onProfileLoaded, listener::onError));
    }

    public interface MainListener {

        void navigateLogin();

        void navigateMain(UserProfile userProfile);

        void onProfileLoaded(UserProfile userProfile);

        void onSuccess(String response);

        void onError(Throwable throwable);

        void onSuccessUserToken(String activeToken);

        void onLogOutClicked(LogOutClickEvent event);

        void onSuccessLogOut(Boolean logedOut);

    }
}
