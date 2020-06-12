package com.g.wishkart01;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class start_as extends AppCompatActivity {
    Button customer,deliveryBoy,shopOwner;
    public static final String EXTRA_PROFESSION="com.g.wishkart01.EXTRA_PROFESSION";
    String Profession;
    String flag="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_as);
        this.getWindow().getDecorView().setSystemUiVisibility(
                //   View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                //   | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                // |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        //   | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        Intent i = getIntent();
        flag = i.getStringExtra(MainActivity.flag);
        VideoView mVideoView2 = (VideoView)findViewById(R.id.videoView);
        String uriPath2 = "wishkart_animation/wishkart_animation.mov";
        Uri uri2 = Uri.parse(uriPath2);
        mVideoView2.setVideoURI(uri2);
        mVideoView2.requestFocus();
        mVideoView2.start();

        VideoView mVideoView3 = (VideoView) findViewById(R.id.videoView);
        // VideoView mVideoView = new VideoView(this);
        String uriPath = "android.resource://" + getPackageName() + "/" + R.raw.logo_animation;
        Uri uri3 = Uri.parse(uriPath);
        mVideoView3.setVideoURI(uri3);
        mVideoView3.requestFocus();
        mVideoView3.start();

//        if(flag.equals("secondtime")){
//            Intent inte=new Intent(start_as.this,login.class);
//            startActivity(inte);
//        }else{

            customer=findViewById(R.id.getStartedAs_customer);
            deliveryBoy=findViewById(R.id.getStartedAs_deliveryBoy);
            shopOwner=findViewById(R.id.getStartedAs_showowner);
            customer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Profession="Customer";
                    Intent start_as_intent=new Intent(start_as.this,signup.class);
                    start_as_intent.putExtra(EXTRA_PROFESSION,Profession);
                    startActivity(start_as_intent);

                }
            });

            deliveryBoy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Profession="Delivery Boy";
                    Intent start_as_intent=new Intent(start_as.this,signup.class);
                    start_as_intent.putExtra(EXTRA_PROFESSION,Profession);
                    startActivity(start_as_intent);
                }
            });


            shopOwner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Profession="Shop Owner";
                    Intent start_as_intent=new Intent(start_as.this,signup.class);
                    start_as_intent.putExtra(EXTRA_PROFESSION,Profession);
                    startActivity(start_as_intent);
                }
            });

      //  }
    }

}
