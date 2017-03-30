package ca.allanwang.capsule.sample.fragments;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ca.allanwang.capsule.library.event.CFabEvent;
import ca.allanwang.capsule.library.fragments.CapsuleSRVFragment;
import ca.allanwang.capsule.sample.R;
import ca.allanwang.swiperecyclerview.library.SwipeRecyclerView;
import ca.allanwang.swiperecyclerview.library.adapters.AnimationAdapter;
import ca.allanwang.swiperecyclerview.library.interfaces.ISwipeRecycler;
import ca.allanwang.swiperecyclerview.library.items.CheckBoxItem;

/**
 * Created by Allan Wang on 2016-12-21.
 */

public class SampleSwipeRecyclerFragment extends CapsuleSRVFragment<CheckBoxItem> {
    private static final String[] ALPHABET = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    @Nullable
    @Override
    protected CFabEvent updateFab() {
        return new CFabEvent(false);
    }

    @Override
    protected void configAdapter(AnimationAdapter<CheckBoxItem> adapter) {
        adapter.withOnPreClickListener(new FastAdapter.OnClickListener<CheckBoxItem>() {
            @Override
            public boolean onClick(View v, IAdapter<CheckBoxItem> adapter, CheckBoxItem item, int position) {
                // consume otherwise radio/checkbox will be deselected
                return true;
            }
        });
        adapter.withItemEvent(new CheckBoxItem.CheckBoxClickEvent());

        adapter.add(generateList());
    }

    @Override
    protected void configSRV(SwipeRecyclerView srv) {

    }

    private List<CheckBoxItem> generateList() {
        int x = 0;
        List<CheckBoxItem> items = new ArrayList<>();
        for (String s : ALPHABET) {
            int count = new Random().nextInt(32);
            for (int i = 1; i <= count; i++, x++)
                items.add(new CheckBoxItem().withName(s + " Test " + x).withIdentifier(100 + x));
        }
        return items;
    }

    @Override
    public int getTitleId() {
        return R.string.home;
    }

    @Override
    public void onRefresh(final ISwipeRecycler.OnRefreshStatus statusEmitter) {
        mAdapter.clear();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.add(generateList());
                statusEmitter.onSuccess();
            }
        }, 3000);
    }
}
