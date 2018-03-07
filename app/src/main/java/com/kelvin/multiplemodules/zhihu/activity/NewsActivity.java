package com.kelvin.multiplemodules.zhihu.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.kelvin.multiplemodules.R;
import com.kelvin.zhihulibrary.http.Constant;
import com.kelvin.zhihulibrary.http.HttpUtils;
import com.kelvin.zhihulibrary.model.News;
import com.kelvin.zhihulibrary.utils.LogUtils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {

    private String TAG = this.getClass().getSimpleName();
    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        try {
            int newId = getIntent().getIntExtra(Constant.NEWS_ID, -1);
            LogUtils.e(TAG, "newId:" + newId);
            requestNet(newId);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e(TAG, "参数为空，回退");
            finish();
            return;
        }
        initView();
    }

    private void initView() {
        wv = (WebView) findViewById(R.id.wv);
    }

    private void requestNet(int newId) {
        HttpUtils.news(newId, new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                LogUtils.e(TAG, "--OK--" + response.body().toString());
                wv.loadData(response.body().getBody(), "text/html; charset=UTF-8", null);//这种写法可以正确解码
//                getCSS(response);
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    private void getCSS(final Response<News> response1) {
        HttpUtils.getImage(response1.body().getCss().get(0), new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                LogUtils.e(TAG, "--onResponse--" + response.message());
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

}
