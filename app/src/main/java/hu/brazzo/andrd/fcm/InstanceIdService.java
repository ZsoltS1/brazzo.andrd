//package hu.brazzo.andrd.fcm;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.preference.PreferenceManager;
//import android.support.v4.content.LocalBroadcastManager;
//import android.util.Log;
//
//import com.google.firebase.iid.FirebaseInstanceId;
//import com.google.firebase.iid.FirebaseInstanceIdService;
//import com.google.firebase.messaging.FirebaseMessaging;
//
//import java.io.IOException;
//
//import javax.inject.Inject;
//
//import hu.brazzo.andrd.app.App;
//import hu.brazzo.andrd.app.api.ApiSource;
//import hu.brazzo.andrd.app.common.Globals;
//import hu.brazzo.andrd.app.preferences.PreferencesHelper;
//import hu.brazzo.andrd.app.util.RxTransformer;
//
//
//public class InstanceIdService extends FirebaseInstanceIdService {
//
//    private static final String TAG = "KriphInstanceIdService";
//
//    private static final String[] TOPICS = {"global"};
//
//    @Inject
//    ApiSource apiSource;
//
//    @Inject
//    PreferencesHelper<String> tokenHelper;
//
//    public InstanceIdService() {
//        super();
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        initializeInjectors();
//    }
//
//    private void initializeInjectors() {
//        App app = (App) getApplication();
//        DaggerNotificationComponent.builder()
//                .appComponent(app.getAppComponent())
//                .notificationModule(new NotificationModule(this))
//                .build().inject(this);
//    }
//
//    /**
//     * Called if InstanceID token is updated. This may occur if the security of
//     * the previous token had been compromised. Note that this is also called
//     * when the InstanceID token is initially generated, so this is where
//     * you retrieve the token.
//     */
//    // [START refresh_token]
//    @Override
//    public void onTokenRefresh() {
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        try {
//            // Get updated InstanceID token.
//            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//            Log.d(TAG, "Refreshed token: " + refreshedToken);
//            sendRegistrationToServer(refreshedToken);
//
//            // Subscribe to topic channels
//            subscribeTopics();
//            // You should store a boolean that indicates whether the generated token has been
//            // sent to your server. If the boolean is false, send the token to your server,
//            // otherwise your server should have already received the token.
//            sharedPreferences.edit().putBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, true).apply();
//            // [END register_for_gcm]
//        } catch (Exception e) {
//            Log.d(TAG, "Failed to complete token refresh", e);
//            // If an exception happens while fetching the new token or updating our registration data
//            // on a third-party server, this ensures that we'll attempt the update at a later time.
//            sharedPreferences.edit().putBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false).apply();
//        }
//        // Notify UI that registration has completed, so the progress indicator can be hidden.
//        Intent registrationComplete = new Intent(QuickstartPreferences.REGISTRATION_COMPLETE);
//        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
//
//    }
//
//    /**
//     * Persist registration to third-party servers.
//     * <p>
//     * Modify this method to associate the user's GCM registration token with any server-side account
//     * maintained by your application.
//     *
//     * @param token The new token.
//     */
//    private void sendRegistrationToServer(String token) {
//        // Add custom implementation, as needed.
//        tokenHelper.get(Globals.APP_USER_TOKEN, String.class)
//                .flatMap(s -> {
//                    if (s != null) {
//                        return apiSource.subcribeNotification(token, s);
//                    }
//                    return null;
//                })
//                .compose(RxTransformer.applyIOSchedulers())
//                .subscribe();
//    }
//
//    /**
//     * Subscribe to any GCM topics of interest, as defined by the TOPICS constant.
//     *
//     * @throws IOException if unable to reach the GCM PubSub service
//     */
//    // [START subscribe_topics]
//    private void subscribeTopics() throws IOException {
//        for (String topic : TOPICS) {
//            Log.d(TAG, "Subscribing to " + topic + " topic");
//            FirebaseMessaging.getInstance().subscribeToTopic(topic);
////                    .addOnCompleteListener(new OnCompleteListener<Void>() {
////                @Override
////                public void onComplete(@NonNull Task<Void> task) {
////                    if (!task.isSuccessful()) {
////                        Log.d(TAG, "Subscribing is successfull");
////                    }
////                    Log.d(TAG, "Subscribing is failure");
////                }
////            });
//        }
//    }
//    // [END subscribe_topics]
//}