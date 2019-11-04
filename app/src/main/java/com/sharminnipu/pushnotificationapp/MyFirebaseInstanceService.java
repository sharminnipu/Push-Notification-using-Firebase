package com.sharminnipu.pushnotificationapp;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.util.Map;
import java.util.Random;

public class MyFirebaseInstanceService extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);


      if (remoteMessage.getData().isEmpty())
          showNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
      else
          showNotification(remoteMessage.getData());

    }

    private void showNotification(Map<String, String> data) {

        String title=data.get("title").toString();
        String body=data.get("body").toString();


        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID="com.sharminnipu.pushnotificationapp.test";

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){

            NotificationChannel notificationChannel=new NotificationChannel(NOTIFICATION_CHANNEL_ID,"Notification",
                    NotificationManager.IMPORTANCE_DEFAULT);

            notificationChannel.setDescription("Sharmin Channel");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.setVibrationPattern(new long[]{0,1000,500,1000});
            notificationChannel.enableLights(true);
            nm.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder  notification=new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID);


        notification.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_stat_notication)
                .setContentTitle(title)
                .setContentText(body)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentInfo("info");


        nm.notify(new Random().nextInt(),notification.build());

    }

    private void showNotification(String title, String body) {

        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID="com.sharminnipu.pushnotificationapp.test";

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){

            NotificationChannel notificationChannel=new NotificationChannel(NOTIFICATION_CHANNEL_ID,"Notification",
                    NotificationManager.IMPORTANCE_DEFAULT);

            notificationChannel.setDescription("Sharmin Channel");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.setVibrationPattern(new long[]{0,1000,500,1000});
            notificationChannel.enableLights(true);
            nm.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder  notification=new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID);


        notification.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_stat_notication)
                .setContentTitle(title)
                .setContentText(body)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentInfo("info");


        nm.notify(new Random().nextInt(),notification.build());


    }
    

    
    
    
    


    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.d("TOKEN",s);
    }
}
