package com.yidou.wandou.example_19.present;

import android.widget.Toast;

import com.yidou.wandou.example_19.dao.DataBeanDao;
import com.yidou.wandou.example_19.dao.DbCore;
import com.yidou.wandou.example_19.model.DataBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/17.
 */

public class DBUtils
{
    DBOperate mDBOperate;
    DbCore mCore;
    DataBeanDao mDao;

    public DBUtils(DBOperate DBOperate, DbCore core)
    {
        mDBOperate = DBOperate;
        mCore = core;
        mDao = core.getDaoSession().getDataBeanDao();
    }

    public void insertDB(DataBean item)//查询数据并插入
    {
        boolean isHave = false;
        List<DataBean> been = mDao.loadAll();
        for (int i = 0; i < been.size(); i++)
        {
            if (item.getUrl().equals(been.get(i).getUrl()))
            {
                isHave = true;
                break;
            }
        }
        if (isHave)
        {
            mDBOperate.notifyFail();//您已经添加过磁条数据
        }else
        {
            mDao.insert(item);
            mDBOperate.notifySucess();
        }
    }
}
