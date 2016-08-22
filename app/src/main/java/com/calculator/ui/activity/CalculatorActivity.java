package com.calculator.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.calculator.R;
import com.calculator.adapter.PagerAdapter;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;

public class CalculatorActivity extends AppCompatActivity {

    public static final String LOGIN_KEY = "login";
    public static final String PASSWORD_KEY = "password";
    private final String TAG = CalculatorActivity.class.getSimpleName();

    @Bind(R.id.pager)
    ViewPager viewPager;
    @Bind(R.id.tabs)
    TabLayout tabLayout;
    /*    @Bind(R.id.coordinatorLayout)
        CoordinatorLayout coordinatorLayout;*/
    @BindString(R.string.tab_factorial)
    String factorialTitle;
    @BindString(R.string.tab_palindrome)
    String palindromeTitle;
    @BindString(R.string.tab_pairs)
    String pairsTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        ButterKnife.bind(this);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        setupTitles();
    }

    private void setupTitles() {
        tabLayout.getTabAt(0).setText(palindromeTitle);
        tabLayout.getTabAt(1).setText(factorialTitle);
        tabLayout.getTabAt(2).setText(pairsTitle);
    }
}
