package io.github.ingluismontesdeoca.fragment.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import io.github.ingluismontesdeoca.fragment.admin.Debug;

/**
 * Created by Aministrador on 02/09/2016.
 */
public class PagerAdapter extends FragmentPagerAdapter{

    private ArrayList<Fragment> Fragments;
    private Context context;

    public PagerAdapter(FragmentManager fm, ArrayList<Fragment> Fragments,Context context) {
        super(fm);
        this.Fragments = Fragments;
        this.context = context;
        Debug.mkToast("construct PagerAdapter",context);
    }

    @Override
    public Fragment getItem(int position) {
        Debug.mkToast(String.valueOf(position),context);
        return Fragments.get(position);
    }

    @Override
    public int getCount() {
        return Fragments.size();
    }

}
