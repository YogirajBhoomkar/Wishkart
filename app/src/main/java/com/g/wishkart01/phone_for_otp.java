package com.g.wishkart01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class phone_for_otp extends AppCompatActivity {
   EditText phone;
    String name_str;
    String profession;
    String phone_str;
    String email_str;
    String password_str;
    public static final String EXTRA_NAME="com.g.wishkart01.EXTRA_NAME";
    public static final String EXTRA_EMAIL="com.g.wishkart01.EXTRA_EMAIL";
    public static final String EXTRA_PASSWORD="com.g.wishkart01.EXTRA_PASSWORD";
    public static final String EXTRA_PHONE="com.g.wishkart01.EXTRA_PHONE";
    public static final String EXTRA_PROFESSION="com.g.wishkart01.EXTRA_PROFESSION";
   Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_for_otp);

        this.getWindow().getDecorView().setSystemUiVisibility(
                //   View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                //   | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                // |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        //   | View.SYSTEM_UI_FLAG_FULLSCREEN
          | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        Intent i = getIntent();
        name_str = i.getStringExtra(signup.EXTRA_NAME);
        email_str = i.getStringExtra(signup.EXTRA_EMAIL);
        profession = i.getStringExtra(signup.EXTRA_PROFESSION);
        password_str = i.getStringExtra(signup.EXTRA_PASSWORD);
        phone=findViewById(R.id.login_email_txt);
        next=findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone_str=phone.getText().toString();
                Intent otp_intent=new Intent(phone_for_otp.this,otp_verification.class);
                otp_intent.putExtra(EXTRA_PHONE,phone_str);
                otp_intent.putExtra(EXTRA_NAME, name_str);
                otp_intent.putExtra(EXTRA_EMAIL, email_str);
                otp_intent.putExtra(EXTRA_PASSWORD, password_str);
                otp_intent.putExtra(EXTRA_PROFESSION,profession);
                startActivity(otp_intent);

            }
        });
    }
}
