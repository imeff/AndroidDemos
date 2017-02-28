package cn.imef.androiddemos.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.Printer;

import cn.imef.androiddemos.service.MyService;

/**
 * Created by yqzheng@coremail.cn on 2017/2/27.
 */

public class BindServiceActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, MyService.class);

        bindService(intent, mConnection2 , BIND_AUTO_CREATE);
    }

    private ServiceConnection mConnection1 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBinder binder = (MyService.MyBinder) service;
            MyService myService = binder.getService();
            myService.printLog("log log log");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Looper.getMainLooper();
        }
    };


    class Handler2 extends Handler{

    }

    private ServiceConnection mConnection2 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Messenger messenger = new Messenger(service);
            Message msg = Message.obtain();
            msg.what = 1;
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };



}
