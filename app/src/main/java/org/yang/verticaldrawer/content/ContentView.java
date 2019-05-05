package org.yang.verticaldrawer.content;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import org.yang.verticaldrawer.R;

import androidx.annotation.Nullable;

/**
 * ContentView
 *
 * @author nianyi.yang
 * @date 2019/4/18 15:06
 */
public class ContentView extends LinearLayout {


    public ContentView(Context context) {
        super(context);
        init();
    }

    public ContentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ContentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View rootView = View.inflate(getContext(), R.layout.view_drawer_content, this);

    }
}
