package com.yidou.wandou.example_19.present;

import com.yidou.wandou.example_19.model.DataBean;
import com.yidou.wandou.example_19.model.News;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 *
 */

public interface HttpUtils
{
    @GET("index")
    Observable<News> getNewsData(@Query("type") String top, @Query("key") String key);

}
