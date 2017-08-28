package hu.brazzo.andrd.login.di;

import dagger.Component;
import hu.brazzo.andrd.app.Activity;
import hu.brazzo.andrd.app.di.AppComponent;
import hu.brazzo.andrd.login.view.LoginActivity;

@Activity
@Component(dependencies = AppComponent.class, modules = LoginModule.class)
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}
