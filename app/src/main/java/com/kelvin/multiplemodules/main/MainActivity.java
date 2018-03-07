package com.kelvin.multiplemodules.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kelvin.multiplemodules.R;
import com.kelvin.multiplemodules.main.adapter.MainPagerAdapter;
import com.kelvin.multiplemodules.main.utils.LogUtils;
import com.kelvin.multiplemodules.main.utils.ScreenUtils;
import com.kelvin.multiplemodules.main.utils.SizeUtils;
import com.kelvin.multiplemodules.main.widget.CirclePageIndicator;
import com.kelvin.multiplemodules.main.widget.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    //第一个展示的项
    public static int FIRST_APPEARED_ITEM = 1;

    private List<View> mViewList = new ArrayList<>();

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.indicator)
    CirclePageIndicator indicator;
    @BindView(R.id.login_tv)
    TextView loginTv;
    @BindView(R.id.verify_btn)
    Button verifyBtn;
    @BindView(R.id.activity_land)
    LinearLayout activityLand;

    private String from;

    @OnClick({R.id.login_tv, R.id.verify_btn})
    public void enter(View view) {
        switch (view.getId()) {
            case R.id.login_tv:
                startActivity(new Intent(mContext, LoopActivity.class));
                break;
            case R.id.verify_btn:
                int height = ScreenUtils.getScreenHeight(mContext);
                int width = ScreenUtils.getScreenWidth(mContext);
                int barHeight = ScreenUtils.getStatusBarHeight(mContext);
                int statusBarHeight = ScreenUtils.getActionBarHeight(MainActivity.this);
                LogUtils.i(TAG, "---height:" + height + "width" + width + "barHeight" + barHeight + "statusBarHeight" + statusBarHeight);
//                startActivity(new Intent(mContext, VerifyActivity.class));
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        //从登录界面进来，隐藏登录按钮
        from = getIntent().getStringExtra("from");
        if (!TextUtils.isEmpty(from) || "login".equals(from)) {
            loginTv.setVisibility(View.INVISIBLE);
        }

        getLastIntent();
        initData();

        viewpager.setAdapter(new MainPagerAdapter(mContext, mViewList, viewpager, false));
        indicator.setViewPager(viewpager, FIRST_APPEARED_ITEM);
        viewpager.setPageMargin(SizeUtils.dp2px(mContext, getResources().getDimension(R.dimen.dp_4)));

    }

    private void getLastIntent() {
    }

    /**
     * 初始化数据: 视图，图片资源，文字资源, 并设置点击事件
     */
    private void initData() {

        int[] resIds = new int[]{R.drawable.qianqi_image, R.drawable.tendering_image, R.drawable.accoune_image};
        String[] titleStr = new String[]{getString(R.string.pre_develop), getString(R.string.bid), getString(R.string.me)};
        String[] titleEngStr = new String[]{getString(R.string.pre_develop_en), getString(R.string.bid_en), getString(R.string.me_en)};

        int gap = getResources().getDimensionPixelOffset(R.dimen.dp_8);
        //左右距离屏幕距离之和 40dp
        int rest = getResources().getDimensionPixelOffset(R.dimen.dp_40);
        int adapterWidth = ScreenUtils.getScreenWidth(mContext) - rest;

        for (int i = 0; i < resIds.length; i++) {

            View root = mInflater.inflate(R.layout.adapter_main, null);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(adapterWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            root.setLayoutParams(param);

            RoundedImageView ivTitle = (RoundedImageView) root.findViewById(R.id.iv_title);
            ivTitle.setImageResource(resIds[i]);

            TextView tvChinese = (TextView) root.findViewById(R.id.tv_title_ch);
            tvChinese.setText(titleStr[i]);

            TextView tvEnglish = (TextView) root.findViewById(R.id.tv_title_en);
            tvEnglish.setText(titleEngStr[i]);

            mViewList.add(root);

        }

    }
}
