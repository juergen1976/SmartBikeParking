package juergentoth.smartbikeparking.location;

import android.content.Context;
import android.support.wearable.view.WearableListView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import juergentoth.smartbikeparking.R;

public class LocationItemLayout extends LinearLayout implements WearableListView.OnCenterProximityListener {

    private ImageView mCircle;
    private TextView mName;

    private final float mFadedTextAlpha;
    private final int mFadedCircleColor;
    private final int mChosenCircleColor;

    private static final float NO_ALPHA = 1f, PARTIAL_ALPHA = 0.35f;
    private static final float NO_X_TRANSLATION = 0f, X_TRANSLATION = 15f;

    public LocationItemLayout(Context context) {
        this(context, null);
    }

    public LocationItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LocationItemLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        mFadedTextAlpha = 50f;
        mFadedCircleColor = getResources().getColor(R.color.grey);
        mChosenCircleColor = getResources().getColor(R.color.blue);
    }

    // Get references to the icon and text in the item layout definition
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // These are defined in the layout file for list items
        // (see next section)
        mCircle = (ImageView) findViewById(R.id.locationImage);
        mName = (TextView) findViewById(R.id.locationName);
    }

    @Override
    public void onCenterPosition(boolean animate) {
        if (animate) {
            animate().alpha(NO_ALPHA).translationX(X_TRANSLATION).start();
        } else {
            setAlpha(NO_ALPHA);
            setTranslationX(X_TRANSLATION);
        }
        mName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
    }

    @Override
    public void onNonCenterPosition(boolean animate) {
        if (animate) {
            animate().alpha(PARTIAL_ALPHA).translationX(NO_X_TRANSLATION).start();
        } else {
            setAlpha(PARTIAL_ALPHA);
            setTranslationX(NO_X_TRANSLATION);
        }
        mName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
    }
}