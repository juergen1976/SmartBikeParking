package juergentoth.smartbikeparking.common;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class ParkingHistory {

    private List<ParkingSlot> parkingHistory;
    private ParkingSlot actualSlot = new ParkingSlot();
    private Activity mainActivity;

    public ParkingHistory(Activity activity) {
        mainActivity = activity;
    }

    public List<ParkingSlot> getParkingHistory() {
        return parkingHistory;
    }

    public void addParkingSlot(ParkingSlot parkSlot) {
        parkingHistory.add(parkSlot);
    }

    public ParkingSlot getLatestParkingSlot() {
        if (parkingHistory.size()==0) {
            return null;
        }
        return parkingHistory.get(parkingHistory.size()-1);
    }

    public ParkingSlot getActualSlot() {
        return actualSlot;
    }

    public void saveAll() {
        Gson gson = new Gson();
        String parkingHistoryAsJson = gson.toJson(parkingHistory);
        SharedPreferences sharedPref = mainActivity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("park_history", parkingHistoryAsJson);
        editor.commit();
    }

    public void loadAll() {
        SharedPreferences sharedPref = mainActivity.getPreferences(Context.MODE_PRIVATE);
        String parkingHistoryAsJson = sharedPref.getString("park_history", "");
        parkingHistory = new ArrayList<>();

        if (parkingHistoryAsJson !=null && parkingHistoryAsJson!="") {
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            JsonArray jsonArray = parser.parse(parkingHistoryAsJson).getAsJsonArray();
            for (JsonElement jsonElement : jsonArray) {
                parkingHistory.add(gson.fromJson(jsonElement, ParkingSlot.class));
            }
        }
    }
}
