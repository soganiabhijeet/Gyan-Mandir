package grocerylist.india.com.materialdesign.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import grocerylist.india.com.materialdesign.fragment.DairyProducts;
import grocerylist.india.com.materialdesign.fragment.OtherProducts;
import grocerylist.india.com.materialdesign.fragment.PerishableItems;


/**
 * Created by abhijeetsogani on 9/17/15.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }
    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    //This method return the fragment for the every position in the View Pager

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        if(position == 0) // if the position is 0 we are returning the First tab
        {
            DairyProducts tab1 = new DairyProducts();
            return tab1;
        }
        else if(position==1)             // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            PerishableItems tab2 = new PerishableItems();
            return tab2;
        }
        else{
            OtherProducts tab3=new OtherProducts();
            return tab3;
        }

    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return NumbOfTabs;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip


}
