package com.littlegeektoys.www.picturepurrfect;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.lang.ref.SoftReference;

/**
 * Created by Vanessa on 5/6/16.
 */
public class Splash extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        ImageView imageView = (ImageView) findViewById(R.id.splash_image);
        final Animation an = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);
        //final Animation an2 = AnimationUtils.loadAnimation(getBaseContext(), R.anim.)

        imageView.startAnimation(an);
       an.setAnimationListener(new Animation.AnimationListener() {
           @Override
           public void onAnimationStart(Animation animation) {

           }

           @Override
           public void onAnimationEnd(Animation animation) {
               finish();
               Intent i  = new Intent(Splash.this, MainActivity.class);
               startActivity(i);
           }

           @Override
           public void onAnimationRepeat(Animation animation) {

           }
       });


    }


}
