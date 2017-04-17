package ca.allanwang.capsule.sample.fragments;

import android.os.Handler;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ca.allanwang.capsule.library.event.CFabEvent;
import ca.allanwang.capsule.library.fragments.CapsuleSRVFragment;
import ca.allanwang.capsule.sample.R;
import ca.allanwang.capsule.library.swiperecyclerview.SwipeRecyclerView;
import ca.allanwang.capsule.library.swiperecyclerview.adapters.AnimationAdapter;
import ca.allanwang.capsule.library.swiperecyclerview.interfaces.ISwipeRecycler;
import ca.allanwang.capsule.library.swiperecyclerview.items.CheckBoxItem;

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
        adapter.withOnPreClickListener((v, adapter1, item, position) -> {
            // consume otherwise radio/checkbox will be deselected
            return true;
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
        new Handler().postDelayed(() -> {
            mAdapter.add(generateList());
            statusEmitter.onSuccess();
        }, 1000);
    }
}
