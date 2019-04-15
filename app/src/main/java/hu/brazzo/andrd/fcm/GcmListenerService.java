//package hu.brazzo.andrd.fcm;
//
//import android.app.NotificationChannel;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.content.Context;
//import android.content.Intent;
//import android.media.RingtoneManager;
//import android.net.Uri;
//import android.os.Build;
//import androidx.core.app.NotificationCompat;
//import android.util.Log;
//
//import com.google.firebase.messaging.FirebaseMessagingService;
//import com.google.firebase.messaging.RemoteMessage;
//
//import java.util.Date;
//import java.util.Map;
//
//import javax.inject.Inject;
//
//import hu.brazzo.andrd.app.App;
//import hu.brazzo.andrd.main.view.MainActivity;
//
//public class GcmListenerService extends FirebaseMessagingService {
//
//    private static final String TAG = "GcmListenerService";
//
////    @Inject
////    NotifDatabaseHelper notifDatabaseHelper;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//
//        initializeInjectors();
//    }
//
//    private void initializeInjectors() {
//        App app = (App) getApplication();
//        app.getAppComponent().inject(this);
//    }
//
//    @Override
//    public void onMessageReceived(RemoteMessage message) {
//        Log.d(TAG, "From: " + message.getFrom());
//
//        // Check if message contains a data payload.
//        if (message.getData().size() > 0) {
//            Log.d(TAG, "Message data payload: " + message.getData());
//
//            saveNotification(message.getData());
//        }
//
//        // Check if message contains a notification payload.
//        if (message.getNotification() != null) {
//            Log.d(TAG, "Message Notification Body: " + message.getNotification().getBody());
//
//            sendNotification(message.getNotification().getBody());
//        }
//    }
//
//    private void saveNotification(Map data) {
//        String message = (String) data.get("body");
//        String title = (String) data.get("title");
//
////        NotificationMessage notification = new NotificationMessage();
////        notification.setTitle(title);
////        notification.setMessage(message);
////        notification.setDate(new Date());
////        notification.setUnread(true);
//
//        // Add sample post to the database
////        notifDatabaseHelper.addNotification(notification);
//    }
//
//    /**
//     * Create and show a simple notification containing the received GCM message.
//     *
//     * @param messageBody GCM message received.
//     */
//    private void sendNotification(String messageBody) {
//        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//        intent.putExtra("menuFragment", "notificationsFragment");
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
//                PendingIntent.FLAG_ONE_SHOT);
//
//        String channelId = getString(R.string.default_notification_channel_id);
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId)
//                .setSmallIcon(R.mipmap.logo_without_name)
//                .setContentTitle("Message")
//                .setContentText(messageBody)
//                .setAutoCancel(true)
//                .setSound(defaultSoundUri)
//                .setContentIntent(pendingIntent);
//
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        // Since android Oreo notification channel is needed.
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(channelId,
//                    "Channel human readable title",
//                    NotificationManager.IMPORTANCE_DEFAULT);
//            notificationManager.createNotificationChannel(channel);
//        }
//
//        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
//    }
//
//}