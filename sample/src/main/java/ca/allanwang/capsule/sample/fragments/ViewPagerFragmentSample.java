package ca.allanwang.capsule.sample.fragments;

import java.util.Arrays;
import java.util.List;

import ca.allanwang.capsule.library.fragments.ViewPagerFragment;
import ca.allanwang.capsule.library.interfaces.CPage;
import ca.allanwang.capsule.library.item.PageItem;
import ca.allanwang.capsule.sample.R;

/**
 * Created by Allan Wang on 2016-10-30.
 *
 * Setting up a ViewPagerFragment is this easy
 * Simply return the list of fragments held in the tabs!
 */

public class ViewPagerFragmentSample extends ViewPagerFragment {

    @Override
    protected List<CPage> setPages() {
        return Arrays.asList(new CPage[]{
                new PageItem(new FragmentPageSample(), R.string.account),
                new PageItem(new FragmentPageSampleNoFab(), R.string.settings)
        });
    }

    @Override
    protected int getOffscreenPageLimit(int pageCount) {
        return 5;
    }

    @Override
    public int getTitleId() {
        return R.string.viewpasger;
    }
}
