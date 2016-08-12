package eliel.eden.thatsmyspot;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by Eden on 8/13/2016.
 */
public class NotificationService extends IntentService {
    public NotificationService() {
        super("ShowNotificationIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        
    }
}
