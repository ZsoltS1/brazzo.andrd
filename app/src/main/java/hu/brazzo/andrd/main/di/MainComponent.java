package hu.brazzo.andrd.main.di;

import dagger.Component;
import hu.brazzo.andrd.app.Activity;
import hu.brazzo.andrd.app.di.AppComponent;
import hu.brazzo.andrd.main.view.MainActivity;

@Activity
@Component(dependencies = AppComponent.class, modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}