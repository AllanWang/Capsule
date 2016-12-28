package com.pitchedapps.capsule;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.pitchedapps.capsule.library.adapters.CapsuleAdapter;

import java.util.List;

/**
 * Created by Allan Wang on 2016-12-21.
 */

public class SampleAdapter extends CapsuleAdapter<String, SampleAdapter.CapsuleViewHolder> {

    public SampleAdapter(List<String> data) {
        super(data);
    }

    @Override
    protected int getLayoutRes(int position) {
        return R.layout.textview;
    }

    @NonNull
    @Override
    protected CapsuleViewHolder inflateViewHolder(View view, @LayoutRes int layoutId) {
        return new CapsuleViewHolder(view, layoutId);
    }

    @Override
    public void onBindViewHolder(CapsuleViewHolder holder, int position) {
        holder.textView.setText(getItem(position));
    }

    public static class CapsuleViewHolder extends com.pitchedapps.capsule.library.item.CapsuleViewHolder {

        TextView textView;

        public CapsuleViewHolder(View itemView, int layoutId) {
            super(itemView, layoutId);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
