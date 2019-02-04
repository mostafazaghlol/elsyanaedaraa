package androiddeveloper.hazzaa.yasser.elsyanaedaraa.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.onesignal.OneSignal;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import androiddeveloper.hazzaa.yasser.elsyanaedaraa.R;
import androiddeveloper.hazzaa.yasser.elsyanaedaraa.Volley.ShowAllIOSRequest;
import androiddeveloper.hazzaa.yasser.elsyanaedaraa.model.ShowIOS;
import androiddeveloper.hazzaa.yasser.elsyanaedaraa.utils.screenwithoutAction;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    Intent intent ;
    @BindView(R.id.air_con_txt)TextView air;
    @BindView(R.id.water_txt)TextView water;
    @BindView(R.id.elect_txt)TextView elect;
    @BindView(R.id.garden_txt)TextView garden;
    @BindView(R.id.nell_txt)TextView nell;
    @BindView(R.id.paint_txt)TextView paint;
    private List<ShowIOS> showIOS;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        showIOS = new ArrayList<>();
        progressDialog = new ProgressDialog(this);
        screenwithoutAction.FullScreen(this);
        OneSignal.setSubscription(true);
        intent = new Intent(this,DataActivity.class);
        if(SplachActivity.boo){
            sendRequest();
        }else {
            if (SplachActivity.One == 0) {
                air.setVisibility(View.INVISIBLE);
            }
            if (SplachActivity.Two == 0) {
                elect.setVisibility(View.INVISIBLE);
            }
            if (SplachActivity.Three == 0) {
                garden.setVisibility(View.INVISIBLE);
            }
            if (SplachActivity.Four == 0) {
                nell.setVisibility(View.INVISIBLE);
            }
            if (SplachActivity.Five == 0) {
                paint.setVisibility(View.INVISIBLE);
            }
            if (SplachActivity.Sex == 0) {
                water.setVisibility(View.INVISIBLE);
            }
            air.setText(SplachActivity.One.toString());
            elect.setText(SplachActivity.Two.toString());
            garden.setText(SplachActivity.Three.toString());
            water.setText(SplachActivity.Sex.toString());
            nell.setText(SplachActivity.Four.toString());
            paint.setText(SplachActivity.Five.toString());
        }
    }
    public void go(View view) {
        int id = view.getId();
        switch (id){
            case R.id.air_con:
                intent.putExtra("type",1);
                intent.putExtra("name","تكييف");
                intent.putExtra("color","#ed6860");
                startActivity(intent);
                finish();
                break;
            case R.id.nell:
                intent.putExtra("type",4);
                intent.putExtra("name","تركيب");
                intent.putExtra("color","#eb9b30");
                startActivity(intent);
                finish();
                break;
            case R.id.garden:
                intent.putExtra("type",3);
                intent.putExtra("name","تنسيق حدائق");
                intent.putExtra("color","#48be68");
                startActivity(intent);
                finish();
                break;
            case R.id.elect:
                intent.putExtra("type",2);
                intent.putExtra("name","كهرباء");
                intent.putExtra("color","#fdd006");
                startActivity(intent);
                finish();
                break;
            case R.id.paint:
                intent.putExtra("type",5);
                intent.putExtra("name","ديكور");
                intent.putExtra("color","#7b67ac");
                startActivity(intent);
                finish();
                break;
            case R.id.water:
                intent.putExtra("type",6);
                intent.putExtra("name","سباكة");
                intent.putExtra("color","#2cbdb6");
                startActivity(intent);
                finish();
                break;
        }
    }

    private void sendRequest() {
        progressDialog.setTitle(getString(R.string.app_name));
        progressDialog.setMessage("جارى تحضير البيانات");
        progressDialog.show();
        Response.Listener<String> responseListener = response -> {
            try {
                SplachActivity.One=0;
                SplachActivity.Two=0;
                SplachActivity.Three=0;
                SplachActivity.Four=0;
                SplachActivity.Five=0;
                SplachActivity.Sex=0;
                progressDialog.dismiss();
                JSONArray object = new JSONArray(response);
                for (int i = 0; i < object.length(); i++) {
                    ShowIOS mShowIOS = new Gson().fromJson(object.getJSONObject(i).toString(), ShowIOS.class);
                    showIOS.add(mShowIOS);
                }
                for (int i = 0; i < showIOS.size(); i++) {
                    String id = showIOS.get(i).getType();
                    if (showIOS.get(i).getView().equals("0")) {
                        if (id.equals("1")) {
                            SplachActivity.One++;
                        } else if (id.equals("2")) {
                            SplachActivity.Two++;
                        } else if (id.equals("3")) {
                            SplachActivity.Three++;
                        } else if (id.equals("4")) {
                            SplachActivity.Four++;
                        } else if (id.equals("5")) {
                            SplachActivity.Five++;
                        } else if (id.equals("6")) {
                            SplachActivity.Sex++;
                        }
                    }
                }
                if(SplachActivity.One == 0){
                    air.setVisibility(View.INVISIBLE);
                }
                if(SplachActivity.Two == 0){
                    elect.setVisibility(View.INVISIBLE);
                }
                if(SplachActivity.Three == 0){
                    garden.setVisibility(View.INVISIBLE);
                }
                if(SplachActivity.Four == 0){
                    nell.setVisibility(View.INVISIBLE);
                }
                if(SplachActivity.Five == 0){
                    paint.setVisibility(View.INVISIBLE);
                }
                if(SplachActivity.Sex == 0){
                    water.setVisibility(View.INVISIBLE);
                }
                air.setText(SplachActivity.One.toString());
                elect.setText(SplachActivity.Two.toString());
                garden.setText(SplachActivity.Three.toString());
                water.setText(SplachActivity.Sex.toString());
                nell.setText(SplachActivity.Four.toString());
                paint.setText(SplachActivity.Five.toString());

            } catch (JSONException e) {
                e.printStackTrace();
                //progressBar.setVisibility(View.INVISIBLE);
            }
        };

        ShowAllIOSRequest AccountRequest = new ShowAllIOSRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(AccountRequest);
    }
}
