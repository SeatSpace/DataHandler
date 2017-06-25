package com.example.skatt.seatspace;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tableSetter(1, 1, 20, 20, 20, 20);
       /* tableSetter(2, 1, 20, 20, 20, 30);
        tableSetter(3, 1, 20, 20, 20, 30);

        tableSetter(1, 2, 20, 10, 20, 30);
        tableSetter(2, 2, 20, 20, 20, 20);
        tableSetter(3, 2, 20, 10, 20, 30);

        tableSetter(1, 3, 20, 10, 20, 30);
        tableSetter(2, 3, 20, 20, 20, 20);
        tableSetter(3, 3, 20, 10, 20, 30);*/

        sensorSetter(1, 1, 1, true, new Date());
        sensorSetter(2, 1, 1, true, new Date());
        sensorSetter(3, 1, 1, true, new Date());
        sensorSetter(4, 1, 1, true, new Date());

        /*sensorSetter(1, 2, 1, true, new Date());
        sensorSetter(2, 2, 1, true, new Date());
        sensorSetter(3, 2, 1, true, new Date());
        sensorSetter(4, 2, 1, true, new Date());

        sensorSetter(1, 3, 1, true, new Date());
        sensorSetter(2, 3, 1, true, new Date());
        sensorSetter(3, 3, 1, true, new Date());
        sensorSetter(4, 3, 1, true, new Date());

        sensorSetter(1, 1, 2, true, new Date());
        sensorSetter(2, 1, 2, true, new Date());
        sensorSetter(3, 1, 2, true, new Date());
        sensorSetter(4, 1, 2, true, new Date());

        sensorSetter(1, 2, 2, true, new Date());
        sensorSetter(2, 2, 2, true, new Date());
        sensorSetter(3, 2, 2, true, new Date());
        sensorSetter(4, 2, 2, true, new Date());

        sensorSetter(1, 3, 2, true, new Date());
        sensorSetter(2, 3, 2, true, new Date());
        sensorSetter(3, 3, 2, true, new Date());
        sensorSetter(4, 3, 2, true, new Date());

        sensorSetter(1, 1, 3, true, new Date());
        sensorSetter(2, 1, 3, true, new Date());
        sensorSetter(3, 1, 3, true, new Date());
        sensorSetter(4, 1, 3, true, new Date());

        sensorSetter(1, 2, 3, true, new Date());
        sensorSetter(2, 2, 3, true, new Date());
        sensorSetter(3, 2, 3, true, new Date());
        sensorSetter(4, 2, 3, true, new Date());

        sensorSetter(1, 3, 3, true, new Date());
        sensorSetter(2, 3, 3, true, new Date());
        sensorSetter(3, 3, 3, true, new Date());
        sensorSetter(4, 3, 3, true, new Date());*/

        DatabaseReference ref1 = database.getReference("/floor/1/arduino/1/sensor/1/taken");
        ref1.addValueEventListener(new ValueEventListener() {

            String TAG = "";
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(Boolean.class).toString();
                Log.d(TAG, "############################################## Updated 1:1:1: " + value);
                Log.d(TAG, "##############################################" + database.getReference("/floor/1/arduino/1/sensor/1/taken").toString());
            }

            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }

    protected void update(View view){
        sensorSetter(1, 1, 1, false, new Date());
    }

    protected void tableSetter(int id, int fID, int x, int y, int height, int width){

        DatabaseReference refID = database.getReference("/floor/" + fID + "/arduino/" + id);
        DatabaseReference refX = database.getReference("/floor/" + fID + "/arduino/" + id + "/x");
        DatabaseReference refY = database.getReference("/floor/" + fID + "/arduino/" + id + "/y");
        DatabaseReference refWidth = database.getReference("/floor/" + fID + "/arduino/" + id + "/height");
        DatabaseReference refHeight = database.getReference("/floor/" + fID + "/arduino/" + id + "/width");

        refID.setValue(id);
        refX.setValue(x);
        refY.setValue(y);
        refWidth.setValue(width);
        refHeight.setValue(height);
    }

    protected void sensorSetter(int id, int fID, int aID, boolean taken, Date date){

        DatabaseReference refID = database.getReference("/floor/" + fID + "/arduino/" + aID + "/sensor/" + id + "/id");
        DatabaseReference refTaken = database.getReference("/floor/" + fID + "/arduino/" + aID  + "/sensor/" + id + "/taken");
        DatabaseReference refDate = database.getReference("/floor/" + fID + "/arduino/" + aID  + "/sensor/" + id + "/date");

        refID.setValue(id);
        refTaken.setValue(taken);
        refDate.setValue(date.toString());
    }
}
