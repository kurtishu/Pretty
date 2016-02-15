package com.dreamfactory.kurtishu.pretty.view.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dreamfactory.kurtishu.pretty.R;
import com.dreamfactory.kurtishu.pretty.event.NavigatorEvent;
import com.dreamfactory.kurtishu.pretty.model.Gallery;
import com.dreamfactory.kurtishu.pretty.utils.UniqueListArray;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;

/**
 * Created by kurtishu on 11/30/15.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private UniqueListArray<Gallery> mValues;

    public MyItemRecyclerViewAdapter() {
        mValues = new UniqueListArray<Gallery>();
    }

    public MyItemRecyclerViewAdapter(List<Gallery> items) {
        mValues = new UniqueListArray<Gallery>(items);
    }

    public void setData(UniqueListArray<Gallery> datas) {
        mValues = datas;
    }

    public void addDatas(List<Gallery> datas) {
        mValues.addAllWithSort(datas);
    }

    public void clearData() {
        mValues.clear();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder mHolder = (ViewHolder) holder;
        mHolder.mTitleView.setText(mValues.get(position).title);
        mHolder.mVCountView.setText(mValues.get(position).count + "");
        mHolder.mFCountView.setText(mValues.get(position).fcount + "");
        mHolder.mRCountView.setText(mValues.get(position).rcount + "");
        Uri uri = Uri.parse(mValues.get(position).getImg());
        mHolder.draweeView.setImageURI(uri);
        mHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map params = new HashMap();
                params.put("id", (Integer)mValues.get(position).id);
                params.put("img", mValues.get(position).getImg());
                params.put("title", mValues.get(position).title);
                EventBus.getDefault().postSticky(new NavigatorEvent(params, false));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mVCountView;
        public final TextView mRCountView;
        public final TextView mFCountView;
        public final TextView mTitleView;
        public SimpleDraweeView draweeView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mVCountView = (TextView) view.findViewById(R.id.v_count);
            mRCountView = (TextView) view.findViewById(R.id.r_count);
            mFCountView = (TextView) view.findViewById(R.id.like_count);
            mTitleView = (TextView) view.findViewById(R.id.title);
            draweeView = (SimpleDraweeView)view.findViewById(R.id.pretty_image_view);
            draweeView.setAspectRatio(1.33f);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitleView.getText() + "'";
        }
    }
}