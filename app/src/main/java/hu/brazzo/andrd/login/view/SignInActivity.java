package hu.brazzo.andrd.login.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

import hu.brazzo.andrd.R;
import hu.brazzo.andrd.app.App;
import hu.brazzo.andrd.app.view.BaseActivity;
import hu.brazzo.andrd.databinding.SignInBinding;
import hu.brazzo.andrd.login.di.DaggerSignInComponent;
import hu.brazzo.andrd.login.di.SignInModule;
import hu.brazzo.andrd.login.viewmodel.SignInViewModel;
import hu.brazzo.andrd.main.model.UserProfile;


public class SignInActivity extends BaseActivity implements SignInViewModel.SignInListener {

    public static final String TAG = "SignInActivity";

    private ProgressDialog progressDialog;

    SignInBinding binding;

    @Inject
    SignInViewModel viewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, SignInActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.sign_in);

        initializeInjectors();

        binding.setViewModel(viewModel);
    }

    private void initializeInjectors() {
        App app = (App) getApplication();
        DaggerSignInComponent.builder()
                .appComponent(app.getAppComponent())
                .signInModule(new SignInModule(this))
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
        finishActivity(requestCode);
    }

//    @Override
//    public void setUsernameError() {
//        emailText.setError("grow_from_middle a valid email address");
//    }
//
//    @Override
//    public void setPasswordError() {
//        passwordText.setError("between 4 and 10 alphanumeric characters");
//    }

    @Override
    public void onSignInClicked() {
        binding.btnSignIn.setEnabled(true);

        String email = binding.etUsername.getText().toString();
        String password = binding.etPassword.getText().toString();
        viewModel.doSignIn(email, password);
    }

    @Override
    public void onSignInSuccess(String tokeString) {
        if (tokeString != null) {
            viewModel.saveUserToken(tokeString);
        }
    }

    @Override
    public void onProfileSuccess(UserProfile userProfile) {
        if (userProfile != null) {
            viewModel.saveUserProfile(userProfile);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        Toast.makeText(SignInActivity.this, "Login failure", Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToMain() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onResetPasswordClicked() {

    }

}
