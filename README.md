# Extendable ViewPager

![Viewpager icon](https://github.com/Cuieney/ExtendVpg/blob/master/app/src/main/res/mipmap-hdpi/second.gif)

## Overview
1. 可设置背景图片
2. 支持修改viewpager滑动速度
3. 支持修改viewpager滑动动画

### Usage
```
<com.example.cuieney.extendvpg.ExtendViewPager
        android:id="@+id/viewpager"
        app:backgroundImage="@mipmap/bg_feet"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
```
or 设置背景图片

```
ExtendViewPager viewPager = (ExtendViewPager) findViewById(R.id.viewpager);
viewPager.setBackgroundImage(R.mipmap.bg_feet);

```


#### Attributes（布局中调用）

Attributes | format | describe
------------ | ------------- | ------------
background_image | reference  | 设置背景图片
pager_scroll_speed | integer  | 设置viewpager滑动速度

#### Method（代码中调用）

methodName  | describe
------------ | ------------
setBackgroundImage | 设置背景图片
setViewPagerScrollSpeed | 设置viewpager滑动速度
setPageTransformer | 设置viewpager滑动动画
