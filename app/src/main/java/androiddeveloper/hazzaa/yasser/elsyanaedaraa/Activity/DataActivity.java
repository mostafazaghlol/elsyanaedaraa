package androiddeveloper.hazzaa.yasser.elsyanaedaraa.Activity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androiddeveloper.hazzaa.yasser.elsyanaedaraa.Adapter.ordersAdapter;
import androiddeveloper.hazzaa.yasser.elsyanaedaraa.R;
import androiddeveloper.hazzaa.yasser.elsyanaedaraa.Volley.ShowIOSRequest;
import androiddeveloper.hazzaa.yasser.elsyanaedaraa.model.ShowIOS;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DataActivity extends AppCompatActivity {
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.pro)
    ProgressBar progressBar;
    @BindView(R.id.text1000) TextView text;
    List<ShowIOS> showIOS;
    ordersAdapter mOrdersAdapter;
    LinearLayoutManager linearLayoutManager;
    int type;
    public static ShowIOS ShowObject;
    public String color,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        ButterKnife.bind(this);
        name  = getIntent().getStringExtra("name");
        setTitle(getIntent().getStringExtra("name"));
        color = getIntent().getStringExtra("color");
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor(getIntent().getStringExtra("color"))));
        RelativeLayout relativeLayout = new RelativeLayout(this);
        TextView textView = new TextView(this);
        textView.setText(getIntent().getStringExtra("name"));
        textView.setTextSize(25);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(getResources().getColor(R.color.white));
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.left);
        imageView.setOnClickListener(v->{
            onBackPressed();
        });
        relativeLayout.addView(textView);
        relativeLayout.addView(imageView);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(relativeLayout);
        type = getIntent().getIntExtra("type",1);
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if(type == 1) {
                window.setStatusBarColor(this.getResources().getColor(R.color.color1));
            }else if(type == 2){
                window.setStatusBarColor(this.getResources().getColor(R.color.color2));
            }else if(type == 3){
                window.setStatusBarColor(this.getResources().getColor(R.color.color3));
            }else if(type == 4){
                window.setStatusBarColor(this.getResources().getColor(R.color.color4));
            }else if(type == 5){
                window.setStatusBarColor(this.getResources().getColor(R.color.color5));
            }else{
                window.setStatusBarColor(this.getResources().getColor(R.color.color6));
            }
            }
        //        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0000ff")));
        showIOS = new ArrayList<>();
        mOrdersAdapter = new ordersAdapter(this, showIOS, (view, position) -> {
            ShowObject = showIOS.get(position);
            Intent intent = new Intent(this,OrderActivity.class);
            intent.putExtra("type",type);
            intent.putExtra("name",name);
            intent.putExtra("color",color);
            startActivity(intent);
        });
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mOrdersAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        getData();


    }

    private void getData() {
        Response.Listener<String> responseListener =response -> {
            try {
                JSONArray object = new JSONArray(response);
                progressBar.setVisibility(View.INVISIBLE);
                for(int i=0;i<object.length();i++){
                    ShowIOS mShowIOS = new Gson().fromJson(object.getJSONObject(i).toString(),ShowIOS.class);
                    showIOS.add(mShowIOS);
                }
                mOrdersAdapter.notifyDataSetChanged();
                if(showIOS.size() == 0){
                    recyclerView.setVisibility(View.INVISIBLE);
                    text.setVisibility(View.VISIBLE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                progressBar.setVisibility(View.INVISIBLE);
            }
        };

        ShowIOSRequest AccountRequest = new ShowIOSRequest(responseListener, String.valueOf(type));
        RequestQueue queue = Volley.newRequestQueue(DataActivity.this);
        queue.add(AccountRequest);

    }
}
