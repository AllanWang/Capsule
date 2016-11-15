package com.pitchedapps.capsule;

import com.pitchedapps.capsule.library.fragments.ViewPagerFragment;
import com.pitchedapps.capsule.library.interfaces.CPage;
import com.pitchedapps.capsule.library.item.PageItem;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Allan Wang on 2016-10-30.
 */

public class ViewPagerFragmentSample extends ViewPagerFragment {

    @Override
    protected List<CPage> setPages() {
        return Arrays.asList(new CPage[]{
                new PageItem(new FragmentPageSample(), R.string.account),
                new PageItem(new FragmentPageSampleNoFab(), R.string.settings)
        });
    }
}
