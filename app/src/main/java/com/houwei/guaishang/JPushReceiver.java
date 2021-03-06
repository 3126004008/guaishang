package com.houwei.guaishang;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.houwei.guaishang.activity.MainActivity;
import com.houwei.guaishang.activity.TopicHomeFragment;
import com.houwei.guaishang.tools.ApplicationProvider;
import com.houwei.guaishang.tools.VoiceUtils;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by chrc on 2018/4/7.
 */

public class JPushReceiver extends BroadcastReceiver {

    private static final String TAG = "guaishang.jpush.receive";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.d(TAG, "onReceive - " + intent.getAction());

        if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            String content = "";
            content = bundle.getString(JPushInterface.EXTRA_MESSAGE);
            VoiceUtils.getInstance(ApplicationProvider.privode()).speak(content);
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            String content = "";
            content = bundle.getString(JPushInterface.EXTRA_ALERT);
            VoiceUtils.getInstance(ApplicationProvider.privode()).speak(content);
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "用户点击打开了通知");
            // 在这里可以自己写代码去定义用户点击后的行为
            Intent i = new Intent(context, MainActivity.class);  //自定义打开的界面
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }

//        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
//            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
//            Log.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
//        }else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
//            Log.d(TAG, "收到了自定义消息。消息内容是：" + bundle.getString(JPushInterface.EXTRA_MESSAGE));
//            // 自定义消息不会展示在通知栏，完全要开发者写代码去处理
//        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
//            Log.d(TAG, "收到了通知");
//            // 在这里可以做些统计，或者做些其他工作
//        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
//            Log.d(TAG, "用户点击打开了通知");
//            // 在这里可以自己写代码去定义用户点击后的行为
//            Intent i = new Intent(context, MainActivity.class);  //自定义打开的界面
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(i);
//        } else {
//            Log.d(TAG, "Unhandled intent - " + intent.getAction());
//        }
    }
}
