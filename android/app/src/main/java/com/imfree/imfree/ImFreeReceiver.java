package com.imfree.imfree;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ImFreeReceiver extends BroadcastReceiver {
    public ImFreeReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            Intent i = new Intent(context, ImFreeService.class);
            context.startService(i);
        }
    }
}
