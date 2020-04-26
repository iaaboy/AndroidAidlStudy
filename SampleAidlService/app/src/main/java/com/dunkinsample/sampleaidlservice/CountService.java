package com.dunkinsample.sampleaidlservice;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

public class CountService extends Service {

    private int mCurrentCount = 0;
    private Thread mCountThread;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d("CountService", "onStartCommand called");

        if(mCountThread == null)
        {
            mCountThread = new Thread("Cound Thread"){
                @Override
                public void run() {
                    super.run();
                    while(true){
                        Log.d("CountService", "Cound Thread run: " + mCurrentCount);
                        mCurrentCount++;
                        try{Thread.sleep(1000);}
                        catch (InterruptedException e){
                            break;
                        }
                    }
                }
            };
            mCountThread.start();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("CountService", "onDestroy called");
        super.onDestroy();

        Log.i("superdroid", "onDestroy()");

        stopForeground( true );

        if( mCountThread != null )
        {
            mCountThread.interrupt();
            mCountThread = null;
            mCurrentCount = 0;
        }
    }

    ICountService.Stub mBinder = new ICountService.Stub() {
        @Override
        public int getCurCountNumber() throws RemoteException {
            return mCurrentCount;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent)
    {
        return super.onUnbind(intent);
    }

}
