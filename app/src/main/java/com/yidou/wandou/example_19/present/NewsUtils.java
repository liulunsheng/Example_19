package com.yidou.wandou.example_19.present;

import android.content.Context;
import android.widget.Toast;

import com.yidou.wandou.example_19.Constances;
import com.yidou.wandou.example_19.model.News;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/17.
 */

public class NewsUtils
{
    GetDatas mUtils;
    Context mContext;

    public NewsUtils(GetDatas utils, Context context)
    {
        mUtils = utils;
        mContext = context;
    }

    public void loadMoreDatas(String msg)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(msg)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        retrofit.create(HttpUtils.class)
                .getNewsData("top", Constances.KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<News>()
                {
                    @Override
                    public void onCompleted()
                    {

                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(News news)
                    {
                        mUtils.getListData(news.getResult().getData());
                    }
                });
    }
}
