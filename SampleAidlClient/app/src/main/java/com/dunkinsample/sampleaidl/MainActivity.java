package com.dunkinsample.sampleaidl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.dunkinsample.sampleaidlservice.ICountService;

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity";

    private ICountService mBinder = null;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected");
            mBinder = ICountService.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //카운트 서비스 연결
        //============
        Intent serviceIntent = new Intent().setAction("com.dunkinsample.sampleaidlservice.CountService");
        serviceIntent.setPackage("com.dunkinsample.sampleaidlservice");

        bindService(serviceIntent, mConnection, BIND_AUTO_CREATE);
        //============
    }

    @Override
    protected void onDestroy(){

        //카운트 서비스 해제
        //============
        unbindService(mConnection);
        //============
        super.onDestroy();
    }

    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.start_count_number:
            {
                Intent serviceIntent = new Intent().setAction("com.dunkinsample.sampleaidlservice.CountService");
                serviceIntent.setPackage("com.dunkinsample.sampleaidlservice");

                startService( serviceIntent );
                break;
            }
            case R.id.stop_count_number:
            {
                Intent serviceIntent = new Intent().setAction("com.dunkinsample.sampleaidlservice.CountService");
                serviceIntent.setPackage("com.dunkinsample.sampleaidlservice");

                stopService( serviceIntent );
                break;
            }
            case R.id.get_count_number:
            {
                int curCountNumber;
                Log.d(TAG, "get_count_number clicked: ");
                try
                {
                    curCountNumber = mBinder.getCurCountNumber();
                    Log.d(TAG, "onServiceDisconnected: " + curCountNumber);
                }
                catch (RemoteException e){
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
