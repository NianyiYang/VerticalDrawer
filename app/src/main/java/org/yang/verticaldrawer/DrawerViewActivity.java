package org.yang.verticaldrawer;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import org.yang.lib.VerticalDrawerView;
import org.yang.verticaldrawer.content.CollapseContentView;
import org.yang.verticaldrawer.content.ContentView;
import org.yang.verticaldrawer.content.ExpandContentView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

/**
 * DrawerViewActivity
 *
 * @author nianyi.yang
 * @date 2019/4/25 18:28
 */
public class DrawerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        VerticalDrawerView drawerSame = findViewById(R.id.vd_same);
        VerticalDrawerView drawerDifferent = findViewById(R.id.vd_different);

        Drawable indicatorDrawable = ContextCompat.getDrawable(this, R.drawable.ic_arrow_down);

        if (indicatorDrawable != null) {
            drawerSame.setIndicator(indicatorDrawable, indicatorDrawable.getIntrinsicWidth(), indicatorDrawable.getIntrinsicHeight());
            drawerDifferent.setIndicator(indicatorDrawable, indicatorDrawable.getIntrinsicWidth(), indicatorDrawable.getIntrinsicHeight());
        }

        ContentView contentView = new ContentView(this);

        drawerSame.setCollapsedView(contentView, ScreenUtils.dp2px(33));
        drawerSame.setExpandedView(contentView);

        drawerSame.setBackground(ContextCompat.getDrawable(this, R.drawable.drawer_view_background));


        drawerDifferent.setCollapsedView(new CollapseContentView(this));
        drawerDifferent.setExpandedView(new ExpandContentView(this));

        drawerDifferent.setBackground(ContextCompat.getDrawable(this, R.drawable.drawer_view_background));
    }

}
