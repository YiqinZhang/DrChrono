package com.example.a327_prototype_1;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity implements SensorEventListener, StepListener {
    private Button signOut;
    private TextView TvSteps;
    private Button BtnStart;
    private Button BtnStop;
    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
    private int numSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // Get an instance of the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);
        TvSteps = findViewById(R.id.textView_steps);
        BtnStart = findViewById(R.id.button_start);
        BtnStop = findViewById(R.id.button_stop);

        BtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                numSteps = 0;
                sensorManager.registerListener(HomeActivity.this, accel, SensorManager.SENSOR_DELAY_FASTEST);
            }
        });
        BtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                sensorManager.unregisterListener(HomeActivity.this);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.bear:
                        startActivity(new Intent(getApplicationContext(),BearActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.setting:
                        startActivity(new Intent(getApplicationContext(),SettingActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.recycle:
                        startActivity(new Intent(getApplicationContext(),RecycleActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
        signOut = findViewById(R.id.signOut);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intentRegister = new Intent(HomeActivity.this, RegisterActivity.class);
                startActivity(intentRegister);
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
    @Override
    public void step(long timeNs) {
        numSteps++;
        TvSteps.setText(TEXT_NUM_STEPS + numSteps);
    }
}
