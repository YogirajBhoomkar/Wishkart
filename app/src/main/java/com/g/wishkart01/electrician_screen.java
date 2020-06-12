package com.g.wishkart01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class electrician_screen extends AppCompatActivity {
    ImageButton fan;
    ImageButton switches;
    ImageButton tubelight;
    ImageButton geyser;
    ImageButton tv;
    ImageButton other;
    @Nullable
    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.activity_electrician_screen,container,false);
//    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electrician_screen);
        fan=findViewById(R.id.imageButton_fan);
        switches=findViewById(R.id.imageButton_switch);
        tubelight=findViewById(R.id.imageButton_tube);
        geyser=findViewById(R.id.imageButton_geyser);
        tv=findViewById(R.id.imageButton_tv);
        other=findViewById(R.id.imageButton_e_other);

        fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open_elect_jobs=new Intent(electrician_screen.this,elect_jobs.class);
                startActivity(open_elect_jobs);
            }
        });

        switches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open_elect_jobs=new Intent(electrician_screen.this,elect_jobs.class);
                startActivity(open_elect_jobs);
            }
        });
        tubelight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open_elect_jobs=new Intent(electrician_screen.this,elect_jobs.class);
                startActivity(open_elect_jobs);
            }
        });

        geyser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open_elect_jobs=new Intent(electrician_screen.this,elect_jobs.class);
                startActivity(open_elect_jobs);
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open_elect_jobs=new Intent(electrician_screen.this,elect_jobs.class);
                startActivity(open_elect_jobs);
            }
        });

        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open_elect_jobs=new Intent(electrician_screen.this,elect_jobs.class);
                startActivity(open_elect_jobs);
            }
        });


    }
}
