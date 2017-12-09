package kr.hs.gshs.blebeaconprotocollibrary;

/**
 * Created by kjh on 2017-12-09.
 */

public enum PacketTypes {
    INFORMATION("Information"),
    ADVERTISEMENT("Advertisement"),
    COUPON("Coupon"),
    CAUTION("Caution");

    private String displayName;
    private static PacketTypes[] values = values();

    PacketTypes(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }

    public static PacketTypes fromOrdinal(int ordinal) {
        return values[ordinal];
    }

    public static PacketTypes[] getValues() {
        return values;
    }
}
