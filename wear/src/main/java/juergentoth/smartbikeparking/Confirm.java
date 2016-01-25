package juergentoth.smartbikeparking;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.ConfirmationActivity;
import android.support.wearable.view.DelayedConfirmationView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;

import juergentoth.smartbikeparking.common.ParkingHistory;
import juergentoth.smartbikeparking.common.ParkingSlot;


public class Confirm extends Fragment implements DelayedConfirmationView.DelayedConfirmationListener {

    private DelayedConfirmationView mDelayedView;
    private boolean mIsAnimating = false;
    private TextView mTextView;

    private ParkingHistory parkingHistory;


    public Confirm() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View confirmView = inflater.inflate(R.layout.confirm, container, false);

        mDelayedView = (DelayedConfirmationView) confirmView.findViewById(R.id.delayed_confirm);
        mTextView = (TextView) confirmView.findViewById(R.id.txtSave);
        mDelayedView.setImageResource(R.drawable.ic_action_send);
        mDelayedView.setListener(this);
        // Two seconds to cancel the action
        mDelayedView.setTotalTimeMs(10000);
        confirmView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickHandler();
            }
        });

        return confirmView;
    }

    @Override
    public void onTimerFinished(View view) {
        mIsAnimating = false;

        // Starting the confirmation screen
        Intent intent = new Intent(view.getContext(), ConfirmationActivity.class);
        intent.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE,
                ConfirmationActivity.SUCCESS_ANIMATION);
        intent.putExtra(ConfirmationActivity.EXTRA_MESSAGE, "Gespeichert");
        startActivity(intent);

        mDelayedView.reset();
        mDelayedView.setImageResource(R.drawable.ic_action_send);
        mTextView.setText("Speichern");

        // Save
        ParkingSlot actualSlot = new ParkingSlot();
        actualSlot.setLocation(parkingHistory.getActualSlot().getLocation());
        actualSlot.setSlot(parkingHistory.getActualSlot().getSlot());
        actualSlot.setParkDate(new Date());

        parkingHistory.addParkingSlot(actualSlot);
    }

    @Override
    public void onTimerSelected(View view) {
        // User canceled, abort the action
        mDelayedView.reset();
        mDelayedView.setImageResource(R.drawable.ic_action_send);
    }

    public void onClickHandler() {
        if (mIsAnimating) {
            mTextView.setText("Speichern");
            mIsAnimating = false;
            mDelayedView.setImageResource(R.drawable.ic_action_send);
            return;
        }
        mIsAnimating = true;
        mTextView.setText("Speichern...");
        mDelayedView.setTotalTimeMs(3000);
        mDelayedView.start();
        mDelayedView.setImageResource(R.drawable.ic_full_cancel);
    }
}
