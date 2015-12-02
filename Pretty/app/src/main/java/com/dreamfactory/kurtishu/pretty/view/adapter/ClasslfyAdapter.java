package com.dreamfactory.kurtishu.pretty.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dreamfactory.kurtishu.pretty.R;
import com.dreamfactory.kurtishu.pretty.api.entity.SearchEntity;
import com.dreamfactory.kurtishu.pretty.db.entity.ClasslfyEntity;
import com.dreamfactory.kurtishu.pretty.event.UpdateImageListEvent;
import com.dreamfactory.kurtishu.pretty.utils.ViewHolder;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by kurtishu on 12/1/15.
 */
public class ClasslfyAdapter extends BaseAdapter {

    private List<ClasslfyEntity> mDatas;
    private LayoutInflater mInflater;

    public ClasslfyAdapter(Context context, List<ClasslfyEntity> mDatas) {
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public ClasslfyEntity getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.classlfy_item, parent, false);
        }

        TextView content = ViewHolder.get(convertView, R.id.content);
        content.setText(getItem(position).getTitle());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchEntity searchEntity = SearchEntity.getInstance();
                searchEntity.id = getItem(position).getId();
                searchEntity.page = 1;
                searchEntity.rows = 20;
                EventBus.getDefault().postSticky(new UpdateImageListEvent(true, searchEntity));
            }
        });
        return convertView;
    }
}
