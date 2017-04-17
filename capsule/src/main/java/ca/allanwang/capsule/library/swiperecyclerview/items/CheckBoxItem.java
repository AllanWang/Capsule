package ca.allanwang.capsule.library.swiperecyclerview.items;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.fastadapter.listeners.ClickEventHook;
import com.mikepenz.materialize.holder.StringHolder;

import java.util.List;

import ca.allanwang.capsule.library.R;

/**
 * Created by Allan Wang on 2017-02-24.
 */

public class CheckBoxItem extends AbstractItem<CheckBoxItem, CheckBoxItem.ViewHolder> {

    public String header;
    public StringHolder name;
    public StringHolder description;

    public CheckBoxItem withHeader(String header) {
        this.header = header;
        return this;
    }

    public CheckBoxItem withName(String Name) {
        this.name = new StringHolder(Name);
        return this;
    }

    public CheckBoxItem withName(@StringRes int NameRes) {
        this.name = new StringHolder(NameRes);
        return this;
    }

    public CheckBoxItem withDescription(String description) {
        this.description = new StringHolder(description);
        return this;
    }

    public CheckBoxItem withDescription(@StringRes int descriptionRes) {
        this.description = new StringHolder(descriptionRes);
        return this;
    }

    @Override
    public int getType() {
        return R.id.capsule_srv_fastadapter_checkbox_item_id;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.capsule_srv_fastitem_checkbox;
    }

    @Override
    public void bindView(ViewHolder viewHolder, List<Object> payloads) {
        super.bindView(viewHolder, payloads);
        viewHolder.checkBox.setChecked(isSelected());
        StringHolder.applyTo(name, viewHolder.name);
        StringHolder.applyToOrHide(description, viewHolder.description);
    }

    @Override
    public void unbindView(ViewHolder holder) {
        super.unbindView(holder);
        holder.name.setText(null);
        holder.description.setText(null);
    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    /**
     * our ViewHolder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected View view;
        public CheckBox checkBox;
        TextView name;
        TextView description;

        public ViewHolder(View view) {
            super(view);
            checkBox = (CheckBox) view.findViewById(R.id.checkbox);
            name = (TextView) view.findViewById(R.id.title);
            description = (TextView) view.findViewById(R.id.description);
            this.view = view;
        }
    }

    public static class CheckBoxClickEvent extends ClickEventHook<CheckBoxItem> {
        @Override
        public View onBind(@NonNull RecyclerView.ViewHolder viewHolder) {
            if (viewHolder instanceof CheckBoxItem.ViewHolder) {
                return ((CheckBoxItem.ViewHolder) viewHolder).checkBox;
            }
            return null;
        }

        @Override
        public void onClick(View v, int position, FastAdapter<CheckBoxItem> fastAdapter, CheckBoxItem item) {
            fastAdapter.toggleSelection(position);
        }
    }

}
