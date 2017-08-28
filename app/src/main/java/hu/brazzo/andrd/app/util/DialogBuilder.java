package hu.brazzo.andrd.app.util;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

import hu.brazzo.andrd.R;

public class DialogBuilder {

    public static MaterialDialog.Builder progressDialog(Context context, int title, int content) {
        return new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .canceledOnTouchOutside(false)
                .progress(true, 0);
    }

    public static MaterialDialog.Builder infoDialog(Context context, int title, int content) {
        return new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .positiveText(R.string.dialog_action_ok);

    }

    public static MaterialDialog.Builder errorDialog(Context context, int title, String content) {
        return new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .positiveText(R.string.dialog_action_ok);
    }

}
