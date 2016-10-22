package com.pitchedapps.capsule.library.changelog;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pitchedapps.capsule.library.R;

import java.util.List;

/**
 * @author Allan Wang
 */
class ChangelogAdapter extends RecyclerView.Adapter<ChangelogAdapter.ChangelogVH> {

    private final List<ChangelogXmlParser.ChangelogItem> mItems;

    ChangelogAdapter(List<ChangelogXmlParser.ChangelogItem> items) {
        mItems = items;
    }

    @Override
    public ChangelogVH onCreateViewHolder (ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.changelog_content, parent, false);
        return new ChangelogVH(view);
    }

    @Override
    public void onBindViewHolder (ChangelogVH holder, int position) {
        ChangelogXmlParser.ChangelogItem item = mItems.get(position);

        String contentStr = "";
        List<String> points = item.getItems();
        for (int i = 0; i < points.size(); i++) {
            if (i > 0) {
                // No need for new line on the first item
                contentStr += "\n";
            }
            contentStr += "\u2022 ";
            contentStr += points.get(i);
        }
        holder.title.setText(item.getTitle());
        holder.content.setText(contentStr);
    }

    @Override
    public long getItemId (int position) {
        return position;
    }

    @Override
    public int getItemCount () {
        return mItems != null ? mItems.size() : 0;
    }

    static class ChangelogVH extends RecyclerView.ViewHolder {

        final TextView title, content;

        ChangelogVH (View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.changelog_title);
            content = (TextView) itemView.findViewById(R.id.changelog_content);
        }
    }
}