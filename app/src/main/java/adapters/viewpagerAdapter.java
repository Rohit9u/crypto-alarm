package adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import fragment.history;
import fragment.market;
import fragment.setAlarm;

public class viewpagerAdapter extends FragmentPagerAdapter {

    public viewpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0: return new market();
            case 1: return new history();
            case 2: return new setAlarm();
            default: return new market();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title=null;
        if(position==0){
            title="Market";
        }
        else if(position==1){
            title="Alarm";
        }
        else{
            title="History";
        }
        return
                title;
    }
}
