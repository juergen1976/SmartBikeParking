package juergentoth.smartbikeparking;

import juergentoth.smartbikeparking.common.ParkingSlot;

public interface ParkSelector {

    public ParkingSlot getActualParkingSlot();

    public void addParkingSlot(ParkingSlot parkingSlot);

    public ParkingSlot getLatestParkingSlot();

    public void saveAll();

    public void loadAll();
}
