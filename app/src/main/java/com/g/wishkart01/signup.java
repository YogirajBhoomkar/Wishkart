package com.g.wishkart01;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {
    Button next_btn;
    CheckBox chk;
    String profession;
    String name_str,email_str,pass_str,phone_str;
    EditText name_txt,email_txt,pass_txt,phone_txt;
    Button goto_to_login;
    /*------- CREATING GLOBAL CONSTATNTS ----------*/
    public static final String EXTRA_NAME="com.g.wishkart01.EXTRA_NAME";
    public static final String EXTRA_EMAIL="com.g.wishkart01.EXTRA_EMAIL";
    public static final String EXTRA_PASSWORD="com.g.wishkart01.EXTRA_PASSWORD";
    public static final String EXTRA_PROFESSION="com.g.wishkart01.EXTRA_PROFESSION";
    private FirebaseAuth auth;
    /*---------------------------------------------------------*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        this.getWindow().getDecorView().setSystemUiVisibility(
                //   View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                //   | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                // |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        //   | View.SYSTEM_UI_FLAG_FULLSCREEN
          | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        auth = FirebaseAuth.getInstance();
        chk=findViewById(R.id.terms_chkbx);

        Intent start_as_intent = getIntent();
        profession = start_as_intent.getStringExtra(start_as.EXTRA_PROFESSION);
        next_btn=findViewById(R.id.signup_main_btn);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*------- PASSING STRING VALUES TO NEXT ACTIVITY ----------*/
                name_txt=findViewById(R.id.login_name_txt);
                email_txt=findViewById(R.id.login_email_txt);
                pass_txt=findViewById(R.id.pass_txt);
                // phone_txt=findViewById(R.id.phone_txt);

                name_str=name_txt.getText().toString();
                email_str=email_txt.getText().toString();
                pass_str=pass_txt.getText().toString();
                if (TextUtils.isEmpty(name_txt.getText().toString())) {
                    Toast.makeText(signup.this, "Please Enter Full Name", Toast.LENGTH_LONG).show();
                }
                else if (TextUtils.isEmpty(email_txt.getText().toString())) {
                    Toast.makeText(signup.this, "Please Enter Email", Toast.LENGTH_LONG).show();

                }
                else if (TextUtils.isEmpty(pass_txt.getText().toString())) {
                    Toast.makeText(signup.this, "Please Enter Password", Toast.LENGTH_LONG).show();
                }
//                else if (TextUtils.isEmpty(phone_txt.getText().toString())) {
//                    Toast.makeText(signup.this, "Please Enter Phone Number", Toast.LENGTH_LONG).show();
//                }
//                else if(phone_txt.getText().toString().length()>10){
//                    Toast.makeText(signup.this, "Please Enter Valid Phone Number", Toast.LENGTH_LONG).show();
//                }
                else {
                    if(chk.isChecked()){


                        Intent i = new Intent(signup.this, phone_for_otp.class);
                        i.putExtra(EXTRA_NAME, name_str);
                        i.putExtra(EXTRA_EMAIL, email_str);
                        i.putExtra(EXTRA_PASSWORD, pass_str);
                        i.putExtra(EXTRA_PROFESSION, profession);
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(signup.this, "Please Check The CheckBox for Terms.", Toast.LENGTH_LONG).show();
                    }

                }
                /*---------------------------------------------------------*/
            }
        });

        goto_to_login=findViewById(R.id.goto_signin_btn);
        goto_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(signup.this,login.class);
                startActivity(i);
            }
        });
    }

}
