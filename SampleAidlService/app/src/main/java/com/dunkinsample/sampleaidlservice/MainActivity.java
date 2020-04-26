package com.dunkinsample.sampleaidlservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick( View v )
    {
        switch( v.getId() )
        {
            // 1. 카운트 시작
            // ================================================================
            case R.id.start_count_btn:
            {

                Intent serviceIntent = new Intent().setAction("com.dunkinsample.sampleaidlservice.CountService");
                serviceIntent.setPackage("com.dunkinsample.sampleaidlservice");

                startService( serviceIntent );

                break;
            }
            // ================================================================

            // 2. 카운트 종료
            // ================================================================
            case R.id.stop_count_btn:
            {
                Intent serviceIntent = new Intent().setAction("com.dunkinsample.sampleaidlservice.CountService");
                serviceIntent.setPackage("com.dunkinsample.sampleaidlservice");

                stopService( serviceIntent );
                break;
            }
            // ================================================================

            // 3. 현재까지 카운트 된 수치 보기
            // ================================================================
            case R.id.get_cur_count_number_btn:
            {
                break;
            }
            // ================================================================
        }
    }
}
