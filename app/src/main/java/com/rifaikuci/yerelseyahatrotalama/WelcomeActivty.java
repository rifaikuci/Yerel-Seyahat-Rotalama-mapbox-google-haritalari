package com.rifaikuci.yerelseyahatrotalama;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class WelcomeActivty extends AppCompatActivity implements View.OnClickListener {


    private ViewPager mPager;
    private  MpagerAdapter mpagerAdapter;
    private int[]  layouts ={
            R.layout.first_slide,

            R.layout.fourth_slide,

            R.layout.second_slide,
            R.layout.third_slide,


    };
    private Button bnSkip,bnNext;
    private LinearLayout Dots_Layout;
    private  ImageView[] dots;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /*  Uygulama kapatılınca nerede kaldı ise oradan devam  etmeyi sağlar ama bu uygulamada her kapanıp açıldığında  tekrar tanıtım yapmak için
       eklenmiştir . tanıtım tek bir defa olmasını istersek yorum satırlarını kaldırabilirsiniz.
        if(new PreferenceManager(this).checkPreference())
        {
            loadHome();
        }
*/

         ActionBar actionBar= getSupportActionBar();
         actionBar.hide();
        if(Build.VERSION.SDK_INT>=19)
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        else
        {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        setContentView(R.layout.activity_welcome_activty);
        mPager =(ViewPager) findViewById(R.id.viewPager);
        mpagerAdapter=new MpagerAdapter(layouts,this);
        mPager.setAdapter(mpagerAdapter);
        bnNext=(Button) findViewById(R.id.bnNext);
         bnSkip=(Button) findViewById(R.id.bnSkip);
         Dots_Layout =(LinearLayout) findViewById(R.id.dotsLayout);
        bnSkip.setOnClickListener(this);
         bnNext.setOnClickListener(this);
        createDots(0);

         mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
             @Override
             public void onPageScrolled(int i, float v, int i1) {

             }

             @Override
             public void onPageSelected(int i) {
                createDots(i);
                if(i==layouts.length-1)
                {
                    bnNext.setText("Başla");
                    bnSkip.setVisibility(View.INVISIBLE);
                }
                else
                {
                    bnNext.setText("Sonraki");
                    bnSkip.setVisibility(View.VISIBLE);
                }
             }

             @Override
             public void onPageScrollStateChanged(int i) {

             }
         });
    }

    private  void  createDots(int current_position)
    {
        if(Dots_Layout!=null)
            Dots_Layout.removeAllViews();

        dots = new ImageView[layouts.length];

        for (int i =0; i<layouts.length; i++)
        {
            dots[i] =new ImageView(this);
            if(i == current_position)
            {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_dots));
            }
            else
            {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.inactive_dots));
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
            ,ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4,0,4,0);

            Dots_Layout.addView(dots[i],params);
        }



    }

    @Override
    public void onClick(View v) {

         switch (v.getId())
         {
             case R.id.bnNext:
                 loadNextSlide();
                 break;



             case R.id.bnSkip:
                 loadHome();
                 new PreferenceManager(this).writePreference();
                 break;
         }
    }

    private void loadHome() {

     startActivity(new Intent(this,yerlerinGosterimi.class));
     finish();
     }

     private  void loadNextSlide()
     {
         int next_slide=mPager.getCurrentItem()+1;
         if(next_slide<layouts.length)
         {
             mPager.setCurrentItem(next_slide);
         }
         else
         {
             loadHome();
             new PreferenceManager(this).writePreference();

         }

     }
}
