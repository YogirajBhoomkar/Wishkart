package com.g.wishkart01;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class CustomSwipeAdapter extends PagerAdapter {
    private int[] image_resources={
            R.drawable.intro_set_loc,
            R.drawable.intro_daily,
            R.drawable.intro_services,
            R.drawable.intro_delivery
    };
    private  int[] small_images={

            R.drawable.intro_set_loc,
            R.drawable.intro_set_loc,
            R.drawable.intro_welcome,
            R.drawable.intro_welcome
    };
    private Context ctx;
    private LayoutInflater layoutInflater;
     public CustomSwipeAdapter(Context ctx){
         this.ctx=ctx;
     }
    @Override
    public int getCount() {
        return image_resources.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view== (ConstraintLayout)object) ;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view =layoutInflater.inflate(R.layout.swipe_layout,container,false);
        ImageView imageView=(ImageView) item_view.findViewById(R.id.imageView);
       // ImageView imageView1 = (ImageView) item_view.findViewById(R.id.imageView);
        imageView.setImageResource(image_resources[position]);
       // imageView.setImageResource(small_images[position]);
        Button button = item_view.findViewById(R.id.skip_intro);
        if (position == 3) {
            button.setBackground(ctx.getDrawable(R.drawable.lets_go));
        } else {
            button.setBackground(ctx.getDrawable(R.drawable.skip_btn));
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent skip_into=new Intent(ctx,start_as.class);

                ctx.startActivity(skip_into);

                ((Activity) ctx).overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);

            }
        });
        container.addView(item_view);
        return item_view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((ConstraintLayout)object);
    }
}
