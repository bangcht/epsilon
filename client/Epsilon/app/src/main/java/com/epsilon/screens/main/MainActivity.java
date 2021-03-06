package com.epsilon.screens.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.epsilon.R;

public class MainActivity extends AppCompatActivity {


    private static final String TAB_POSITION_KEY = "tab position";
    private static final String IS_FROM_COURSE_KEY = "from course key";
    private static final String IS_FROM_COURSE_URL_KEY = "url key";
    public static final int CATEGORY_TAB_POSITION = 1;
    public static final int COURSES_TAB_POSITION = 0;
    public static final int RECOMMEND_TAB_POSITION = 2;


    public static String POSITION = "POSITION";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String[] mTitles = new String[] {"Đã tham gia", "Khám phá", "Gợi ý"};

    public static Intent makeIntent(Context context, int tabPosition) {
        return new Intent(context, MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .putExtra(TAB_POSITION_KEY, tabPosition);
    }

    public static Intent makeIntent(Context context, boolean isFromEnrollCourse, String url) {
        return makeIntent(context, COURSES_TAB_POSITION)
                .putExtra(IS_FROM_COURSE_KEY, isFromEnrollCourse)
                .putExtra(IS_FROM_COURSE_URL_KEY, url);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up view pager and tab layout
        setUpTabLayout(savedInstanceState);


        // Set up tool bar as an action bar
        setUpToolBar();

        // Go to appropriate tab
        if (savedInstanceState == null) {
            int tabPosition = getIntent().getIntExtra(TAB_POSITION_KEY, 0);
            goToTab(tabPosition);
            setTitle(mTitles[tabPosition]);

            if (getIntent().getBooleanExtra(IS_FROM_COURSE_KEY, false)) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                        getIntent().getStringExtra(IS_FROM_COURSE_URL_KEY)
                ));
                startActivity(browserIntent);
            }
        }
    }

    private void goToTab(int tabPosition) {
        viewPager.setCurrentItem(tabPosition);
    }


    private void setUpToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    private void setUpTabLayout(Bundle savedInstanceState) {
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        MainScreenPagerAdapter pagerAdapter =
                new MainScreenPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(pagerAdapter);

        // Give the TabLayout the ViewPager
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        if (tabLayout != null) {
            tabLayout.setupWithViewPager(viewPager);
        }

        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                setTitle(mTitles[tab.getPosition()]);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                super.onTabUnselected(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                super.onTabReselected(tab);
            }
        });


    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION, tabLayout.getSelectedTabPosition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        viewPager.setCurrentItem(savedInstanceState.getInt(POSITION));
        setTitle(mTitles[savedInstanceState.getInt(POSITION)]);
    }


}
