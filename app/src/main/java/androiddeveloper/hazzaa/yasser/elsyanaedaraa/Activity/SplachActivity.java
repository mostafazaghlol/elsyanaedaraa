package androiddeveloper.hazzaa.yasser.elsyanaedaraa.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.onesignal.OneSignal;

import java.util.Timer;
import java.util.TimerTask;

import androiddeveloper.hazzaa.yasser.elsyanaedaraa.R;
import androiddeveloper.hazzaa.yasser.elsyanaedaraa.utils.screenwithoutAction;

public class SplachActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);
        screenwithoutAction.FullScreen(this);
       // startActivity(new Intent(this,MainActivity.class));
        ConnectivityManager conMgr =  (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        if (netInfo == null){
            new AlertDialog.Builder(SplachActivity.this)
                    .setTitle(getResources().getString(R.string.app_name))
                    .setMessage(getResources().getString(R.string.internet_error))
                    .setPositiveButton("OK", null).show();
        }else{
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            }, 1500);

        }
    }
}
