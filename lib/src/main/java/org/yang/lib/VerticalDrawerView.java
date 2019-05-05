package org.yang.lib;

import android.animation.Animator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * VerticalDrawerView 一个支持纵向展开收起的抽屉布局
 *
 * @author nianyi.yang
 * @date 2019/4/17 14:14
 */
public class VerticalDrawerView extends RelativeLayout {

    public enum PositionEnum {
        // left
        LEFT,
        // right
        RIGHT
    }

    /**
     * 是否为展开状态，默认为false
     */
    private boolean mIsExpanded = false;
    /**
     * 默认初始化高度
     */
    private int mInitialHeight;
    /**
     * 展开时的高度
     */
    private int mExpandedHeight;
    /**
     * 收起时的高度
     */
    private int mCollapsedHeight;
    /**
     * 指示器容器
     */
    private RelativeLayout mIndicatorContainer;
    /**
     * 被折叠时显示的View
     */
    private View mCollapsedView;
    /**
     * 被展开时显示的View
     */
    private View mExpandedView;

    /**
     * 指示器处于左边还是右边
     */
    private PositionEnum mPositionEnum = PositionEnum.RIGHT;
    /**
     * 指示器Drawable
     */
    private Drawable mIndicatorDrawable;
    /**
     * Animator
     */
    private VerticalDrawerAnimator mDrawerAnimator;

    private LayoutParams mExpandedParams;
    private LayoutParams mCollapsedParams;

    public VerticalDrawerView(@NonNull Context context) {
        super(context);
        init();
    }

    public VerticalDrawerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VerticalDrawerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mDrawerAnimator = new VerticalDrawerAnimator(this);
        mDrawerAnimator.setListener(new VerticalDrawerAnimator.OnAnimationListener() {
            @Override
            public void onStart(Animator animation) {
                beforeAnimation();
            }

            @Override
            public void onEnd(Animator animation) {
                afterAnimation();
            }
        });
        mIndicatorContainer = new RelativeLayout(getContext());
        mIndicatorContainer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onTrigger();
            }
        });
    }

    /**
     * 设置展开收起的状态
     */
    public void setExpanded(boolean expanded) {
        mIsExpanded = expanded;
    }

    /**
     * 设置指示器图标
     */
    public void setIndicator(Drawable drawable, int width, int height) {
        mIndicatorDrawable = drawable;
        mIndicatorDrawable.setBounds(0, 0, width, height);
    }

    /**
     * 设置收起时对外展示的View
     *
     * @param collapsedView   收起时对外展示的View
     * @param collapsedHeight 外部设置的固定高度
     */
    public void setCollapsedView(View collapsedView, int collapsedHeight) {
        setCollapsedView(collapsedView);
        mCollapsedHeight = collapsedHeight;
    }

    /**
     * 设置收起时对外展示的View
     *
     * @param collapsedView 收起时对外展示的View
     */
    public void setCollapsedView(View collapsedView) {
        mCollapsedView = collapsedView;
        mCollapsedView.measure(0, 0);
    }

    /**
     * 设置展开时对外展示的View
     *
     * @param expandedView   收起时对外展示的View
     * @param expandedHeight 外部设置的固定高度
     */
    public void setExpandedView(View expandedView, int expandedHeight) {
        setExpandedView(expandedView);
        mExpandedHeight = expandedHeight;
    }

    /**
     * 设置展开时对外展示的View
     *
     * @param expandedView 收起时对外展示的View
     */
    public void setExpandedView(View expandedView) {
        mExpandedView = expandedView;
        mExpandedView.measure(0, 0);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        initializeExpanded();
        initializeCollapsed();

        // 设置初始状态
        if (mIsExpanded) {
            if (mExpandedView != null) {
                addView(mExpandedView, mExpandedParams);
                mInitialHeight = mExpandedHeight;
            }
        } else {
            if (mCollapsedView != null) {
                addView(mCollapsedView, mCollapsedParams);
                mInitialHeight = mCollapsedHeight;
            }
        }

        // 设置指示器
        ImageView indicatorView = new ImageView(getContext());

        if (mIndicatorDrawable != null) {
            indicatorView.setImageDrawable(mIndicatorDrawable);
        }

        LayoutParams indicatorParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        indicatorParams.addRule(CENTER_IN_PARENT);

        mIndicatorContainer.addView(indicatorView, indicatorParams);

        mDrawerAnimator.setIndicatorView(indicatorView);

        LayoutParams containerParams = new LayoutParams(mInitialHeight, mInitialHeight);
        containerParams.addRule(mPositionEnum == PositionEnum.RIGHT ? ALIGN_PARENT_RIGHT : ALIGN_PARENT_LEFT);
        containerParams.addRule(ALIGN_PARENT_TOP);

        addView(mIndicatorContainer, containerParams);
    }

    private void initializeCollapsed() {
        if (mCollapsedHeight == 0) {
            // 没有设置默认收起高度
            if (mCollapsedView != null) {
                // 取CollapsedView的高度作为收起高度
                mCollapsedHeight = mCollapsedView.getMeasuredHeight();
            } else {
                // 取一个默认值作为收起高度
                mCollapsedHeight = dp2px(30);
            }
        }

        mCollapsedParams = new LayoutParams(LayoutParams.MATCH_PARENT, mCollapsedHeight);
        mCollapsedParams.addRule(ALIGN_PARENT_LEFT);
        mCollapsedParams.addRule(ALIGN_PARENT_TOP);
    }

    private void initializeExpanded() {
        if (mExpandedHeight == 0) {
            // 没有设置默认展开高度
            if (mExpandedView != null) {
                // 取ExpandedView的高度作为展开高度
                mExpandedHeight = mExpandedView.getMeasuredHeight();
            } else {
                // 取一个默认值作为展开高度
                mExpandedHeight = dp2px(100);
            }
        }

        mExpandedParams = new LayoutParams(LayoutParams.MATCH_PARENT, mExpandedHeight);
        mExpandedParams.addRule(ALIGN_PARENT_LEFT);
        mExpandedParams.addRule(ALIGN_PARENT_TOP);
    }

    private void onTrigger() {

        if (mIsExpanded) {
            // 收起动画
            mDrawerAnimator.addResizeAnimator(mExpandedHeight, mCollapsedHeight, 500)
                    .addRotationAnimator(180F, 0F, 150)
                    .start();

        } else {
            // 展开动画
            mDrawerAnimator.addResizeAnimator(mCollapsedHeight, mExpandedHeight, 500)
                    .addRotationAnimator(0F, 180F, 150)
                    .start();
        }
    }

    private void beforeAnimation() {

        // 收起到展开的时候要先布局，不然会一片空白
        if (!mIsExpanded) {

            removeView(mCollapsedView);
            addView(mExpandedView, mExpandedParams);
        }

        mIndicatorContainer.setEnabled(false);
    }

    private void afterAnimation() {

        if (mIsExpanded) {

            removeView(mExpandedView);
            addView(mCollapsedView, mCollapsedParams);
        }

        mIsExpanded = !mIsExpanded;

        mIndicatorContainer.setEnabled(true);
    }

    public static int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, Resources.getSystem().getDisplayMetrics());
    }
}
