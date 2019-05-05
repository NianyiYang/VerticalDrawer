package org.yang.lib;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * VerticalDrawerAnimator
 *
 * @author nianyi.yang
 * @date 2019/4/17 19:31
 */
public class VerticalDrawerAnimator {

    private AnimatorSet mAnimatorSet;

    private ViewAnimFactory mViewAnimFactory;

    private List<Animator> mAnimators;

    private ImageView mIndicatorView;

    private OnAnimationListener mListener;

    public VerticalDrawerAnimator(View view) {

        mAnimators = new ArrayList<>();

        mViewAnimFactory = new ViewAnimFactory();
        mViewAnimFactory.setView(view);

        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (mListener != null) {
                    mListener.onStart(animation);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (mListener != null) {
                    mListener.onEnd(animation);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    public void setListener(OnAnimationListener listener) {
        mListener = listener;
    }

    public void setIndicatorView(ImageView indicatorView) {
        mIndicatorView = indicatorView;
    }

    public VerticalDrawerAnimator addResizeAnimator(int startHeight, int endHeight, int duration) {

        ObjectAnimator resizeAnimator = ObjectAnimator.ofInt(mViewAnimFactory, "height", startHeight, endHeight);
        resizeAnimator.setDuration(duration);

        mAnimators.add(resizeAnimator);

        return this;
    }

    public VerticalDrawerAnimator addRotationAnimator(float startAngle, float endAngle, int duration) {

        ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(mIndicatorView, "rotation", startAngle, endAngle);
        rotationAnimator.setDuration(duration);

        mAnimators.add(rotationAnimator);

        return this;
    }

    public VerticalDrawerAnimator start() {

        if (mAnimatorSet != null) {
            mAnimatorSet.cancel();
            mAnimatorSet.playSequentially(mAnimators);
            mAnimatorSet.start();
        }

        mAnimators.clear();

        return this;
    }

    /**
     * 简单的回调监听动画完成事件
     */
    public interface OnAnimationListener {
        /**
         * 动画开始
         *
         * @param animation Animator
         */
        void onStart(Animator animation);

        /**
         * 动画结束
         *
         * @param animation Animator
         */
        void onEnd(Animator animation);
    }
}
