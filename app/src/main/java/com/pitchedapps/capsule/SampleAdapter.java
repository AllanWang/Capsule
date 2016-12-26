package com.pitchedapps.capsule;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.pitchedapps.capsule.library.adapters.CapsuleAdapter;
import com.pitchedapps.capsule.library.item.CapsuleViewHolder;

import java.util.List;

/**
 * Created by Allan Wang on 2016-12-21.
 */

public class SampleAdapter extends CapsuleAdapter<String, SampleAdapter.SampleViewHolder> {

    public SampleAdapter(List<String> data) {
        super(data);
    }

    @Override
    protected int getLayoutRes(int position) {
        return R.layout.textview;
    }

    @NonNull
    @Override
    protected SampleViewHolder inflateViewHolder(View view, @LayoutRes int layoutId) {
        return new SampleViewHolder(view, layoutId);
    }

    @Override
    public void onBindViewHolder(SampleViewHolder holder, int position) {
        holder.textView.setText(getItem(position));
    }

    public static class SampleViewHolder extends CapsuleViewHolder {

        TextView textView;

        public SampleViewHolder(View itemView, int layoutId) {
            super(itemView, layoutId);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
