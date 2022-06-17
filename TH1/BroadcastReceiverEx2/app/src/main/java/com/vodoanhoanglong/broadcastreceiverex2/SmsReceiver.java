package com.vodoanhoanglong.broadcastreceiverex2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class SmsReceiver extends BroadcastReceiver {
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.getDefault());

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            Object[] arrMessage = (Object[]) bundle.get("pdus");
            if(arrMessage != null){
                String content, phone, time;
                long currentTImeStamp;

                byte[] data;
                for(Object msgObj: arrMessage){
                    data = (byte[]) msgObj;
                    SmsMessage message = SmsMessage.createFromPdu(data);
                    content = message.getDisplayMessageBody();
                    phone = message.getDisplayOriginatingAddress();
                    currentTImeStamp = message.getTimestampMillis();
                    time = dateFormat.format(currentTImeStamp);
                    Toast.makeText(context, "Phone: " + phone + "\nTime: " + time + "\nContent: " + content, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
