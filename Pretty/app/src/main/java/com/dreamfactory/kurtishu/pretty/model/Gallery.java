package com.dreamfactory.kurtishu.pretty.model;

import com.dreamfactory.kurtishu.pretty.config.Config;

/**
 * Created by kurtishu on 11/30/15.
 */
public class Gallery {

    public int id;
    public int  galleryclass ;//          图片分类
    public String title     ;//          标题
    public String img     ;//          图库封面
    public int count     ;//          访问数
    public int rcount     ;//           回复数
    public int fcount     ;//          收藏数
    public int size     ;//      图片多少张

    public String getImg() {
        return Config.ISUSINGMOCKDATA ? img : Config.URL.IMAGE_HOST + img;
    }
}
