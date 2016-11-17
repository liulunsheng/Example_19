package com.yidou.wandou.example_19;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.yidou.wandou.example_19.ui.MainActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LoadingActivity extends AppCompatActivity
{

    @InjectView(R.id.loading_images)
    ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        ButterKnife.inject(this);
        initImages();
    }

    private void initImages()
    {
        AlphaAnimation animation = new AlphaAnimation(1.0f, 1.0f);
        animation.setDuration(2000);
        mImageView.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {

            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                startActivity(intent);
                LoadingActivity.this.finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });
        animation.start();
    }

}
