/*
 *
 *  * Copyright 2016 Kurtis <kurtis_hu@hotmail.com>
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.dreamfactory.kurtishu.pretty.view.delegate;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dreamfactory.kurtishu.pretty.R;
import com.dreamfactory.kurtishu.pretty.model.Picture;
import com.dreamfactory.kurtishu.pretty.view.adapter.ImageDetailAdapter;
import com.dreamfactory.kurtishu.pretty.view.adapter.ImageViewerAdapter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;

import java.util.List;

/**
 * Created by kurtishu on 12/1/15.
 */
public class ImageViewerDelegate extends BaseAppDelegate implements View.OnClickListener {

    private ViewPager mRecyclerViewPager;
    private ImageViewerAdapter imageAdapter;
    private Button btnDownload;
    private TextView tvCount;
    private int totalCount;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_image_viewer;
    }

    @Override
    public void initViews(Context context, Intent mIntent) {

        btnDownload = get(R.id.download);
        btnDownload.setOnClickListener(this);
        tvCount = get(R.id.count);

        mRecyclerViewPager = get(R.id.image_detail_list);
        imageAdapter = new ImageViewerAdapter(context);
        mRecyclerViewPager.setAdapter(imageAdapter);
        mRecyclerViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                String count = String.format("%d/%d", position + 1, totalCount);
                tvCount.setText(count);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    public void updateData(List<Picture> list) {
        imageAdapter.setData(list);
        totalCount = list.size();
        String count = String.format("1/%d", totalCount);
        tvCount.setText(count);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.fab:


                break;
        }
    }

}
