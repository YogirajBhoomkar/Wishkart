package com.g.wishkart01;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class bottom_navigation extends AppCompatActivity implements profile.OnFragmentInteractionListener {
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private String location;
    TextView locationset;
    ImageButton electrician_btn;
    ImageButton plumber_btn;
//    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//           switch(menuItem.getItemId()){
//               case R.id.Home:
//                   fragmentTransaction = fragmentManager.beginTransaction();
//                   fragmentTransaction.replace(R.id.fragment,new HomeFragment());
//                   fragmentTransaction.commit();
//                   return true;
//           }
//            return false;
//        }
//    };

        private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
                =new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId())
                {
                    case R.id.nav_home:
                        fragmentTransaction=fragmentManager.beginTransaction();
                        //fragmentTransaction.replace(R.id.fragment_area,new HomeFragment());
                        fragmentTransaction.commit();
                        return true;

                }




                return false;
            }
        };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        Intent bottom_nav = getIntent();
        location = bottom_nav.getStringExtra(setlocation.EXTRA_LOCATION);
        locationset=findViewById(R.id.locationText);
        locationset.setText(location);
        electrician_btn=findViewById(R.id.electrician_btn);
//        electrician_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent open_electrician=new Intent(bottom_navigation.this,electrician_screen.class);
//                startActivity(open_electrician);
//            }
//        });
//        plumber_btn=findViewById(R.id.plumber_btn);
//        plumber_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent open_plumber=new Intent(bottom_navigation.this,plumber_screen.class);
//                startActivity(open_plumber);
//            }
//        });

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}