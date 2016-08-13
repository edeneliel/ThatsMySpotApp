package eliel.eden.thatsmyspot;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.Color;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Eden on 8/13/2016.
 */
public class NotificationService extends IntentService {
    private NotificationCompat.Builder _notificationBuilder;
    private NotificationManager _notificationManager;
    private boolean _first;

    public NotificationService() {
        super("ShowNotificationIntentService");
    }

    @Override
    public void onCreate(){
        super.onCreate();
        setNotification();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        _first = true;
        onHandleIntent(intent);
        return(START_STICKY);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ChildEventListener a = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (_first)
                    _first = false;
                else
                    updateNotification(dataSnapshot.getKey(),dataSnapshot.getValue().toString());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                if (_first)
                    _first = false;
                else
                    updateNotification(dataSnapshot.getKey(),dataSnapshot.getValue().toString());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        FirebaseDatabase.getInstance().getReference().addChildEventListener(a);
    }

    private void setNotification(){
        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,(int) System.currentTimeMillis(),resultIntent,0);

        _notificationBuilder = new NotificationCompat.Builder(this)
                .setContentTitle("movieTitle")
                .setContentText("tillTime")
                .setSmallIcon(R.drawable.notification_icon)
                .setContentIntent(pendingIntent)
                .setVibrate(new long[] {0,1000})
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setPriority(Notification.PRIORITY_HIGH)
                .setAutoCancel(true);

        _notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }
    private void updateNotification(String movieTitle, String tillTime){
        _notificationBuilder.setContentTitle(movieTitle);
        _notificationBuilder.setContentText(tillTime);
        _notificationManager.notify(0,_notificationBuilder.build());
    }
}
