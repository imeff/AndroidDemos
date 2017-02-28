package cn.imef.androiddemos.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by yqzheng@coremail.cn on 2017/2/27.
 */

public class MyService extends Service {

    private static final String TAG = "MyService";

    private Messenger messenger;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        messenger = new Messenger(handler);
        return messenger.getBinder();
    }

    private MyHandler handler = new MyHandler();

    public class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d(TAG, "MyService MyHandler " + msg.what);
            Message msg1 = Message.obtain();
            msg1.what = 2;
            try {
                messenger.send(msg1);
            } catch (RemoteException e) {
            }
        }
    }


    public class MyBinder extends Binder {

        public MyService getService() {
            return MyService.this;
        }
    }

    public void printLog(String log) {
        Log.d(TAG, log);
    }
}
