package grocerylist.india.com.materialdesign.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import grocerylist.india.com.materialdesign.R;
import grocerylist.india.com.materialdesign.fragment.ProductListFragment;


/**
 * Created by abhijeetsogani on 9/17/15.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<TabData> tabDataArrayList;

    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        tabDataArrayList = new ArrayList<>();

        tabDataArrayList.add(new TabData(1, context.getResources().getString(R.string.tab_pen)));
        tabDataArrayList.add(new TabData(2,context.getResources().getString(R.string.tab_canvas)));
        tabDataArrayList.add(new TabData(3, context.getResources().getString(R.string.tab_color)));

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabDataArrayList.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return ProductListFragment.newInstance(tabDataArrayList.get(position).getId(), tabDataArrayList.get(position).getTitle());
    }

    @Override
    public int getCount() {
        return tabDataArrayList.size();
    }


}
