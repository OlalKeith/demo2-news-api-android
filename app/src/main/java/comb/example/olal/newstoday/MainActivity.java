package comb.example.olal.newstoday;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import comb.example.olal.newstoday.fragment.BusinessFragment;
import comb.example.olal.newstoday.fragment.GeneralFragment;
//import comb.example.olal.newstoday.fragment.PoliticsFragment;
import comb.example.olal.newstoday.fragment.PoliticsFragment;
import comb.example.olal.newstoday.fragment.SportFragment;
import comb.example.olal.newstoday.fragment.TechFragment;


public class MainActivity extends AppCompatActivity {


    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        viewPager = (ViewPager)findViewById(R.id.viewpager);


        tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

//
        setUpViewPager(viewPager);

    }


    public void setUpViewPager(ViewPager viewPager){
        //an instance of viewpageradapter
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new BusinessFragment(),"Business");
        viewPagerAdapter.addFragment(new PoliticsFragment(),"Politics");
        viewPagerAdapter.addFragment(new TechFragment(),"Tech");
        viewPagerAdapter.addFragment(new SportFragment(),"Sport");
        viewPagerAdapter.addFragment(new GeneralFragment(), "General");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter{

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position) ;
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void  addFragment(Fragment fragment, String title){
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
