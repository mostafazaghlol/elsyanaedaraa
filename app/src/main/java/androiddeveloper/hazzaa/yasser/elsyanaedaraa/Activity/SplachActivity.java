package androiddeveloper.hazzaa.yasser.elsyanaedaraa.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.onesignal.OneSignal;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androiddeveloper.hazzaa.yasser.elsyanaedaraa.R;
import androiddeveloper.hazzaa.yasser.elsyanaedaraa.Volley.ShowAllIOSRequest;
import androiddeveloper.hazzaa.yasser.elsyanaedaraa.Volley.ShowIOSRequest;
import androiddeveloper.hazzaa.yasser.elsyanaedaraa.model.ShowIOS;
import androiddeveloper.hazzaa.yasser.elsyanaedaraa.utils.screenwithoutAction;

public class SplachActivity extends AppCompatActivity {

    public static boolean boo = false;
    private List<ShowIOS> showIOS;
    public HashMap<String, Integer> views;
    public static Integer One=0, Two=0, Three=0, Four=0, Five=0, Sex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);
        screenwithoutAction.FullScreen(this);
        // startActivity(new Intent(this,MainActivity.class));
        showIOS = new ArrayList<>();
        views = new HashMap<>();
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        if (netInfo == null) {
            new AlertDialog.Builder(SplachActivity.this)
                    .setTitle(getResources().getString(R.string.app_name))
                    .setMessage(getResources().getString(R.string.internet_error))
                    .setPositiveButton("OK", null).show();
        } else {
            sendRequest();

        }
    }

    private void sendRequest() {
        Response.Listener<String> responseListener = response -> {
            try {
                JSONArray object = new JSONArray(response);
                for (int i = 0; i < object.length(); i++) {
                    ShowIOS mShowIOS = new Gson().fromJson(object.getJSONObject(i).toString(), ShowIOS.class);
                    showIOS.add(mShowIOS);
                }
                for (int i = 0; i < showIOS.size(); i++) {
                    String id = showIOS.get(i).getType();
                    if (showIOS.get(i).getView().equals("0")) {
                        if (id.equals("1")) {
                            One++;
                        } else if (id.equals("2")) {
                            Two++;
                        } else if (id.equals("3")) {
                            Three++;
                        } else if (id.equals("4")) {
                            Four++;
                        } else if (id.equals("5")) {
                            Five++;
                        } else if (id.equals("6")) {
                            Sex++;
                        }
                    }
                }
                startActivity(new Intent(this,MainActivity.class));
            } catch (JSONException e) {
                e.printStackTrace();
                //progressBar.setVisibility(View.INVISIBLE);
            }
        };

        ShowAllIOSRequest AccountRequest = new ShowAllIOSRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(SplachActivity.this);
        queue.add(AccountRequest);
    }
}
