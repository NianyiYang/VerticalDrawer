package org.yang.verticaldrawer;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * ScreenUtils
 *
 * @author nianyi.yang
 * @date 2019/4/9 15:47
 */
public class ScreenUtils {


    /**
     * dp转px
     *
     * @return
     */
    public static int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, Resources.getSystem().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @return
     */
    public static int sp2px(float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, Resources.getSystem().getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @param pxVal
     * @return
     */
    public static float px2dp(float pxVal) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px to sp
     *
     * @param pxValue pxValue
     * @return sp
     */
    public static int px2sp(float pxValue) {
        float scaleDensity = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / scaleDensity + 0.5f);
    }
}
