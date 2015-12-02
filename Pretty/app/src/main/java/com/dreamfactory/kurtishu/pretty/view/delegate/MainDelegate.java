package com.dreamfactory.kurtishu.pretty.view.delegate;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;

import com.dreamfactory.kurtishu.pretty.R;
import com.dreamfactory.kurtishu.pretty.api.entity.ImageList;
import com.dreamfactory.kurtishu.pretty.api.entity.SearchEntity;
import com.dreamfactory.kurtishu.pretty.db.DBManager;
import com.dreamfactory.kurtishu.pretty.event.UpdateImageListEvent;
import com.dreamfactory.kurtishu.pretty.view.adapter.ClasslfyAdapter;
import com.dreamfactory.kurtishu.pretty.widget.LoadMoreRecyclerView;
import com.dreamfactory.kurtishu.pretty.view.adapter.MyItemRecyclerViewAdapter;
import com.orhanobut.logger.Logger;

import de.greenrobot.event.EventBus;

/**
 * Created by kurtishu on 11/30/15.
 */
public class MainDelegate extends BaseAppDelegate implements View.OnClickListener {

    private LoadMoreRecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;
    private MyItemRecyclerViewAdapter mAdapter;
    private DrawerLayout mDrawerLayout;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }

    public void initViews(Context context) {

        initClasslfyListView(context);

        Toolbar toolbar = get(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);

        mDrawerLayout = get(R.id.drawerlayout);
        FloatingActionButton fab = get(R.id.fab);
        fab.setOnClickListener(this);

        mRefreshLayout = get(R.id.refresh_layout);
        mRecyclerView = get(R.id.recyclerview_list);

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Logger.i("setOnRefreshListener");
                //更新第一页数据
                mRefreshLayout.setRefreshing(false);
                SearchEntity.getInstance().page = 1;
                EventBus.getDefault().postSticky(new UpdateImageListEvent(false, SearchEntity.getInstance()));

            }
        });

        // new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        mRecyclerView.setAutoLoadMoreEnable(true);

        mRecyclerView.setLoadMoreListener(new LoadMoreRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                //加载下一页数据
                Logger.i("onLoadMore");
                mRefreshLayout.setRefreshing(false);
                SearchEntity.getInstance().page += 1;
                EventBus.getDefault().postSticky(new UpdateImageListEvent(false, SearchEntity.getInstance()));
            }
        });
    }


    private void initClasslfyListView(Context context) {
        ListView classList = get(R.id.listview);
        ClasslfyAdapter adapter = new ClasslfyAdapter(context, DBManager.getClasslfyList());
        classList.setAdapter(adapter);
    }

    public void setRecyclerViewAdapter(ImageList list) {
        if (null == list || null == list.tngou) {
            return;
        }
        if (null == mAdapter) {
            mAdapter = new MyItemRecyclerViewAdapter(list.tngou);
            mRecyclerView.setAdapter(mAdapter);
        }
        mAdapter.addDatas(list.tngou);
        mRecyclerView.notifyMoreFinish((list.total > mAdapter.getItemCount()) ? true : false);
    }

    public void clearRecyclerViewData() {
        if (null != mAdapter) {
            mAdapter.clearData();
        }
        mDrawerLayout.closeDrawer(Gravity.LEFT);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.fab:
                toggleDrawerLayout();
                break;
        }
    }

    private void toggleDrawerLayout() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
    }
}
