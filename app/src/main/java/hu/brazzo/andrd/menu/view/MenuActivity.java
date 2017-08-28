package hu.brazzo.andrd.menu.view;


import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import org.parceler.Parcels;

import hu.brazzo.andrd.R;
import hu.brazzo.andrd.app.common.Globals;
import hu.brazzo.andrd.app.view.BaseActivity;
import hu.brazzo.andrd.databinding.MenuActivityBinding;
import hu.brazzo.andrd.main.model.UserProfile;

public class MenuActivity extends BaseActivity {

    public static final String TAG = "MenuActivity";

    MenuActivityBinding binding;

    UserProfile userProfile;

    public static Intent newIntent(Context context, UserProfile userProfile) {
        Intent intent = new Intent(context, MenuActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Globals.APP_USER_PROFILE, Parcels.wrap(userProfile));
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.menu_activity);

        userProfile = Parcels.unwrap(getIntent().getExtras().getParcelable(Globals.APP_USER_PROFILE));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.unbind();
    }

}
