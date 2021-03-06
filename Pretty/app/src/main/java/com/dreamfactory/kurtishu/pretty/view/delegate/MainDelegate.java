package com.dreamfactory.kurtishu.pretty.view.delegate;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.dreamfactory.kurtishu.pretty.R;
import com.dreamfactory.kurtishu.pretty.api.entity.ImageList;
import com.dreamfactory.kurtishu.pretty.api.entity.SearchEntity;
import com.dreamfactory.kurtishu.pretty.event.UpdateImageListEvent;
import com.dreamfactory.kurtishu.pretty.view.adapter.MyItemRecyclerViewAdapter;
import com.orhanobut.logger.Logger;

import de.greenrobot.event.EventBus;
import space.sye.z.library.listener.OnLoadMoreListener;
import space.sye.z.library.manager.RecyclerMode;
import space.sye.z.library.manager.RecyclerViewManager;
import space.sye.z.library.widget.RefreshRecyclerView;

/**
 * Created by kurtishu on 11/30/15.
 */
public class MainDelegate extends BaseAppDelegate implements View.OnClickListener {

    private RefreshRecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;
    private MyItemRecyclerViewAdapter mAdapter;
    private DrawerLayout mDrawerLayout;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews(Context context, Intent mIntent) {

        initClasslfyListView(context);
        Toolbar toolbar = get(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(Color.WHITE);
        ((AppCompatActivity)context).setSupportActionBar(toolbar);

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
                SearchEntity.getInstance().page = 1;
                EventBus.getDefault().postSticky(new UpdateImageListEvent(false, SearchEntity.getInstance()));

            }
        });

        mAdapter = new MyItemRecyclerViewAdapter();
        RecyclerViewManager.with(mAdapter, new LinearLayoutManager(context))
                .setMode(RecyclerMode.BOTTOM)
                .setOnLoadMoreListener(new OnLoadMoreListener() {
                    @Override
                    public void onLoadMore() {
                        //加载下一页数据
                        Logger.i("onLoadMore");
                        mRefreshLayout.setRefreshing(false);
                        SearchEntity.getInstance().page += 1;
                        EventBus.getDefault().postSticky(new UpdateImageListEvent(false, SearchEntity.getInstance()));
                    }
                }).into(mRecyclerView, context);
    }


    private void initClasslfyListView(Context context) {
        //ListView classList = get(R.id.listview);
        //ClasslfyAdapter adapter = new ClasslfyAdapter(context, DBManager.getClasslfyList());
        //classList.setAdapter(adapter);

        NavigationView naView = (NavigationView) get(R.id.navigation_view);
        naView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                int id = 1;
                switch (item.getItemId()) {
                    case R.id.c1:
                        id = 1;
                        break;
                    case R.id.c2:
                        id = 2;
                        break;
                    case R.id.c3:
                        id = 3;
                        break;
                    case R.id.c4:
                        id = 4;
                        break;
                    case R.id.c5:
                        id = 5;
                        break;
                    case R.id.c6:
                        id = 6;
                        break;
                    case R.id.c7:
                        id = 7;
                        break;
                }
                toggleDrawerLayout();
                SearchEntity searchEntity = SearchEntity.getInstance();
                searchEntity.id = id;
                searchEntity.page = 1;
                searchEntity.rows = 20;
                EventBus.getDefault().postSticky(new UpdateImageListEvent(true, searchEntity));
                return true;
            }
        });

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
        mRefreshLayout.setRefreshing(false);
        RecyclerViewManager.onRefreshCompleted();
        RecyclerViewManager.notifyDataSetChanged();
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
