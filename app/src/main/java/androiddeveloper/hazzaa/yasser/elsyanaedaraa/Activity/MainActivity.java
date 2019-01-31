package androiddeveloper.hazzaa.yasser.elsyanaedaraa.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.onesignal.OneSignal;

import androiddeveloper.hazzaa.yasser.elsyanaedaraa.R;
import androiddeveloper.hazzaa.yasser.elsyanaedaraa.utils.screenwithoutAction;

public class MainActivity extends AppCompatActivity {
    Intent intent ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screenwithoutAction.FullScreen(this);
        OneSignal.setSubscription(true);
        intent = new Intent(this,DataActivity.class);
    }
    public void go(View view) {
        int id = view.getId();
        switch (id){
            case R.id.air_con:
                intent.putExtra("type",1);
                intent.putExtra("name","تكييف");
                intent.putExtra("color","#ed6860");
                startActivity(intent);
                break;
            case R.id.nell:
                intent.putExtra("type",4);
                intent.putExtra("name","تركيب");
                intent.putExtra("color","#eb9b30");
                startActivity(intent);
                break;
            case R.id.garden:
                intent.putExtra("type",3);
                intent.putExtra("name","تنسيق حدائق");
                intent.putExtra("color","#48be68");
                startActivity(intent);
                break;
            case R.id.elect:
                intent.putExtra("type",2);
                intent.putExtra("name","كهرباء");
                intent.putExtra("color","#fdd006");
                startActivity(intent);
                break;
            case R.id.paint:
                intent.putExtra("type",5);
                intent.putExtra("name","ديكور");
                intent.putExtra("color","#7b67ac");
                startActivity(intent);
                break;
            case R.id.water:
                intent.putExtra("type",6);
                intent.putExtra("name","سباكة");
                intent.putExtra("color","#2cbdb6");
                startActivity(intent);
                break;
        }
    }
}
