/*
 *
 *  * Copyright 2015 Kurtis <kurtis_hu@hotmail.com>
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
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dreamfactory.kurtishu.pretty.BuildConfig;
import com.dreamfactory.kurtishu.pretty.R;
import com.dreamfactory.kurtishu.pretty.event.NavigatorEvent;

import org.apache.commons.collections4.map.ListOrderedMap;

import de.greenrobot.event.EventBus;

/**
 * Created by kurtishu on 12/3/15.
 */
public class AboutDelegate extends BaseAppDelegate implements Toolbar.OnClickListener {

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initViews(Context context, Intent mIntent) {
        super.initViews(context, mIntent);
        Toolbar toolbar = get(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back);
        toolbar.setNavigationOnClickListener(this);

        TextView textViewVersion = get(R.id.version);
        textViewVersion.setText(context.getString(R.string.version, BuildConfig.VERSION_NAME, BuildConfig.FLAVOR));


        RecyclerView mRecyclerView = get(R.id.dp_libraries);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        ListOrderedMap<String, String> dataMap = new ListOrderedMap<String, String>();
        dataMap.put("项目GitHub地址", "https://github.com/KurtisHu/Pretty");
        dataMap.put("  ", " 本项目所使用的依赖库");
        dataMap.put("square / okhttp", "https://square.github.io/okhttp/");
        dataMap.put("google / gson", "https://github.com/google/gson");
        dataMap.put("facebook / fresco", "https://github.com/facebook/fresco");
        dataMap.put("orhanobut / logger", "https://github.com/orhanobut/logger");
        dataMap.put("greenrobot / EventBus", "https://github.com/greenrobot/EventBus");
        dataMap.put("lsjwzh / RecyclerViewPager", "https://github.com/lsjwzh/RecyclerViewPager");
        dataMap.put("Syehunter / RecyclerViewManager", "https://github.com/Syehunter/RecyclerViewManager");
        dataMap.put("Apache / commons-collections" , "http://commons.apache.org/proper/commons-collections/");
        dataMap.put("Realm", "https://realm.io");

        mRecyclerView.setAdapter(new LibraryAdapter(context, dataMap));
    }

    @Override
    public void onClick(View v) {
        EventBus.getDefault().post(new NavigatorEvent(null));
    }

    class LibraryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


        private ListOrderedMap<String, String> dataMap = new ListOrderedMap<String, String>();
        private LayoutInflater mInflater;

        public LibraryAdapter(Context context, ListOrderedMap<String, String> dataMap) {
            this.dataMap = dataMap;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           return viewType == 0 ? new HeaderViewHolder(mInflater.inflate(R.layout.item_header, parent, false)) :
                   new ItemViewHolder(mInflater.inflate(R.layout.item_library, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            String key = dataMap.get(position / 2);
            if (holder.getItemViewType() == 0) {
                ((HeaderViewHolder)holder).tvHeader.setText(key);
            } else {
                ((ItemViewHolder)holder).tvItem.setText(dataMap.get(key));
            }
        }

        @Override
        public int getItemCount() {
            return dataMap.size() * 2;
        }

        @Override
        public int getItemViewType(int position) {
            // 0 : header
            // 1 : item
            return position % 2 == 0 ? 0 : 1;
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        private TextView tvHeader;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            tvHeader = (TextView)itemView;
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView tvItem;
        public ItemViewHolder(View itemView) {
            super(itemView);
            tvItem = (TextView)itemView.findViewById(R.id.name);
        }
    }
}
