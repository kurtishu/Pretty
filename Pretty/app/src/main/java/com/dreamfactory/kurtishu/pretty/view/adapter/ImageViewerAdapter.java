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

package com.dreamfactory.kurtishu.pretty.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dreamfactory.kurtishu.pretty.R;
import com.dreamfactory.kurtishu.pretty.model.Picture;
import com.dreamfactory.kurtishu.pretty.widget.PhotoImageView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：kurtishu on 2/15/16
 * Eevery one should have a dream, what if one day it comes true!
 */
public class ImageViewerAdapter extends PagerAdapter {

    private LayoutInflater mInflater;
    private SparseArray<View> views = new SparseArray<View>();
    private List<Picture> mItems;

    public ImageViewerAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mItems = new ArrayList<Picture>();
    }


    public void setData(List<Picture> mItems) {
        this.mItems.addAll(mItems);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View contentView = views.get(position);
        if (null == contentView) {
            contentView = mInflater.inflate(R.layout.image_detail_item, container, false);
            views.put(position, contentView);
        }

        PhotoImageView simpleDraweeView = (PhotoImageView) contentView.findViewById(R.id.detail_image_view);
        Picture picture = mItems.get(position);
        Uri uri = Uri.parse(picture.getImg());
        simpleDraweeView.setImageURI(uri);

        container.addView(contentView);
        return contentView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View contentView = views.get(position);
        container.removeView(contentView);
    }
}
