package com.g.wishkart01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class test_profile extends AppCompatActivity {
    private FirebaseFirestore firestoreRef = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth=FirebaseAuth.getInstance();
    EditText email;
    String USERID;
    String profession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_profile);
        String email_str;
        final EditText email_txt;
        email_txt=findViewById(R.id.login_email_txt);
        Intent login = getIntent();
        email_str = login.getStringExtra(phone_for_otp.EXTRA_EMAIL);
        profession=login.getStringExtra(start_as.EXTRA_PROFESSION);
        email_txt.setText(email_str);
//       USERID=mAuth.getCurrentUser().getUid();
//        firestoreRef.collection(profession).document(email_str)
//                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                String name=documentSnapshot.getString("NAME");
//                email_txt.setText(name);
//
//            }
//        });

        findViewById(R.id.log_out_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent signup = new Intent(test_profile.this,start_as.class);
                startActivity(signup);
            }
        });
    }
}
