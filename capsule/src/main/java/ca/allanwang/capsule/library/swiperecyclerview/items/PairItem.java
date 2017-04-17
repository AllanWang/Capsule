package ca.allanwang.capsule.library.swiperecyclerview.items;

import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

import ca.allanwang.capsule.library.R;

/**
 * Created by Allan Wang on 19/03/2017.
 */

public class PairItem extends AbstractItem<PairItem, PairItem.ViewHolder> {

    private String left, right;

    public PairItem(String left, String right) {
        this.left = left;
        this.right = right;
    }

    public int getType() {
        return R.id.capsule_srv_fastadapter_pair_item_id;
    }

    public int getLayoutRes() {
        return R.layout.capsule_srv_fastitem_pair;
    }

    @Override
    public void bindView(PairItem.ViewHolder viewHolder, List<Object> payloads) {
        super.bindView(viewHolder, payloads);
        viewHolder.left.setText(left);
        viewHolder.right.setText(right);
        if (viewHolder.getAdapterPosition() % 2 == 0)
            viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(viewHolder.itemView.getContext(), getShader()));
    }

    protected
    @ColorRes
    int getShader() {
        return R.color.capsule_srv_transparent_black;
    }

    @Override
    public void unbindView(PairItem.ViewHolder holder) {
        super.unbindView(holder);
        holder.left.setText(null);
        holder.right.setText(null);
        holder.itemView.setBackgroundColor(0x00000000);
    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView left, right;

        public ViewHolder(View view) {
            super(view);
            left = (TextView) view.findViewById(R.id.text_left);
            right = (TextView) view.findViewById(R.id.text_right);
        }
    }
}
