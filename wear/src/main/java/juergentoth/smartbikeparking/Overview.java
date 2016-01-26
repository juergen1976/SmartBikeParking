package juergentoth.smartbikeparking;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;

import juergentoth.smartbikeparking.common.ParkingSlot;


public class Overview extends Fragment {

    private TextView txtSlot;
    private TextView txtLocation;
    private TextView txtDuration;

    public Overview() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View overview = inflater.inflate(R.layout.overview, container, false);
        txtSlot = (TextView) overview.findViewById(R.id.txtSpace);
        txtLocation = (TextView) overview.findViewById(R.id.txtLocation);
        txtDuration = (TextView) overview.findViewById(R.id.txtDuration);



        return overview;
    }

    @Override
    public void onStart() {
        super.onStart();
        ParkSelector parkSelector = (ParkSelector) getActivity();

        ParkingSlot actualSlot = parkSelector.getLatestParkingSlot();
        if (actualSlot != null) {
            txtSlot.setText(actualSlot.getSlot());
            txtLocation.setText(actualSlot.getLocation());
            Date now = new Date();
            Long timeNow = now.getTime();
            if (actualSlot.getParkDate() != null) {
                Long timeParked = actualSlot.getParkDate().getTime();
                Long parkedMin = (timeNow - timeParked) / 1000 / 60;
                txtDuration.setText(parkedMin.toString() + "min");
            }
            else {
                txtDuration.setText("-");
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
