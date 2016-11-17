package com.yidou.wandou.example_19.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.listener.OnRecyclerItemClickListener;
import com.github.library.view.LoadType;
import com.squareup.picasso.Picasso;
import com.yidou.wandou.example_19.Constances;
import com.yidou.wandou.example_19.R;
import com.yidou.wandou.example_19.dao.DbCore;
import com.yidou.wandou.example_19.model.DataBean;
import com.yidou.wandou.example_19.model.News;
import com.yidou.wandou.example_19.present.DBOperate;
import com.yidou.wandou.example_19.present.DBUtils;
import com.yidou.wandou.example_19.present.GetDatas;
import com.yidou.wandou.example_19.present.HttpUtils;
import com.yidou.wandou.example_19.present.NewsUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.http.Query;
import rx.Observable;

public class MainActivity extends AppCompatActivity
{

    @InjectView(R.id.main_toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.main_drawerLayout)
    DrawerLayout mDrawerLayout;
    @InjectView(R.id.main_navigation)
    NavigationView mNavigationView;

    @InjectView(R.id.main_swipeRefresh)
    SwipeRefreshLayout mRefreshLayout;


    @InjectView(R.id.main_recycler)
    RecyclerView mRecyclerView;
    BaseRecyclerAdapter<DataBean> mAdapter;

    NewsUtils mUtils;//工具类  所有的逻辑和运算都在这里
    DbCore mCore;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        initToolBars();
        initDrawer();
        initViews();
        initUtils();

    }

    private void initUtils()
    {
        mUtils = new NewsUtils(new GetDatas()
        {
            @Override
            public void getListData(final List<DataBean> mlist)
            {
                mAdapter.setData(mlist);
                mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
                {
                    @Override
                    public void onRefresh()
                    {
                        mHandler.postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                mAdapter.setData(mlist);
                                setTitle("新闻");
                            }
                        }, 5000);
                        mRefreshLayout.setRefreshing(false);
                    }

                });

            }
        }, this);
        mUtils.loadMoreDatas(Constances.PATH);
    }

    private void initViews()
    {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new BaseRecyclerAdapter<DataBean>(this, null, R.layout.item)
        {
            @Override
            protected void convert(BaseViewHolder helper, final DataBean item)
            {
                helper.setText(R.id.texts_title, item.getTitle());
                helper.setText(R.id.texts_authorname, item.getAuthor_name());
                helper.setText(R.id.texts_date, item.getDate());
                ImageView imageView = (ImageView) helper.getConvertView().findViewById(R.id.images_name);
                Picasso.with(MainActivity.this).load(item.getThumbnail_pic_s()).fit().into(imageView);
                helper.setOnClickListener(R.id.item_linear, new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                        intent.putExtra(Constances.TAG, item);
                        startActivity(intent);
                    }
                });
            }
        };
        mRecyclerView.setAdapter(mAdapter);
        mRefreshLayout.setColorSchemeResources(R.color.colorRed);
        mAdapter.setLoadMoreType(LoadType.SWAP);
        mAdapter.addNoMoreView();
    }

    private void initDrawer()
    {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.action_collections:
//                        Toast.makeText(MainActivity.this, "您还没有收藏哦，小主!", Toast.LENGTH_SHORT).show();
                        List<DataBean> been = mCore.getDaoSession().getDataBeanDao().loadAll();
                        mAdapter.setData(been);
                        setTitle("收藏");
                        mDrawerLayout.closeDrawer(Gravity.LEFT, true);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void initToolBars()
    {
        mToolbar.setNavigationIcon(R.mipmap.ic_menu_black_24dp);
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                mDrawerLayout.openDrawer(Gravity.LEFT, true);
                break;
            case R.id.action_think:
                Toast.makeText(this, "如果小主有好的建议或者问题，可以1070138445@qq.com!!!", Toast.LENGTH_SHORT).show();
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
