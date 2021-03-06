package com.dreamfactory.kurtishu.pretty.model;

import com.dreamfactory.kurtishu.pretty.config.Config;

/**
 * Created by kurtishu on 11/30/15.
 */
public class Picture {

    public int id;
    public int gallery; //图片库
    public String src; //图片地址

    public String getImg() {
        return Config.ISUSINGMOCKDATA ? src : Config.URL.IMAGE_HOST + src;
    }
}
