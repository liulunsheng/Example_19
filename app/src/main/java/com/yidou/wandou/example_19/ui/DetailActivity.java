package com.yidou.wandou.example_19.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.yidou.wandou.example_19.Constances;
import com.yidou.wandou.example_19.R;
import com.yidou.wandou.example_19.dao.DataBeanDao;
import com.yidou.wandou.example_19.dao.DbCore;
import com.yidou.wandou.example_19.model.DataBean;
import com.yidou.wandou.example_19.present.DBOperate;
import com.yidou.wandou.example_19.present.DBUtils;

import java.io.Serializable;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DetailActivity extends AppCompatActivity
{
    @InjectView(R.id.detail_webView)
    WebView mWebView;

    @InjectView(R.id.detail_toolBar)
    Toolbar mToolbar;
    @InjectView(R.id.detail_fb)
    FloatingActionButton mActionButton;

    DBUtils mUtils;
    DbCore mCore;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.inject(this);

        initToolBars();

        Intent intent = getIntent();
        final DataBean datas = (DataBean) intent.getSerializableExtra(Constances.TAG);
        mWebView.loadUrl(datas.getUrl());
        setTitle(datas.getTitle());


        mUtils = new DBUtils(new DBOperate()
        {

            @Override
            public void notifySucess()
            {
                Toast.makeText(DetailActivity.this, "收藏成功！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void notifyFail()
            {
                Toast.makeText(DetailActivity.this, "您已收藏过此条新闻，切勿重复添加！！", Toast.LENGTH_SHORT).show();
            }
        }, mCore);

        //按钮点击事件
        mActionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mUtils.insertDB(datas);//插入数据
            }
        });


    }


    private void initToolBars()
    {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_shares:
                Toast.makeText(this, "分享成功！", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                this.finish();
            default:
                break;
        }
        return true;
    }
}
