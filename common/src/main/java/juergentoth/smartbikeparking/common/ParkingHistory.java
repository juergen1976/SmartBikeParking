package juergentoth.smartbikeparking.common;


import java.util.ArrayList;
import java.util.List;

public class ParkingHistory {

    private static List<ParkingSlot> parkingHistory = new ArrayList<>();
    private static ParkingSlot actualSlot = new ParkingSlot();

    public static List<ParkingSlot> getParkingHistory() {
        return parkingHistory;
    }

    public static void addParkingSlot(ParkingSlot parkSlot) {

        parkingHistory.add(parkSlot);
    }

    public static ParkingSlot getLatestParkingSlot() {
        if (parkingHistory.size()==0) {
            return null;
        }
        return parkingHistory.get(parkingHistory.size()-1);
    }

    public static ParkingSlot getActualSlot() {
        return actualSlot;
    }
}
