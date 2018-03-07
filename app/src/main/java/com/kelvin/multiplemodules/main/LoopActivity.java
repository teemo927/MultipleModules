package com.kelvin.multiplemodules.main;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.kelvin.multiplemodules.R;
import com.kelvin.multiplemodules.main.widget.BannerLoopAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoopActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.dots_layout)
    LinearLayout dotsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop);
        ButterKnife.bind(this);

        List<Integer> lists = new ArrayList<>();
        lists.add(R.drawable.accoune_image);
        lists.add(R.drawable.qianqi_image);
        lists.add(R.drawable.tendering_image);
        viewpager.setAdapter(new BannerLoopAdapter(getApplicationContext(), lists, viewpager, dotsLayout));
    }
}
