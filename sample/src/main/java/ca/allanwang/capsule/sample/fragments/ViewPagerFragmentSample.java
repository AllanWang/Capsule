package ca.allanwang.capsule.sample.fragments;

import java.util.Arrays;
import java.util.List;

import ca.allanwang.capsule.library.fragments.ViewPagerFragment;
import ca.allanwang.capsule.library.interfaces.CPage;
import ca.allanwang.capsule.library.item.PageItem;
import ca.allanwang.capsule.sample.R;

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
