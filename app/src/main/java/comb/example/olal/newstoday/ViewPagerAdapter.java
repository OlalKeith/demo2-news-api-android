package comb.example.olal.newstoday;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olal on 9/5/17.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    //giving a list of fragments and strings

    List<Fragment> fragmentList = new ArrayList<Fragment>();
    List<String> fragmentTitleList = new ArrayList<String>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position){

        return fragmentTitleList.get(position);
    }

    public void addFragment(Fragment fragment, String title){

        fragmentList.add(fragment);
        fragmentTitleList.add(title);
    }

}
