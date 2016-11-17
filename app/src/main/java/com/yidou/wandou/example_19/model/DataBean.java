package com.yidou.wandou.example_19.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2016/11/17.
 */

@Entity
public class DataBean implements Serializable
{
    @Id(autoincrement = true)
    private Long id;
    @Property
    private String title;
    @Property
    private String date;
    @Property
    private String author_name;
    @Property
    private String thumbnail_pic_s;
    private String thumbnail_pic_s02;
    private String thumbnail_pic_s03;
    @Property
    private String url;
    private String uniquekey;
    private String type;
    private String realtype;

    @Generated(hash = 338265738)
    public DataBean(Long id, String title, String date, String author_name,
            String thumbnail_pic_s, String thumbnail_pic_s02,
            String thumbnail_pic_s03, String url, String uniquekey, String type,
            String realtype) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.author_name = author_name;
        this.thumbnail_pic_s = thumbnail_pic_s;
        this.thumbnail_pic_s02 = thumbnail_pic_s02;
        this.thumbnail_pic_s03 = thumbnail_pic_s03;
        this.url = url;
        this.uniquekey = uniquekey;
        this.type = type;
        this.realtype = realtype;
    }

    @Generated(hash = 908697775)
    public DataBean() {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getAuthor_name()
    {
        return author_name;
    }

    public void setAuthor_name(String author_name)
    {
        this.author_name = author_name;
    }

    public String getThumbnail_pic_s()
    {
        return thumbnail_pic_s;
    }

    public void setThumbnail_pic_s(String thumbnail_pic_s)
    {
        this.thumbnail_pic_s = thumbnail_pic_s;
    }

    public String getThumbnail_pic_s02()
    {
        return thumbnail_pic_s02;
    }

    public void setThumbnail_pic_s02(String thumbnail_pic_s02)
    {
        this.thumbnail_pic_s02 = thumbnail_pic_s02;
    }

    public String getThumbnail_pic_s03()
    {
        return thumbnail_pic_s03;
    }

    public void setThumbnail_pic_s03(String thumbnail_pic_s03)
    {
        this.thumbnail_pic_s03 = thumbnail_pic_s03;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUniquekey()
    {
        return uniquekey;
    }

    public void setUniquekey(String uniquekey)
    {
        this.uniquekey = uniquekey;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getRealtype()
    {
        return realtype;
    }

    public void setRealtype(String realtype)
    {
        this.realtype = realtype;
    }
}
