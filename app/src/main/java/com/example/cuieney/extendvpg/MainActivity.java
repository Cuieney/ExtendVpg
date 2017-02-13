package com.example.cuieney.extendvpg;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_main);
        ExtendViewPager vpg = ((ExtendViewPager) findViewById(R.id.viewpager));
        List<View> views = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            ImageView view = new ImageView(this);
            view.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            view.setImageResource(R.mipmap.ic_launcher);
            views.add(view);
        }
        vpg.setAdapter(new VpgAdapter(views));

        vpg.setPageTransformer(true,new DepthPageTransformer());

        ExtendViewPager viewPager = (ExtendViewPager) findViewById(R.id.viewpager);
        viewPager.setBackgroundImage(R.mipmap.bg_feet);
    }

}
