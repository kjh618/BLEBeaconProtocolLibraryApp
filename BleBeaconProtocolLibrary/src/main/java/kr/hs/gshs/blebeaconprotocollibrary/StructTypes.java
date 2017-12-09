package kr.hs.gshs.blebeaconprotocollibrary;

/**
 * Created by kjh on 2017-12-09.
 */

public enum StructTypes {
    TEXT_UNCOMPRESSED("Text (Uncompressed)"),
    TEXT_RLE("Text (Run-length Encoding)"),
    TEXT_HUFFMAN_CODING("Text (Huffman Coding)"),
    REGULAR_URL("Regular URL"),
    GOOGL_URL("goo.gl URL"),
    DEVICE_NAME("Device Name"),
    SERVICE_NAME("Service Name");

    private String displayName;
    private static StructTypes[] values = values();

    StructTypes(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }

    public static StructTypes fromOrdinal(int ordinal) {
        return values[ordinal];
    }

    public static StructTypes[] getValues() {
        return values;
    }
}
