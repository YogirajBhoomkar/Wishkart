package com.g.wishkart01;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


public class login extends AppCompatActivity {

    private ImageView hello_there_img;
    private ImageView login_group_img;
    private EditText phone_edttxt;
    long animatinduration = 1000;
    long animation_group_duration = 1000;
    long verify_animation_group_duration = 700;
    long logi_go_duration = 500;
    Button re_enter_phone;
    String phone_number;
    Button send_otp;
    Button verify_btn;
    ImageView verify_group;
    EditText login_otp1;
    EditText login_otp2;
    EditText login_otp3;
    EditText login_otp4;
    EditText login_otp5;
    EditText login_otp6;
    TextView phone_txt;
    private String edittextCode;
    private FirebaseAuth mAuth;
    private String VerificationId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getWindow().getDecorView().setSystemUiVisibility(
                //   View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                //   | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                // |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        //   | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        mAuth = FirebaseAuth.getInstance();
        hello_there_img = findViewById(R.id.hello_there_img);
        login_group_img = findViewById(R.id.login_group_img);
        phone_edttxt = findViewById(R.id.phone_edttxt);
        ObjectAnimator animatorx = ObjectAnimator.ofFloat(hello_there_img, "Y", 300f);
        ObjectAnimator animator_group_x = ObjectAnimator.ofFloat(login_group_img, "y", 3000f, 0f);
        animatorx.setDuration(animatinduration);
        animator_group_x.setDuration(animation_group_duration);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorx, animator_group_x);
        animatorSet.start();
        send_otp = findViewById(R.id.send_otp);
        verify_group = findViewById(R.id.verify_group);
        verify_btn = findViewById(R.id.verify);
        verify_btn.setVisibility(View.INVISIBLE);
        login_otp1 = findViewById(R.id.login_otp_1);
        login_otp2 = findViewById(R.id.login_otp_2);
        login_otp3 = findViewById(R.id.login_otp_3);
        login_otp4 = findViewById(R.id.login_otp_4);
        login_otp5 = findViewById(R.id.login_otp_5);
        login_otp6 = findViewById(R.id.login_otp_6);
        phone_txt = findViewById(R.id.login_email_txt);
        re_enter_phone = findViewById(R.id.re_enter_phone);

        send_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                phone_number=phone_edttxt.getText().toString();
                Toast.makeText(login.this, "Now the phone number is: "+phone_number, Toast.LENGTH_LONG).show();

                if (phone_number.length() > 9) {

                    Toast.makeText(login.this, "OTP sent to: " + phone_number, Toast.LENGTH_SHORT).show();
                    sendVerificationCode(phone_number);


                } else {
                    Toast.makeText(login.this, "Enter Valid PhoneNumber", Toast.LENGTH_LONG).show();
                }


                ObjectAnimator animator_login_group_x = ObjectAnimator.ofFloat(login_group_img, "X", 0f, -3000f);
                animator_login_group_x.setDuration(logi_go_duration);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(animator_login_group_x);
                animatorSet.start();
                verify_btn.setVisibility(View.VISIBLE);
phone_edttxt.setVisibility(View.INVISIBLE);
                login_otp1.setVisibility(View.VISIBLE);
                login_otp2.setVisibility(View.VISIBLE);
                login_otp3.setVisibility(View.VISIBLE);
                login_otp4.setVisibility(View.VISIBLE);
                login_otp5.setVisibility(View.VISIBLE);
                login_otp6.setVisibility(View.VISIBLE);
                verify_group.setVisibility(View.VISIBLE);
                login_group_img.setVisibility(View.INVISIBLE);

                re_enter_phone.setVisibility(View.VISIBLE);
                verify_btn.setVisibility(View.VISIBLE);
                send_otp.setVisibility(View.INVISIBLE);


                login_otp1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        login_otp2.requestFocus();
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {


                    }
                });

                login_otp2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        login_otp3.requestFocus();
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {


                    }
                });


                login_otp3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        login_otp4.requestFocus();
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {


                    }
                });

                login_otp4.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        login_otp5.requestFocus();
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {


                    }
                });
                login_otp5.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        login_otp6.requestFocus();
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });


            }
        });
        re_enter_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_group_img.setVisibility(View.VISIBLE);
                ObjectAnimator animator_login_group = ObjectAnimator.ofFloat(login_group_img, "X", 0f, 0f);
                animator_login_group.setDuration(logi_go_duration);

                AnimatorSet animatorSet_1 = new AnimatorSet();
                animatorSet_1.playTogether(animator_login_group);
                animatorSet_1.start();


                verify_group.setVisibility(View.INVISIBLE);
                verify_btn.setVisibility(View.INVISIBLE);
                login_otp1.setVisibility(View.INVISIBLE);
                login_otp2.setVisibility(View.INVISIBLE);
                login_otp3.setVisibility(View.INVISIBLE);
                login_otp4.setVisibility(View.INVISIBLE);
                login_otp5.setVisibility(View.INVISIBLE);
                login_otp6.setVisibility(View.INVISIBLE);
                phone_edttxt.setVisibility(View.VISIBLE);
                send_otp.setVisibility(View.VISIBLE);
                re_enter_phone.setVisibility(View.INVISIBLE);

            }
        });
        verify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edittextCode = login_otp1.getText().toString()
                        + login_otp2.getText().toString()
                        + login_otp3.getText().toString()
                        + login_otp4.getText().toString()
                        + login_otp5.getText().toString()
                        + login_otp6.getText().toString();
                if (edittextCode.length() < 6 || edittextCode.isEmpty()) {
                    Toast.makeText(login.this, "Please Enter OTP", Toast.LENGTH_SHORT).show();
                    return;
                }


                VerifyCode(edittextCode);
            }
        });

    }
    private void VerifyCode (String Code){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(VerificationId, Code);
        signinWithCredential(credential);


    }
    private void signinWithCredential(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){


                            Intent inte=new Intent(login.this,bottom_navigation.class);
                            inte.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(inte);
                        }
                        else {

                            Toast.makeText(login.this, "Nahi !! Error Yetoy !!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    private void sendVerificationCode(String phone_str) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91"+phone_str,
                60,
                TimeUnit.SECONDS,
                this,
                mCallBack
        );
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            VerificationId = s;


        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            String Code= phoneAuthCredential.getSmsCode();
            if(Code!=null){

                Toast.makeText(login.this, "OTP SENT TO : " +phone_number, Toast.LENGTH_LONG).show();

                VerifyCode(Code);
            }

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(login.this,e.getMessage().toString(),Toast.LENGTH_LONG).show();
        }

    };






}



