package eliel.eden.thatsmyspot;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Eden on 8/13/2016.
 */
public class FirebaseInit extends  android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
