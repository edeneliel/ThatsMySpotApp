package eliel.eden.thatsmyspot;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private TextView _movieTitle;
    private TextView _tillTime;
    private Notification _notificationBuild;
    private NotificationManager _notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        new FirebaseManager(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _movieTitle = (TextView) findViewById(R.id.movieText);
        _tillTime = (TextView) findViewById(R.id.tillTime);

        setNotificationBuild();
    }

    public void setMovieTitleText(String string){
        _movieTitle.setText(string);
    }
    public void setTillTimeText(String string){
        _tillTime.setText(string);
    }
    public void makeNotification(){
        _notificationManager.notify(0, _notificationBuild);
    }

    private void setNotificationBuild(){
        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,(int) System.currentTimeMillis(),resultIntent,0);

        _notificationBuild = new Notification.Builder(this)
                .setContentTitle("Test")
                .setContentText("Another Test")
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true).build();

        _notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }
}
