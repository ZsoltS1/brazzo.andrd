package hu.brazzo.andrd.app.preferences;

import rx.Observable;

public interface PreferencesHelper<T> {

    Observable<T> save(String key, T value);

    Observable<T> get(String key, Class<T> generic);

    Observable<Boolean> clear();
}
