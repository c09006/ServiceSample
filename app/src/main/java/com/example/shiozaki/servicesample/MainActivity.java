package com.example.shiozaki.servicesample;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ServiceConfigurationError;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ボタンの登録
        Button btn_service_start = (Button)findViewById(R.id.button);
        btn_service_start.setOnClickListener((View.OnClickListener) btnListener);
        Button btn_service_stop = (Button)findViewById(R.id.button2);
        btn_service_stop.setOnClickListener((View.OnClickListener) btnListener);
        Button btn_bind_start = (Button)findViewById(R.id.button3);
        btn_bind_start.setOnClickListener((View.OnClickListener) btnListener);
        Button btn_bind_stop = (Button)findViewById(R.id.button4);
        btn_bind_stop.setOnClickListener((View.OnClickListener) btnListener);
    }

    //ボタンの動作を設定
    private View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button:
                    //Intentによるサービスの起動
                    startService(new Intent(MainActivity.this, SampleService.class));
                    Log.d("test", "button2");
                    break;
                case R.id.button2:
                    //Intentによるサービスの停止
                    Log.d("test", "button2");
                    stopService(new Intent(MainActivity.this, SampleService.class));
                    break;
                case R.id.button3:
                    //bindによるサービスの開始
                    Intent i = new Intent(MainActivity.this, SampleService.class);
                    bindService(i, mConnection, Context.BIND_AUTO_CREATE);
                    Log.d("test", "button3");
                    break;
                case R.id.button4:
                    //bindによるサービスの停止
                    unbindService(mConnection);
                    Log.d("test", "button4");
                    break;

                default:
                    break;
            }
        }
    };

    //Serviceとのインターフェースクラス
    private ServiceConnection mConnection = new ServiceConnection() {
        SampleService sService;
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //Serviceとの接続確立時に呼び出される
            //Service引数には、Onbind()で返却したBinderがわたされる
            sService =((SampleService.ServiceLocalBinder)service).getService();
            //ここにServiceの制御を記入
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //Serviceとの切断時に呼び出される
            sService = null;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
