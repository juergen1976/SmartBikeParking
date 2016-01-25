package juergentoth.smartbikeparking.slot;


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


public class SlotSelection extends Fragment {

    private ParkingDefinitions definitions = new ParkingDefinitions();
    private ParkingHistory history = new ParkingHistory();
    private ParkingHistory parkingHistory;


    public SlotSelection() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View overview = inflater.inflate(R.layout.slot_selection, container, false);

        // Get the list component from the layout of the activity
        WearableListView listView = (WearableListView) overview.findViewById(R.id.slot_list);
        listView.setGreedyTouchMode(true);

        // Assign an adapter to the list
        listView.setAdapter(new SlotAdapter(container.getContext(), definitions.getParkingSlots().toArray(new
                String[definitions.getParkingSlots().size()])));

        listView.setClickListener(mClickListener);
        return overview;
    }

    private WearableListView.ClickListener mClickListener = new WearableListView.ClickListener() {
                @Override
                public void onClick(WearableListView.ViewHolder viewHolder) {
                    int position = viewHolder.getLayoutPosition();
                    ImageView imageView = (ImageView) viewHolder.itemView.findViewById(R.id.slotImage);
                    imageView.setImageResource(R.drawable.done);

                    // Save actual slot
                    String actualSlot = definitions.getParkingSlots().get(position);
                    parkingHistory.getActualSlot().setSlot(actualSlot);
                }

                @Override
                public void onTopEmptyRegionClick() {

                }
            };
}
