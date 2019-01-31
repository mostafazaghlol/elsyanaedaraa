package androiddeveloper.hazzaa.yasser.elsyanaedaraa.Activity;

import android.app.ActionBar;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
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
import androiddeveloper.hazzaa.yasser.elsyanaedaraa.model.ShowIOS;
import butterknife.BindView;
import butterknife.ButterKnife;

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
    String lat,lng;
    LatLng  k;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        mShowIOS = DataActivity.ShowObject;
        Picasso.get().load("http://maintenanceksa.com/WebServices/images/"+mShowIOS.getImg()).placeholder(R.drawable.no_image).into(imageView);
        TXTNAME.setText(mShowIOS.getName());
        TXTADDRESS.setText(mShowIOS.getAddress());
        TXTCODE.setText(mShowIOS.getCode());
        TXTPHONE.setText(mShowIOS.getPhone());
        TXTPHONE.setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+mShowIOS.getPhone()));
            startActivity(intent);
        });
        if(mShowIOS.getNotes().equals("لا توجد ملاحظات")){
            LI.setVisibility(View.GONE);
        }else{
            TXTNOTES.setText(mShowIOS.getNotes());
        }
        lat = mShowIOS.getLat();
        lng =mShowIOS.getLan();
        k = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map1);
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
