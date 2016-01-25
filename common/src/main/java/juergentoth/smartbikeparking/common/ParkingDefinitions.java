package juergentoth.smartbikeparking.common;

import java.util.ArrayList;
import java.util.List;

public class ParkingDefinitions {

    private List<String> parkingSlots = new ArrayList<>();

    private List<String> parkingLocations = new ArrayList<>();

    public ParkingDefinitions() {
        parkingSlots.add("1");
        parkingSlots.add("2");
        parkingSlots.add("3");
        parkingSlots.add("4");
        parkingSlots.add("5");
        parkingSlots.add("6");

        parkingLocations.add("Pfosten");
        parkingLocations.add("Vorne");
        parkingLocations.add("Hinten");
        parkingLocations.add("Mitte");
    }

    public List<String> getParkingLocations() {
        return parkingLocations;
    }

    public List<String> getParkingSlots() {
        return parkingSlots;
    }
}
