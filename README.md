# VerticalDrawer

[ ![Download](https://api.bintray.com/packages/nianyiyang/VerticalDrawer/VerticalDrawer/images/download.svg?version=1.0.0) ](https://bintray.com/nianyiyang/VerticalDrawer/VerticalDrawer/1.0.0/link)

A Simple Vertical Drawer View

# Demo
![image](https://github.com/NianyiYang/VerticalDrawer/blob/master/drawer.gif)

# How to use

## Add dependency

In project `build.gradle`

```
allprojects {
     repositories {
        ...
        jcenter()
    }
}
```

In app `build.gradle`

```
dependencies {
    implementation 'org.yang.lib:VerticalDrawer:1.0.0'
}
```

## Usage

```
VerticalDrawerView drawer = findViewById(R.id.vd);

// add indicator
drawer.setIndicator(indicatorDrawable, indicatorDrawable.getIntrinsicWidth(), indicatorDrawable.getIntrinsicHeight());

// add background
drawer.setBackground(ContextCompat.getDrawable(this, R.drawable.drawer_view_background));

// add content. using same content
drawer.setCollapsedView(contentView, dp2px(33));
drawer.setExpandedView(contentView);

// or using different content
drawer.setCollapsedView(collapseContentView);
drawer.setExpandedView(expandContentView);
```

