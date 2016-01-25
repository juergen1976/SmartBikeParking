package juergentoth.smartbikeparking.location;


import android.app.Fragment;
import android.os.Bundle;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import juergentoth.smartbikeparking.R;
import juergentoth.smartbikeparking.common.ParkingDefinitions;
import juergentoth.smartbikeparking.common.ParkingHistory;

public class LocationSelection extends Fragment {

    private ParkingDefinitions definitions = new ParkingDefinitions();

    public LocationSelection() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View overview = inflater.inflate(R.layout.location_selection, container, false);

        // Get the list component from the layout of the activity
        WearableListView listView = (WearableListView) overview.findViewById(R.id.location_list);
        listView.setGreedyTouchMode(true);

        // Assign an adapter to the list
        listView.setAdapter(new LocationAdapter(getActivity(), definitions.getParkingLocations().toArray
                (new
                String[definitions.getParkingLocations().size()])));
        listView.setClickListener(mClickListener);
        return overview;
    }

    private WearableListView.ClickListener mClickListener = new WearableListView.ClickListener() {
        @Override
        public void onClick(WearableListView.ViewHolder viewHolder) {
            int position = viewHolder.getLayoutPosition();
            ImageView imageView = (ImageView) viewHolder.itemView.findViewById(R.id.locationImage);
            imageView.setImageResource(R.drawable.done);

            // Save actual location
            String actualLocation = definitions.getParkingLocations().get(position);
            ParkingHistory.getActualSlot().setLocation(actualLocation);
        }

        @Override
        public void onTopEmptyRegionClick() {

        }
    };
}
