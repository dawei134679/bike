package com.coolu.nokelock.bike.util;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by admin on 2017/9/6.
 */

public class AnimatorHelper{
    /**
     * 沿Y 轴平移
     * @param  view 控件
     * @param start 开始
     * @param end 结束
     * @param time 时间
     */
    public static void translationY(View view, float start, float end,long time){
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", start,end);
        animator.setDuration(time);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }
}
