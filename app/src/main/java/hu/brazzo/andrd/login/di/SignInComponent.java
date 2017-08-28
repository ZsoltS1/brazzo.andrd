package hu.brazzo.andrd.login.di;

import dagger.Component;
import hu.brazzo.andrd.app.Activity;
import hu.brazzo.andrd.app.di.AppComponent;
import hu.brazzo.andrd.login.view.SignInActivity;

@Activity
@Component(dependencies = AppComponent.class, modules = SignInModule.class)
public interface SignInComponent {
    void inject(SignInActivity signInActivity);
}
