package com.g.wishkart01;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {
    Button button;

    TextView register_txt;
    public static final String EXTRA_FLAG="";
    int Permission_All = 1;
   public static final String flag="com.g.wishkart01.EXTRA_FLAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences=getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        String FirstTime=preferences.getString("FirstTimeInstall","");
        if(FirstTime.equals("Yes")){
            Intent login_inte=new Intent(MainActivity.this,start_as.class);
           // login_inte.putExtra(flag,"secondtime");
            startActivity(login_inte);
            finish();
        }
        else
        {

            SharedPreferences.Editor editor=preferences.edit();
            editor.putString("FirstTimeInstall","Yes");
            editor.apply();
            button = findViewById(R.id.get_started);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openActivity2();
                }
            });

            String[] Permissions = {
                    Manifest.permission.SEND_SMS,
                    Manifest.permission.RECEIVE_SMS,
                    Manifest.permission.READ_SMS,
                    android.Manifest.permission.INTERNET,
                    android.Manifest.permission.ACCESS_WIFI_STATE,
                    android.Manifest.permission.ACCOUNT_MANAGER,
                    android.Manifest.permission.BATTERY_STATS,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
            };
            if(!hasPermissions(this, Permissions)){
                ActivityCompat.requestPermissions(this, Permissions, Permission_All);
            }
        }


    }
    public static boolean hasPermissions(Context context, String... permissions){

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && context!=null && permissions!=null){
            for(String permission: permissions){
                if(ActivityCompat.checkSelfPermission(context, permission)!= PackageManager.PERMISSION_GRANTED){
                    return  false;
                }
            }
        }
        return true;
    }


        public void openActivity2() {
            Intent intent = new Intent(this, intro.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }


    }

