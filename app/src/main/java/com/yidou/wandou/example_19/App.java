package com.yidou.wandou.example_19;

import android.app.Application;

import com.yidou.wandou.example_19.dao.DbCore;

/**
 * Created by Administrator on 2016/11/17.
 */

public class App extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        DbCore.init(this);
    }
}
