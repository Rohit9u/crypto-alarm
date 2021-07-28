package com.example.cryptoalarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import adapters.viewpagerAdapter;

public class MainActivity extends AppCompatActivity {
    private TabLayout layout;
    private ViewPager viewPager;
    final int[] image = {R.drawable.ic_increase, R.drawable.ic_alarm_clock, R.drawable.ic_history};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.pager);
        viewPager.setAdapter(new viewpagerAdapter(getSupportFragmentManager()));
        layout.setupWithViewPager(viewPager);
        getSupportActionBar().hide();
        this.layout.setupWithViewPager(this.viewPager);
        this.layout.getTabAt(0).setIcon(this.image[0]);
        this.layout.getTabAt(1).setIcon(this.image[1]);
        this.layout.getTabAt(2).setIcon(this.image[2]);
        this.layout.getTabAt(0).getIcon().setColorFilter(Color.parseColor("#eb1b10"), PorterDuff.Mode.SRC_IN);
        this.layout.getTabAt(1).getIcon().setColorFilter(-1, PorterDuff.Mode.SRC_IN);
        this.layout.getTabAt(2).getIcon().setColorFilter(-1, PorterDuff.Mode.SRC_IN);
        this.layout.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) new TabLayout.OnTabSelectedListener() {
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.parseColor("#eb1b10"), PorterDuff.Mode.SRC_IN);
            }

            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN);
            }

            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}