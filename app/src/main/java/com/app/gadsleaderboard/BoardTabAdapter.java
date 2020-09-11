package com.app.gadsleaderboard;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.tabs.TabLayout;

public class BoardTabAdapter extends FragmentStateAdapter {
    private static final int CARD_ITEM_SIZE = 2;
    public BoardTabAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        return BoardFragment.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return CARD_ITEM_SIZE;
    }
}
