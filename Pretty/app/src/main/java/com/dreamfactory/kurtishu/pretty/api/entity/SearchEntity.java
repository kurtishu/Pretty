package com.dreamfactory.kurtishu.pretty.api.entity;

/**
 * Created by kurtishu on 11/30/15.
 */
public class SearchEntity {

    public int id = -1; //分类ID，默认返回的是全部。这里的ID就是指分类的ID

    public int page = 1; //请求页数，默认page=1

    public int rows = 20; //每页返回的条数，默认rows=20

    public int classify; // 获取最新数据用 分类ID，取得该分类下的最新数据


    private static SearchEntity searchEntity = new SearchEntity();

    private SearchEntity() {

    }

    public static SearchEntity getInstance() {
        return searchEntity;
    }
}
