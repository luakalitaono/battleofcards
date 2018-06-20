package com.exercicio.cardsbattle;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        this.setContentView(R.layout.activity_menu);
        this.animateBackground();

        findViewById(R.id.btn_play).setOnClickListener(this);
    }


    private void animateBackground() {
        final ImageView backgroundOne = findViewById(R.id.background_one);
        if(backgroundOne != null) {
            backgroundOne.setVisibility(View.VISIBLE);
            final ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.setInterpolator(new LinearInterpolator());
            animator.setDuration(2000L);
            animator.setRepeatMode(ValueAnimator.REVERSE);
            animator.addUpdateListener(animation ->
                    backgroundOne.setAlpha((float) animation.getAnimatedValue()));
            animator.start();
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_play) {
            this.startActivity(new Intent(this, MainActivity.class));
            this.finish();
        }
    }
}
