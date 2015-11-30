package com.dreamfactory.kurtishu.pretty.view.delegate;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.dreamfactory.kurtishu.pretty.R;
import com.dreamfactory.kurtishu.pretty.api.entity.ImageList;
import com.dreamfactory.kurtishu.pretty.widget.LoadMoreRecyclerView;
import com.dreamfactory.kurtishu.pretty.widget.MyItemRecyclerViewAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by kurtishu on 11/30/15.
 */
public class MainDelegate extends BaseAppDelegate{

    private LoadMoreRecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;
    private MyItemRecyclerViewAdapter mAdapter;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }

    public void initViews(Context context, ImageList list) {
        mRefreshLayout = get(R.id.refresh_layout);
        mRecyclerView = get(R.id.recyclerview_list);

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Logger.i("setOnRefreshListener");
                //更新第一页数据
                mRefreshLayout.setRefreshing(false);

            }
        });

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new MyItemRecyclerViewAdapter(list.tngou);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setAutoLoadMoreEnable(true);

        mRecyclerView.setLoadMoreListener(new LoadMoreRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                //加载下一页数据
                Logger.i("onLoadMore");
                mRefreshLayout.setRefreshing(false);

            }
        });
    }

}
