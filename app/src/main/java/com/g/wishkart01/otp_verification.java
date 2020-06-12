package com.g.wishkart01;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.TimeUnit;

public class otp_verification extends AppCompatActivity {


    TextView text;
    Button get_otp_btn;
    FirebaseAuth auth;
    TextView phone_txtview;
    Button verify;
    String message;
    String name_str;
    String profession;
    String phone_str;
    String email_str;
    String password_str;
    String edittextCode;
    private ProgressBar progressBar;
    private String VerificationId;
    EditText otp_1, otp_2, otp_3, otp_4, otp_5, otp_6;
    private FirebaseFirestore firestoreRef = FirebaseFirestore.getInstance();
    private static final String TAG = "MainActivity";

    public static final String EXTRA_NAME="com.g.wishkart01.EXTRA_NAME";
    public static final String EXTRA_EMAIL="com.g.wishkart01.EXTRA_EMAIL";
    public static final String EXTRA_PASSWORD="com.g.wishkart01.EXTRA_PASSWORD";
    public static final String EXTRA_PROFESSION="com.g.wishkart01.EXTRA_PROFESSION";
    private FirebaseAuth mAuth;
    TextView resend_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        this.getWindow().getDecorView().setSystemUiVisibility(
                //   View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                //   | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                // |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        //   | View.SYSTEM_UI_FLAG_FULLSCREEN
          | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        /*-------RECIEVING STRINGS FROM PREVIOUS ACTIVITY----------*/
        Intent otp_intent = getIntent();
        profession = otp_intent.getStringExtra(phone_for_otp.EXTRA_PROFESSION);
        name_str = otp_intent.getStringExtra(phone_for_otp.EXTRA_NAME);
        phone_str = otp_intent.getStringExtra(phone_for_otp.EXTRA_PHONE);
        email_str = otp_intent.getStringExtra(phone_for_otp.EXTRA_EMAIL);
        password_str = otp_intent.getStringExtra(phone_for_otp.EXTRA_PASSWORD);


        otp_1 = findViewById(R.id.login_otp_1);
        otp_2 = findViewById(R.id.otp_2);
        otp_3 = findViewById(R.id.otp_3);
        otp_4 = findViewById(R.id.otp_4);
        otp_5 = findViewById(R.id.otp_5);
        otp_6 = findViewById(R.id.otp_6);
        verify = findViewById(R.id.verify_btn);
        verify.setVisibility(View.VISIBLE);
        phone_txtview = findViewById(R.id.login_email_txt);
        phone_txtview.setText(phone_str);
        if (phone_str.length() > 9) {

            Toast.makeText(this,"OTP sent to: "+phone_str, Toast.LENGTH_LONG).show();
            sendVerificationCode(phone_str);


        } else {
            Toast.makeText(otp_verification.this, "Enter Valid PhoneNumber", Toast.LENGTH_LONG).show();
        }

        otp_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                otp_2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        otp_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                otp_3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });


        otp_3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                otp_4.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        otp_4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                otp_5.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });
        otp_5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                otp_6.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                edittextCode=otp_1.getText().toString()
                        +otp_2.getText().toString()
                        +otp_3.getText().toString()
                        +otp_4.getText().toString()
                        +otp_5.getText().toString()
                        +otp_6.getText().toString();
                if(edittextCode.length()<6 || edittextCode.isEmpty()){
                    Toast.makeText(otp_verification.this, "Please Enter OTP", Toast.LENGTH_SHORT).show();
                    return;
                }


                VerifyCode(edittextCode);
            }
        });

        resend_txt=findViewById(R.id.resend_txt);
        resend_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phone_str.length() > 9) {

                    Toast.makeText(otp_verification.this, "OTP sent to: "+phone_str, Toast.LENGTH_SHORT).show();
                    sendVerificationCode(phone_str);


                } else {
                    Toast.makeText(otp_verification.this, "Enter Valid PhoneNumber", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void VerifyCode(String Code){
         PhoneAuthCredential credential= PhoneAuthProvider.getCredential(VerificationId,edittextCode);
         signinWithCredential(credential);


}

    private void signinWithCredential(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){








//                            if(profession.equals("Customer")){
//
//                                Map<String,Object> customer_map=new HashMap<>();
//                                customer_map.put(KEY_Name,name_str);
//                                customer_map.put(KEY_EMAIL,email_str);
//                                customer_map.put(KEY_PASSWORD,password_str);
//                                firestoreRef.collection("Customer").document(email_str).set(customer_map)
//                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                            @Override
//                                            public void onSuccess(Void aVoid) {
//                                                Toast.makeText(otp_verification.this,"Registered Succesfully",Toast.LENGTH_LONG).show();
//                                            }
//                                        })
//                                        .addOnFailureListener(new OnFailureListener() {
//                                            @Override
//                                            public void onFailure(@NonNull Exception e) {
//                                                Toast.makeText(otp_verification.this,"Registered Failed",Toast.LENGTH_LONG).show();
//                                                Log.d(TAG, e.toString());
//
//                                            }
//                                        });
//                            }
//                            else if(profession.equals("Delivery Boy")){
//                                Map<String,Object> deliveryboy_map=new HashMap<>();
//                                deliveryboy_map.put(KEY_Name,name_str);
//                                deliveryboy_map.put(KEY_EMAIL,email_str);
//                                deliveryboy_map.put(KEY_PASSWORD,password_str);
//                                firestoreRef.collection("Delivery Boys").document(email_str).set(deliveryboy_map)
//                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                            @Override
//                                            public void onSuccess(Void aVoid) {
//                                                Toast.makeText(otp_verification.this,"Registered Succesfully",Toast.LENGTH_LONG).show();
//                                            }
//                                        })
//                                        .addOnFailureListener(new OnFailureListener() {
//                                            @Override
//                                            public void onFailure(@NonNull Exception e) {
//                                                Toast.makeText(otp_verification.this,"Registered Failed",Toast.LENGTH_LONG).show();
//                                                Log.d(TAG, e.toString());
//
//                                            }
//                                        });
//                            }
//                            else {
//                                Map<String,Object> shopowner_map=new HashMap<>();
//                                shopowner_map.put(KEY_Name,name_str);
//                                shopowner_map.put(KEY_EMAIL,email_str);
//                                shopowner_map.put(KEY_PASSWORD,password_str);
//                                firestoreRef.collection("Shop Owners").document(email_str).set(shopowner_map)
//                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                            @Override
//                                            public void onSuccess(Void aVoid) {
//                                                Toast.makeText(otp_verification.this,"Registered Succesfully",Toast.LENGTH_LONG).show();
//                                            }
//                                        })
//                                        .addOnFailureListener(new OnFailureListener() {
//                                            @Override
//                                            public void onFailure(@NonNull Exception e) {
//                                                Toast.makeText(otp_verification.this,"Registered Failed",Toast.LENGTH_LONG).show();
//                                                Log.d(TAG, e.toString());
//
//                                            }
//                                        });
//
//                            }

                            Intent inte=new Intent(otp_verification.this,setlocation.class);
                            inte.putExtra(EXTRA_NAME, name_str);
                            inte.putExtra(EXTRA_EMAIL, email_str);
                            inte.putExtra(EXTRA_PASSWORD, password_str);
                            inte.putExtra(EXTRA_PROFESSION, profession);

                          //  inte.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(inte);
                            finish();
                        }
                        else {

                            Toast.makeText(otp_verification.this, "Nahi !! Error Yetoy !!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void sendVerificationCode(String phone_str) {
        progressBar.setVisibility(View.VISIBLE);
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

                Toast.makeText(otp_verification.this, "OTP SENT TO : " +phone_str, Toast.LENGTH_LONG).show();

               // VerifyCode(Code);
            }

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
           Toast.makeText(otp_verification.this,e.getMessage().toString(),Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onStart() {
       super.onStart();
//        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
//            Intent inte=new Intent(otp_verification.this,test_profile.class);
//            startActivity(inte);
//        }
    }
}
