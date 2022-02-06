package com.example.shoppingkart;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    ViewPager mslideViewPager;
    LinearLayout mDotLayout;
    Button backbtn, nextbtn, skipbtn;
    ImageView i;

    TextView[] dots;
    ViewPagerAdapter viewPagerAdapter;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backbtn = findViewById(R.id.backbtn);
        skipbtn = findViewById(R.id.skipbtn);
        nextbtn = findViewById(R.id.nextbtn);
        i = findViewById(R.id.imageview);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((getitem( 0)) >0)
                {
                    mslideViewPager.setCurrentItem(getitem(-1),true);
                }
            }
        });

        skipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getitem(0) < 3)
                {
                    Intent intent =new Intent(MainActivity.this,Welcome_page.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getitem(0) < 3) {

                    mslideViewPager.setCurrentItem(getitem(1), true);

                }

                else
                {

                    Intent intent =new Intent(MainActivity.this,Welcome_page.class);
                    startActivity(intent);
                    finish();

                }

            }
        });

        mslideViewPager = (ViewPager) findViewById(R.id.sliderviewer);
        mDotLayout = (LinearLayout) findViewById(R.id.indicator_layout);

        viewPagerAdapter= new ViewPagerAdapter(this);
        mslideViewPager.setAdapter(viewPagerAdapter);

        setUpindicator(0);
        mslideViewPager.addOnPageChangeListener(viewlistener);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void  setUpindicator(int position){

        dots = new TextView[4];
        mDotLayout.removeAllViews();

        for(int i=0 ; i< dots.length ; i++ )
        {

            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.inactive,getApplicationContext().getTheme()));
            mDotLayout.addView(dots[i]);

        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            dots[position].setTextColor(getResources().getColor(R.color.active,getApplicationContext().getTheme()));
        }

    }

    ViewPager.OnPageChangeListener viewlistener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onPageSelected(int position) {

            setUpindicator(position);
            if (position > 0){

                nextbtn.setText("Next");
                backbtn.setVisibility(View.VISIBLE);
                i.setVisibility(View.VISIBLE);
                if (position == 3)
                {
                    nextbtn.setText("Finish");
                    skipbtn.setVisibility(View.INVISIBLE);
                }
                else
                {
                    skipbtn.setVisibility(View.VISIBLE);
                }
            }else {

                backbtn.setVisibility(View.INVISIBLE);
                i.setVisibility(View.INVISIBLE);
                nextbtn.setText("Next");
            }


        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

    };

    private int getitem(int i)
    {
        return  mslideViewPager.getCurrentItem() + i;
    }
}
