# VerticalDrawer

[ ![Download](https://api.bintray.com/packages/nianyiyang/maven/VerticalDrawer/images/download.svg?version=1.0.0) ](https://bintray.com/nianyiyang/maven/VerticalDrawer/1.0.0/link)

一个简单纵向抽屉的实现

# Demo

**Gif图录制有问题，实际效果没有图片中那么卡顿**

![image](https://github.com/NianyiYang/VerticalDrawer/blob/master/drawer.gif)

# 如何使用

## 添加依赖

项目中 `build.gradle`

```
allprojects {
     repositories {
        ...
        jcenter()
    }
}
```

模块中 `build.gradle`

```
dependencies {
    implementation 'org.yang.lib:VerticalDrawer:1.0.0'
}
```

## 使用方法

```
VerticalDrawerView drawer = findViewById(R.id.vd);

// 添加指示器
drawer.setIndicator(drawable, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

// 添加背景
drawer.setBackground(ContextCompat.getDrawable(this, R.drawable.drawer_view_background));

// 添加内容，使用同一个内容视图
drawer.setCollapsedView(contentView, dp2px(33));
drawer.setExpandedView(contentView);

// 或者使用不同的内容视图
drawer.setCollapsedView(collapseContentView);
drawer.setExpandedView(expandContentView);
```
