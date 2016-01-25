package juergentoth.smartbikeparking;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.wearable.view.FragmentGridPagerAdapter;

import juergentoth.smartbikeparking.location.LocationSelection;
import juergentoth.smartbikeparking.slot.SlotSelection;

public class ParkingViewPagerAdapter extends FragmentGridPagerAdapter {

    private Context context;

    public ParkingViewPagerAdapter(Context ctx, FragmentManager fm) {
        super(fm);
        context = ctx;
    }

    @Override
    public int getColumnCount(int arg0) {
        return 4;
    }

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public Fragment getFragment(int row, int column) {
        if (column == 0) {
            return new Overview();
        }
        else if (column == 1) {
            return new SlotSelection();
        }
        else if (column == 2) {
            return new LocationSelection();
        }
        else {
            return new Confirm();
        }
    }
}
