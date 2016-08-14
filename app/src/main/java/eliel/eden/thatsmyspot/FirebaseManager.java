package eliel.eden.thatsmyspot;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Eden on 8/12/2016.
 */
public class FirebaseManager {
    private MainActivity _mainActivity;
    private static FirebaseDatabase _firebaseInstance;

    public FirebaseManager(MainActivity mainActivity){
        _mainActivity = mainActivity;

        _firebaseInstance = FirebaseDatabase.getInstance();

        setChildEventForMain();
    }

    public void setChildEventForMain(){
        ChildEventListener childListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                _mainActivity.setMovieTitleText(dataSnapshot.getKey());
                _mainActivity.setTillTimeText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                _mainActivity.setMovieTitleText(dataSnapshot.getKey());
                _mainActivity.setTillTimeText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        _firebaseInstance.getReference("Movies").addChildEventListener(childListener);
    }
}
