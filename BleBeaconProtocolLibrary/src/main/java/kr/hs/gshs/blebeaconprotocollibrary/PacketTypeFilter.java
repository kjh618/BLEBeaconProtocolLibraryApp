package kr.hs.gshs.blebeaconprotocollibrary;

/**
 * Created by kjh on 2017-12-10.
 */

public class PacketTypeFilter {
    private boolean[] isBlocked = new boolean[PacketTypes.getValues().length];

    public void block(PacketTypes packetType) {
        isBlocked[packetType.ordinal()] = true;
    }

    public void unblock(PacketTypes packetType) {
        isBlocked[packetType.ordinal()] = false;
    }

    public boolean isBlocked(PacketTypes packetType) {
        return isBlocked[packetType.ordinal()];
    }
}
