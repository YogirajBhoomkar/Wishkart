package com.g.wishkart01;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class intro extends AppCompatActivity {
    Button skip_btn;
    ViewPager viewPager;
    CustomSwipeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new CustomSwipeAdapter(this);
        viewPager.setAdapter(adapter);

    }
}
