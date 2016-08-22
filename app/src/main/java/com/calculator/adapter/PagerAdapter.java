package com.calculator.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.calculator.ui.fragment.FactorialFragment;
import com.calculator.ui.fragment.PairsFragment;
import com.calculator.ui.fragment.PalindromeFragment;

/**
 * Created by valentyn on 07.08.16.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PalindromeFragment();
            case 1:
                return new FactorialFragment();
            case 2:
                return new PairsFragment();

        }
        return null;
    }

   /* @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "PALINDROME";
            case 1:
                return "FACTORIAL";
            case 2:
                return "PAIRS";
        }
        return null;
    }*/

    @Override
    public int getCount() {
        return 3;
    }
}
