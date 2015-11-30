package com.dreamfactory.kurtishu.pretty.widget;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dreamfactory.kurtishu.pretty.R;
import com.dreamfactory.kurtishu.pretty.model.Gallery;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.net.URI;
import java.util.List;

/**
 * Created by kurtishu on 11/30/15.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Gallery> mValues;

    public MyItemRecyclerViewAdapter(List<Gallery> items) {
        mValues = items;

    }

    public void setData(List<Gallery> datas) {
        mValues = datas;
    }

    public void addDatas(List<Gallery> datas) {
        mValues.addAll(datas);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder mHolder = (ViewHolder) holder;
        mHolder.mItem = mValues.get(position);
        mHolder.mContentView.setText(mValues.get(position).title);
        mHolder.mIdView.setText(mValues.get(position).id + "");
        Uri uri = Uri.parse(mHolder.mItem.getImg());
        mHolder.draweeView.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Gallery mItem;
        public SimpleDraweeView draweeView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            draweeView = (SimpleDraweeView)view.findViewById(R.id.pretty_image_view);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}