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
import hu.brazzo.andrd.app.common.GlobalRequestCodes;
import hu.brazzo.andrd.app.view.BaseActivity;
import hu.brazzo.andrd.databinding.LoginActivityBinding;
import hu.brazzo.andrd.login.di.DaggerLoginComponent;
import hu.brazzo.andrd.login.di.LoginModule;
import hu.brazzo.andrd.login.viewmodel.LoginViewModel;


public class LoginActivity extends BaseActivity implements LoginViewModel.LoginListener {

    public static final String TAG = "LoginActivity";

    private ProgressDialog progressDialog;

    LoginActivityBinding binding;

    @Inject
    LoginViewModel viewModel;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.login_activity);

        initializeInjectors();

        binding.setViewModel(viewModel);

    }

    private void initializeInjectors() {
        App app = (App) getApplication();
        DaggerLoginComponent.builder()
                .appComponent(app.getAppComponent())
                .loginModule(new LoginModule(this))
                .build().inject(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GlobalRequestCodes.RESULT_LOGIN && resultCode == RESULT_OK) {
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.stop();
        binding.unbind();
    }

    @Override
    public void onError(Throwable throwable) {
        Toast.makeText(getBaseContext(), "Facebook signIn failed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(String response) {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onLoginClicked() {
        Intent intent = SignInActivity.newIntent(getApplicationContext());
        startActivityForResult(intent, GlobalRequestCodes.RESULT_LOGIN);
    }

    @Override
    public void showProgress(boolean show) {
        if (show) {
            progressDialog = new ProgressDialog(LoginActivity.this, R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Authenticating...");
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }

}
