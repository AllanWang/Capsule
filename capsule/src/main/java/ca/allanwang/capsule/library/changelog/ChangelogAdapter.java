package ca.allanwang.capsule.library.changelog;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ca.allanwang.capsule.library.R;

/**
 * @author Allan Wang
 */
class ChangelogAdapter extends RecyclerView.Adapter<ChangelogAdapter.ChangelogVH> {

    private final List<ChangelogXmlParser.ChangelogItem> mItems;

    ChangelogAdapter(List<ChangelogXmlParser.ChangelogItem> items) {
        mItems = items;
    }

    @Override
    public ChangelogVH onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(getLayout(viewType), parent, false);
        return new ChangelogVH(view);
    }

    private int getLayout(int position) {
        if (mItems.get(position).isTitle()) return R.layout.capsule_changelog_title;
        return R.layout.capsule_changelog_content;
    }

    @Override
    public void onBindViewHolder(ChangelogVH holder, int position) {
        holder.text.setText(mItems.get(position).getText());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mItems != null ? mItems.size() : 0;
    }

    static class ChangelogVH extends RecyclerView.ViewHolder {

        final TextView text;

        ChangelogVH(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.changelog_text);
        }
    }
}