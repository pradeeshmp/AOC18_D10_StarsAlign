/*
 * *
 *  * Created by Pradeesh on 27/09/22, 11:03 AM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 27/09/22, 11:31 AM
 *
 */

package com.pradeeshmp.aocstarsalign;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends Activity {

    ImageView logoImageViewRight;
    ImageView logoImageViewLeft;
    TextView appTitleTextView;

    Animation logoFromRight;
    Animation logoFromLeft;
    Animation appTitleFade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logoImageViewLeft = findViewById(R.id.ewsSplashLogoImgLeftImgv);
        logoImageViewRight = findViewById(R.id.ewsSplashLogoImgRightImgv);
        appTitleTextView = findViewById(R.id.ewsSplashLogoTitleTv);

        logoFromLeft = AnimationUtils.loadAnimation(this, R.anim.fromtopcorner);
        logoFromRight = AnimationUtils.loadAnimation(this, R.anim.frombottomcorner);
        appTitleFade = AnimationUtils.loadAnimation(this, R.anim.textfadeout);

        logoImageViewLeft.setAnimation(logoFromLeft);
        logoImageViewRight.setAnimation(logoFromRight);
        appTitleTextView.setAnimation(appTitleFade);

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        }, 4000);
    }
}
