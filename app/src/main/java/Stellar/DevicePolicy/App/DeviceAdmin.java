package Stellar.DevicePolicy.App;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class DeviceAdmin extends DeviceAdminReceiver {

    public static final String ACTION_DISABLED = "device_admin_action_disabled";
    public static final String ACTION_ENABLED = "device_admin_action_enabled";


    @Override
    public void onProfileProvisioningComplete(Context context, Intent intent) {
        super.onProfileProvisioningComplete(context, intent);
        // I don't know why setting the policies in this receiver won't work very well
        // Anyway, we delegate it to the DummyActivity
        Toast.makeText(context, "Great", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisabled(Context context, Intent intent) {

        super.onDisabled(context, intent);
        LocalBroadcastManager.getInstance(context).sendBroadcast(
                new Intent(ACTION_DISABLED));
    }
    @Override
    public void onEnabled(Context context, Intent intent) {
        super.onEnabled(context, intent);



        LocalBroadcastManager.getInstance(context).sendBroadcast(
                new Intent(ACTION_ENABLED));
    }


    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (ACTION_DEVICE_ADMIN_DISABLE_REQUESTED.equals(action)) {
            CharSequence res = onDisableRequested(context, intent);
            if (res != null) {
                Log.d("Device Admin","Disabled");
            }
        }else if (ACTION_DEVICE_ADMIN_DISABLED.equals(action)) {
            Log.d("Device Admin","Disabled");
        }
    }

}
