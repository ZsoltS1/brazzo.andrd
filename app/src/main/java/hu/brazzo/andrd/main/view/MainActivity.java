package hu.brazzo.andrd.main.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import hu.brazzo.andrd.R;
import hu.brazzo.andrd.app.App;
import hu.brazzo.andrd.app.common.GlobalRequestCodes;
import hu.brazzo.andrd.app.common.LogOutClickEvent;
import hu.brazzo.andrd.app.util.DialogBuilder;
import hu.brazzo.andrd.app.view.BaseActivity;
import hu.brazzo.andrd.databinding.MainActivityBinding;
import hu.brazzo.andrd.login.view.LoginActivity;
import hu.brazzo.andrd.main.di.DaggerMainComponent;
import hu.brazzo.andrd.main.di.MainModule;
import hu.brazzo.andrd.main.model.UserProfile;
import hu.brazzo.andrd.main.viewmodel.MainViewModel;
import hu.brazzo.andrd.menu.view.MenuActivity;

public class MainActivity extends BaseActivity implements MainViewModel.MainListener {

    public static final String TAG = "MainActivity";

    private static final String USER_PROFILE_RESULT = "USER_PROFILE_RESULT";

    MainActivityBinding binding;

    @Inject
    MainViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);

        initializeInjectors();

        binding.setViewModel(viewModel);

        viewModel.getUserToken();
    }

    private void initializeInjectors() {
        App app = (App) getApplication();
        DaggerMainComponent.builder()
                .appComponent(app.getAppComponent())
                .mainModule(new MainModule(this.getApplicationContext(), this))
                .build().inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.stop();
        binding.unbind();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GlobalRequestCodes.RESULT_LOGIN && resultCode == RESULT_OK) {
            viewModel.getUserProfile();
        } else if (requestCode == GlobalRequestCodes.RESULT_LOGOUT) {
            navigateLogin();
        }
    }

    @Override
    public void navigateLogin() {
        // Start the Login activity
        Intent intent = LoginActivity.newIntent(getApplicationContext());
        startActivityForResult(intent, GlobalRequestCodes.RESULT_LOGIN);
    }

    @Override
    public void navigateMain(UserProfile userProfile) {
        if (userProfile == null) {
            userProfile = new UserProfile();
        }
        Intent intent = MenuActivity.newIntent(this.getApplicationContext(), userProfile);
        startActivity(intent);
    }

    @Override
    public void onProfileLoaded(UserProfile userProfile) {
        Log.i(USER_PROFILE_RESULT, "get profile: " + userProfile);
        navigateMain(userProfile);
    }

    @Override
    public void onSuccess(String response) {

    }

    @Override
    public void onError(Throwable throwable) {
        DialogBuilder
                .errorDialog(getApplicationContext(), R.string.errorMessage, throwable.getMessage())
                .show();
    }

    @Override
    public void onSuccessUserToken(String activeToken) {
        if (activeToken != null && !activeToken.isEmpty()) {
            viewModel.getUserProfile();
        } else {
            navigateLogin();
        }
    }

    @Override
    public void onLogOutClicked(LogOutClickEvent event) {
        viewModel.logOut();
    }

    @Override
    public void onSuccessLogOut(Boolean logedOut) {
        if (logedOut) {
            navigateLogin();
        }
    }
}
