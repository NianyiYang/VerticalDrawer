package org.yang.lib;

import android.view.View;

/**
 * ViewAnimFactory
 *
 * @author nianyi.yang
 * @date 2019/4/17 14:48
 */
public class ViewAnimFactory {

    private View view;

    public void setView(View view) {
        this.view = view;
    }

    public void setWidth(int width) {
        view.getLayoutParams().width = width;
        view.requestLayout();
    }

    public void setHeight(int height) {
        view.getLayoutParams().height = height;
        view.requestLayout();
    }
}
