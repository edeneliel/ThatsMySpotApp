package eliel.eden.thatsmyspot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private TextView _movieTitle;
    private TextView _tillTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new FirebaseManager(this);

        Intent intent = new Intent(this, NotificationService.class);
        startService(intent);

        _movieTitle = (TextView) findViewById(R.id.movieText);
        _tillTime = (TextView) findViewById(R.id.tillTime);
    }

    public void setMovieTitleText(String string){
        _movieTitle.setText(string);
    }
    public void setTillTimeText(String string){
        _tillTime.setText(string);
    }
}
