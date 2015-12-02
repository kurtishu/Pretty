package com.dreamfactory.kurtishu.pretty.view.delegate;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dreamfactory.kurtishu.pretty.R;
import com.dreamfactory.kurtishu.pretty.model.Picture;
import com.dreamfactory.kurtishu.pretty.view.adapter.ImageDetailAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Created by kurtishu on 12/1/15.
 */
public class ImageDetailDelegate extends BaseAppDelegate implements View.OnClickListener {

    private RecyclerViewPager mRecyclerViewPager;
    private SimpleDraweeView bgImageView;
    private ImageDetailAdapter imageDetailAdapter;
    private Button fab;
    private TextView tvCount;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_image_detail;
    }

    public void initViews(Context context, String url) {

        fab = get(R.id.fab);
        fab.setOnClickListener(this);
        bgImageView = get(R.id.pretty_image_view);
        tvCount = get(R.id.count);
        bgImageView.setImageURI(Uri.parse(url));

        mRecyclerViewPager = get(R.id.image_detail_list);
        LinearLayoutManager layout = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewPager.setLayoutManager(layout);
        imageDetailAdapter = new ImageDetailAdapter(context, mRecyclerViewPager);
        mRecyclerViewPager.setAdapter(imageDetailAdapter);
        mRecyclerViewPager.setHasFixedSize(true);
        mRecyclerViewPager.setLongClickable(true);

        updateState(RecyclerView.SCROLL_STATE_IDLE);

        mRecyclerViewPager.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
                updateState(scrollState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                int childCount = mRecyclerViewPager.getChildCount();
                int width = mRecyclerViewPager.getChildAt(0).getWidth();
                int padding = (mRecyclerViewPager.getWidth() - width) / 2;

                for (int j = 0; j < childCount; j++) {
                    View v = recyclerView.getChildAt(j);
                    //往左 从 padding 到 -(v.getWidth()-padding) 的过程中，由大到小
                    float rate = 0;
                    ;
                    if (v.getLeft() <= padding) {
                        if (v.getLeft() >= padding - v.getWidth()) {
                            rate = (padding - v.getLeft()) * 1f / v.getWidth();
                        } else {
                            rate = 1;
                        }
                        v.setScaleY(1 - rate * 0.1f);
                        v.setScaleX(1 - rate * 0.1f);

                    } else {
                        //往右 从 padding 到 recyclerView.getWidth()-padding 的过程中，由大到小
                        if (v.getLeft() <= recyclerView.getWidth() - padding) {
                            rate = (recyclerView.getWidth() - padding - v.getLeft()) * 1f / v.getWidth();
                        }
                        v.setScaleY(0.9f + rate * 0.1f);
                        v.setScaleX(0.9f + rate * 0.1f);
                    }
                }
            }
        });
        mRecyclerViewPager.addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
            @Override
            public void OnPageChanged(int oldPosition, int newPosition) {
                Logger.d("oldPosition:" + oldPosition + " newPosition:" + newPosition);
                if (null != tvCount) {
                    int totalCount = imageDetailAdapter.getItemCount();
                    tvCount.setText(String.format("%d/%d", newPosition + 1, totalCount));
                }
            }
        });

        mRecyclerViewPager.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (mRecyclerViewPager.getChildCount() < 3) {
                    if (mRecyclerViewPager.getChildAt(1) != null) {
                        if (mRecyclerViewPager.getCurrentPosition() == 0) {
                            View v1 = mRecyclerViewPager.getChildAt(1);
                            v1.setScaleY(0.9f);
                            v1.setScaleX(0.9f);
                        } else {
                            View v1 = mRecyclerViewPager.getChildAt(0);
                            v1.setScaleY(0.9f);
                            v1.setScaleX(0.9f);
                        }
                    }
                } else {
                    if (mRecyclerViewPager.getChildAt(0) != null) {
                        View v0 = mRecyclerViewPager.getChildAt(0);
                        v0.setScaleY(0.9f);
                        v0.setScaleX(0.9f);
                    }
                    if (mRecyclerViewPager.getChildAt(2) != null) {
                        View v2 = mRecyclerViewPager.getChildAt(2);
                        v2.setScaleY(0.9f);
                        v2.setScaleX(0.9f);
                    }
                }

            }
        });
    }


    private void updateState(int scrollState) {
        String stateName = "Undefined";
        switch (scrollState) {
            case RecyclerView.SCROLL_STATE_IDLE:
                stateName = "Idle";
                break;

            case RecyclerView.SCROLL_STATE_DRAGGING:
                stateName = "Dragging";
                break;

            case RecyclerView.SCROLL_STATE_SETTLING:
                stateName = "Flinging";
                break;
        }
    }

    public void updateData(List<Picture> list) {
        imageDetailAdapter.setData(list);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.fab:
                if (mRecyclerViewPager.getVisibility() == View.VISIBLE) {
                    mRecyclerViewPager.setVisibility(View.GONE);
                } else {
                    mRecyclerViewPager.setVisibility(View.VISIBLE);
                }

                int pos = mRecyclerViewPager.getCurrentPosition();
                bgImageView.setImageURI(Uri.parse(imageDetailAdapter.getCurrentImage(pos)));
                break;
        }
    }

}
