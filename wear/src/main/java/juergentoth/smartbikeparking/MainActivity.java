package juergentoth.smartbikeparking;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.wearable.view.DotsPageIndicator;
import android.support.wearable.view.GridViewPager;

import juergentoth.smartbikeparking.common.ParkingHistory;
import juergentoth.smartbikeparking.common.ParkingSlot;

public class MainActivity extends Activity implements ParkSelector {

    private ParkingHistory parkingHistory = new ParkingHistory(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadAll();

        setContentView(R.layout.activity_main);
        final Resources res = getResources();
        final GridViewPager pager = (GridViewPager) findViewById(R.id.bikepager);

        pager.setAdapter(new ParkingViewPagerAdapter(this, getFragmentManager()));
        DotsPageIndicator dotsPageIndicator = (DotsPageIndicator) findViewById(R.id.page_indicator);
        dotsPageIndicator.setPager(pager);
    }

    @Override
    public ParkingSlot getActualParkingSlot() {
        return parkingHistory.getActualSlot();
    }


    @Override
    public void addParkingSlot(ParkingSlot parkingSlot) {
        parkingHistory.addParkingSlot(parkingSlot);
    }

    @Override
    public ParkingSlot getLatestParkingSlot() {
        return parkingHistory.getLatestParkingSlot();
    }

    @Override
    public void saveAll() {
        parkingHistory.saveAll();
    }

    @Override
    public void loadAll() {
        parkingHistory.loadAll();
    }
}
