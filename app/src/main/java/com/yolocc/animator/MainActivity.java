package com.yolocc.animator;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static int RADIUS = 300;
    private static int VIEW_COUNT = 5;
    private boolean mIsMenuOpen = false;

    private List<View> mViews;

    ImageView black;
    ImageView blue;
    ImageView green;
    ImageView orange;
    ImageView purple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView hello = (TextView) findViewById(R.id.hello);
//        ObjectAnimator.ofFloat(hello,"translationY",-hello.getHeight()).start();
//        ObjectAnimator translationX = ObjectAnimator.ofFloat(hello, "translationX", 0, 90);
//        translationX.setRepeatCount(ValueAnimator.INFINITE);
//        translationX.setRepeatMode(ValueAnimator.REVERSE);
//        translationX.start();
//        ObjectAnimator translationY = ObjectAnimator.ofFloat(hello, "translationY", 0, 90);
//        translationY.setRepeatCount(ValueAnimator.INFINITE);
//        translationY.setRepeatMode(ValueAnimator.REVERSE);
//        translationY.start();
//
//        ValueAnimator colorAnim = ObjectAnimator.ofInt(hello,"backgroundColor",0xFFFF8080,0xFF9090FF);
//        colorAnim.setDuration(3000);
//        colorAnim.setEvaluator(new ArgbEvaluator());
//        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
//        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
////        colorAnim.setInterpolator(new LinearInterpolator());
////        colorAnim.setInterpolator(new AccelerateDecelerateInterpolator());
////        colorAnim.setInterpolator(new DecelerateInterpolator());
//        colorAnim.start();
//
//        AnimatorSet set = new AnimatorSet();
//        set.playTogether(
//                ObjectAnimator.ofFloat(hello, "rotationX", 0, 360),
//                ObjectAnimator.ofFloat(hello, "rotationY", 0, 180),
//                ObjectAnimator.ofFloat(hello, "rotation", 0, -90),
//                ObjectAnimator.ofFloat(hello, "translationX", 0, 90),
//                ObjectAnimator.ofFloat(hello, "translationY", 0, 90),
//                ObjectAnimator.ofFloat(hello, "scaleX", 1, 1.5f),
//                ObjectAnimator.ofFloat(hello, "scaleY", 1, 0.5f),
//                ObjectAnimator.ofFloat(hello, "alpha", 1, 0.25f, 1)
//        );
//        set.setDuration(5 * 1000).start();

        mViews = new ArrayList<>();

        ImageView menu = (ImageView) findViewById(R.id.menu);
        menu.setOnClickListener(this);

        black = (ImageView) findViewById(R.id.black);
        blue = (ImageView) findViewById(R.id.blue);
        green = (ImageView) findViewById(R.id.green);
        orange = (ImageView) findViewById(R.id.orange);
        purple = (ImageView) findViewById(R.id.purple);

        mViews.add(black);
        mViews.add(blue);
        mViews.add(green);
        mViews.add(orange);
        mViews.add(purple);

        black.setOnClickListener(this);
        blue.setOnClickListener(this);
        green.setOnClickListener(this);
        orange.setOnClickListener(this);
        purple.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int count = mViews.size();
        if (v.getId() == R.id.menu) {
            if (!mIsMenuOpen) {
                mIsMenuOpen = true;
                for (int i = 0; i < mViews.size(); i++) {
                    doAnimateOpen(mViews.get(i), i, count, RADIUS);
                }

            } else {
                mIsMenuOpen = false;
                for (int i = mViews.size() - 1; i >= 0; i--) {
                    doAnimateClose(mViews.get(i), i, count, RADIUS);
                }
            }
        } else

        {

        }

    }

    /**
     * @param view   执行动画的view
     * @param index  view在序列中的顺序
     * @param total  动画序列的个数
     * @param radius 动画半径
     */
    private void doAnimateOpen(View view, int index, int total, int radius) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }

        double degree = Math.PI * index / ((total - 1) * 2);
        int translationX = (int) (radius * Math.cos(degree));
        int translationY = (int) (radius * Math.sin(degree));

        AnimatorSet set = new AnimatorSet();

        //平移,缩放,透明度动画
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", 0, translationX),
                ObjectAnimator.ofFloat(view, "translationY", 0, translationY),
                ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f),
                ObjectAnimator.ofFloat(view, "alpha", 0f, 1)
        );
        //动画越来越慢 减速差值器
        set.setInterpolator(new DecelerateInterpolator());
        //动画周期为500ms
        set.setDuration(1 * 500).start();
    }

    private void doAnimateClose(final View view, int index, int total, int radius) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }

        double degree = Math.PI * index / ((total - 1) * 2);
        int translationX = (int) (radius * Math.cos(degree));
        int translationY = (int) (radius * Math.sin(degree));

        AnimatorSet set = new AnimatorSet();

        //平移,缩放,透明度动画
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", translationX, 0),
                ObjectAnimator.ofFloat(view, "translationY", translationY, 0),
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f),
                ObjectAnimator.ofFloat(view, "scaleY", 1f, 0f),
                ObjectAnimator.ofFloat(view, "alpha", 1f, 0)
        );
        //动画越来越慢 减速差值器
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        //动画周期为500ms
        set.setDuration(1 * 500).start();
    }
}
