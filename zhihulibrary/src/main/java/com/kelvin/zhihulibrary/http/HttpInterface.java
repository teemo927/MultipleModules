package com.kelvin.zhihulibrary.http;

import com.kelvin.zhihulibrary.model.LatestNews;
import com.kelvin.zhihulibrary.model.News;
import com.kelvin.zhihulibrary.model.StartImage;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * retrofit 配置网络请求接口
 * Created by Administrator on 2016/10/11.
 */
interface HttpInterface {

    @GET(Constant.start_image)
    Call<StartImage> startImage();

    @GET("{url}")
    Call<ResponseBody> getImage(@Path("url") String url);

    @GET(Constant.latest_news)
    Call<LatestNews> latestNews();

    @GET(Constant.NEWS + Constant.BEFORE + "{date}")
    Call<LatestNews> beforeNews(@Path("date") String date);

    @GET(Constant.NEWS + "{new_id}")
    Call<News> news(@Path("new_id") int new_id);
}
