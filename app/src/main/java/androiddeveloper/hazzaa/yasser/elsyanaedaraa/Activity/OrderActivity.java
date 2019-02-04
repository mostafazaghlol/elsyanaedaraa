package androiddeveloper.hazzaa.yasser.elsyanaedaraa.Activity;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import androiddeveloper.hazzaa.yasser.elsyanaedaraa.R;
import androiddeveloper.hazzaa.yasser.elsyanaedaraa.Volley.UpdateCondition;
import androiddeveloper.hazzaa.yasser.elsyanaedaraa.Volley.sendNotes;
import androiddeveloper.hazzaa.yasser.elsyanaedaraa.model.ShowIOS;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    public ShowIOS mShowIOS;
    @BindView(R.id.image1) ImageView imageView;
    @BindView(R.id.Txtname) TextView TXTNAME;
    @BindView(R.id.Txtphone2) TextView TXTPHONE;
    @BindView(R.id.Txtaddress) TextView TXTADDRESS;
    @BindView(R.id.Txtcode) TextView TXTCODE;
    @BindView(R.id.TxtNOTS) TextView TXTNOTES;
    @BindView(R.id.notes) LinearLayout LI;
    @BindView(R.id.TxtDate) TextView TxtDate;
    @BindView(R.id.order1) LinearLayout Bt1;
    @BindView(R.id.order2) LinearLayout Bt2;
    @BindView(R.id.order3) LinearLayout Bt3;
    @BindView(R.id.order4) LinearLayout Bt4;
    @BindView(R.id.TxtNOTSe) EditText Txt;
    @BindView(R.id.hi) RelativeLayout layout;
    @BindView(R.id.noplace) TextView txtnoplace;
    String lat,lng;
    LatLng  k;
    String code;
    @OnClick(R.id.save)
    public void onsvae(){
        String txt = Txt.getText().toString();

        ProgressDialog x = new ProgressDialog(this);
        x.setTitle(getString(R.string.app_name));
        x.setMessage("جارى التحميل");
        x.show();
        sendNotes mSendNotes = new sendNotes(new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        },mShowIOS.getIDAir(),txt);
        RequestQueue queue = Volley.newRequestQueue(OrderActivity.this);
        queue.add(mSendNotes);
        queue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                x.dismiss();
                Txt.setText("تم ارسال الملاحظات");
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        mShowIOS = DataActivity.ShowObject;
        Picasso.get().load("http://maintenanceksa.com/WebServices/images/"+mShowIOS.getImg()).placeholder(R.drawable.no_image).into(imageView);
        TXTNAME.setText(mShowIOS.getName());
        if(mShowIOS.getAddress().equals("No Place")){
            TXTADDRESS.setText("لم يتم تحديد المكان");
        }else {
            TXTADDRESS.setText(mShowIOS.getAddress());
        }
        TXTCODE.setText(mShowIOS.getCode());
        TXTPHONE.setText(mShowIOS.getPhone());
        TxtDate.setText(mShowIOS.getDate());
        code = mShowIOS.getCond();
        /*

        1 --> تم قبول طلبك
2 --> تم إرسال فنى صيانة
3 --> تم إنهاء طلبك
4 --> تم رفض طلبك
         */
        if(code.equals("1")){
            Bt1.setBackgroundColor(getResources().getColor(R.color.color1));
        }else if(code.equals("2")){
            Bt2.setBackgroundColor(getResources().getColor(R.color.color1));
        }else if(code.equals("3")){
            Bt3.setBackgroundColor(getResources().getColor(R.color.color1));
        }else{
            Bt4.setBackgroundColor(getResources().getColor(R.color.color1));
        }
        TXTPHONE.setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+mShowIOS.getPhone()));
            startActivity(intent);
        });
        if(mShowIOS.getNotes().equals("لا توجد ملاحظات")){
            TXTNOTES.setText("تم طلب خدمة واحدة");
        }else{
            TXTNOTES.setText(mShowIOS.getNotes());
        }
        lat = mShowIOS.getLat();
        lng =mShowIOS.getLan();

        k = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map1);
        if(Double.parseDouble(lat) == 0.0 && Double.parseDouble(lng) == 0.0){
            layout.setVisibility(View.GONE);
            txtnoplace.setVisibility(View.VISIBLE);
        }
        mapFragment.getMapAsync(this);
        setTitle(getIntent().getStringExtra("name"));
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
        int type = getIntent().getIntExtra("type", 1);
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

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        mMap.addMarker(new MarkerOptions().position(k).title("العنوان"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(k,15));

    }

    public void finish(View view) {
        int id = view.getId();
        String IDAIR =  mShowIOS.getIDAir();
        String codn;
        if(id == R.id.order1){
            codn = "1";
        }else if(id == R.id.order2){
            codn = "2";
        }else if(id == R.id.order3){
            codn = "3";
        }else{
            codn = "4";
        }

        UpdateCondition mUpdateCondition = new UpdateCondition(response -> {
            startActivity(new Intent(this,MainActivity.class));
            finish();
        },IDAIR,codn);
        RequestQueue queue = Volley.newRequestQueue(OrderActivity.this);
        queue.add(mUpdateCondition);
    }
}
