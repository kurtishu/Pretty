package com.dreamfactory.kurtishu.pretty.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dreamfactory.kurtishu.pretty.R;
import com.dreamfactory.kurtishu.pretty.model.Gallery;
import com.dreamfactory.kurtishu.pretty.model.Picture;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kurtishu on 12/1/15.
 */
public class ImageDetailAdapter extends RecyclerView.Adapter<ImageDetailAdapter.SimpleViewHolder> {
    private static final int COUNT = 100;

    private final Context mContext;
    private final RecyclerView mRecyclerView;
    private  List<Picture> mItems;

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;
        public final SimpleDraweeView simpleDraweeView;

        public SimpleViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.content);
            simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.detail_image_view);
        }
    }

    public ImageDetailAdapter(Context context, RecyclerView recyclerView) {
        mContext = context;
        mItems = new ArrayList<Picture>();
        mRecyclerView = recyclerView;
    }

    public void setData(List<Picture> mItems) {
        this.mItems.addAll(mItems);
        notifyDataSetChanged();
    }

    public void addItem(int position) {
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.image_detail_item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder mHolder, int position) {
        Picture picture = mItems.get(position);
        mHolder.title.setText(picture.id + "");
        Uri uri = Uri.parse(picture.getImg());
        mHolder.simpleDraweeView.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public String getCurrentImage(int position) {
        return mItems.get(position).getImg();
    }

}
