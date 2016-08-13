package eliel.eden.thatsmyspot;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    private FirebaseManager _firebase;
    private TextView _movieTitle;
    private TextView _tillTime;
    private Button _stopBtn;
    private AlertDialog _dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _firebase = new FirebaseManager(this);

        Intent intent = new Intent(this, NotificationService.class);
        startService(intent);

        _movieTitle = (TextView) findViewById(R.id.movieText);
        _tillTime = (TextView) findViewById(R.id.tillTime);
        _stopBtn = (Button) findViewById(R.id.stopBtn);

        setStopButton();
    }

    private void setStopButton() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    if(which == DialogInterface.BUTTON_POSITIVE) {
                        _firebase.setStopFlag(true);
                    }
                }
            };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation")
                .setMessage("Are you sure you want to stop?")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener);
        _dialog = builder.create();

        _stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _dialog.show();
            }
        });
    }
    public void setMovieTitleText(String string){
        _movieTitle.setText(string);
    }
    public void setTillTimeText(String string){
        _tillTime.setText(string);
    }
}
