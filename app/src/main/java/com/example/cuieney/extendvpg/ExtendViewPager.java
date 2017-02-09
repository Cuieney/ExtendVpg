package com.example.cuieney.extendvpg;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import java.lang.reflect.Field;

/**
 * Created by paohaile on 17/2/7.
 */

public class ExtendViewPager extends RelativeLayout {
    private Context mContext;
    private Drawable mBackgroundImg;
    private HorizontalScrollView mBackground;
    private ImageView mBgImg;
    private ViewPager mViewPager;
    private PagerAdapter mWrapperAdapter;

    public ExtendViewPager(Context context) {
        super(context);
        init(context);
    }

    public ExtendViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
                R.styleable.ExtendViewPager);

        mBackgroundImg = mTypedArray.getDrawable(R.styleable.ExtendViewPager_backgroundImage);

        mTypedArray.recycle();
        init(context);
    }

    public ExtendViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
                R.styleable.ExtendViewPager);

        mBackgroundImg = mTypedArray.getDrawable(R.styleable.ExtendViewPager_backgroundImage);

        mTypedArray.recycle();
        init(context);

    }

    private void init(Context context){
        this.mContext = context;
        addBackground();
        addViewPager();

        addView(mBackground);
        addView(mViewPager);

    }

    private void addViewPager() {
        mViewPager = new ViewPager(mContext);
        LayoutParams sp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        sp.addRule(RelativeLayout.CENTER_IN_PARENT);
        mViewPager.setLayoutParams(sp);

        settingVpg();
    }

    private void settingVpg() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                float widthOfPagers = mViewPager.getWidth() * mWrapperAdapter.getCount();
                // 背景图片的宽的
                float widthOfScroll = mBgImg.getWidth();

                // ViewPager可滑动的总长度
                float moveWidthOfPagers = widthOfPagers - mViewPager.getWidth();
                // 背景图的可滑动总长度
                float moveWidthOfScroll = widthOfScroll - mViewPager.getWidth();

                // 可滑动距离比例
                float ratio = moveWidthOfScroll / moveWidthOfPagers;
                // 当前Pager的滑动距离
                float currentPosOfPager = position * mViewPager.getWidth() + positionOffsetPixels;

                // 背景滑动到对应位置
                mBackground.scrollTo((int) ((currentPosOfPager * ratio) / 3),
                        mBackground.getScrollY());
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setAdapter(PagerAdapter adapter){
        this.mWrapperAdapter = adapter;
        mViewPager.setAdapter(adapter);
    }

    public void setCurrentItem(int item){
        mViewPager.setCurrentItem(item);
    }

    public void setOffscreenPageLimit(int limit){
        mViewPager.setOffscreenPageLimit(limit);
    }

    public void setViewPagerScrollSpeed(int duration){
        try {
            Field mScroller = null;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller( mViewPager.getContext());
            scroller.setmDuration(duration);
            mScroller.set( mViewPager, scroller);
        }catch(NoSuchFieldException e){

        }catch (IllegalArgumentException e){

        }catch (IllegalAccessException e){

        }
    }

    public void setBackgroundImage(int resId){
        mBgImg.setImageResource(resId);
    }

    public void setBackgroundImage(@NonNull Bitmap bitmap){
        mBgImg.setImageBitmap(bitmap);
    }


    public ViewPager getViewpager(){
        return mViewPager;
    }


    private void addBackground() {
        mBackground = new HorizontalScrollView(mContext);
        mBackground.setLayoutParams(new LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        mBgImg = new ImageView(mContext);
        mBgImg.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT));
        mBgImg.setScaleType(ImageView.ScaleType.FIT_XY);
        if (mBackgroundImg != null) {
            mBgImg.setImageDrawable(mBackgroundImg);
        }else{
            Log.i("oye", "addBackground: ");
        }
        mBackground.addView(mBgImg);
    }


    private static class FixedSpeedScroller extends Scroller{
        private int mDuration = 1500;
        public FixedSpeedScroller(Context context) {
            super(context);
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator, boolean flywheel) {
            super(context, interpolator, flywheel);
        }

        public int getmDuration() {
            return mDuration;
        }

        public void setmDuration(int mDuration) {
            this.mDuration = mDuration;
        }
    }
}
