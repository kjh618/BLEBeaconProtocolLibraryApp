package kr.hs.gshs.blebeaconprotocollibrary;

import android.bluetooth.le.AdvertiseData;

import java.util.ArrayList;

/**
 * Created by kjh on 2017-12-09.
 */

public class AdvertiseDataBuilder {

    private AdvertiseDataBuilder() {
    }

    /**
     * Returns an AdvertiseData object.
     *
     * Note: There is a strict limit of 31 Bytes on packets sent over BLE Advertisements.
     *  This includes everything put into AdvertiseData including UUIDs, device info, &
     *  arbitrary service or manufacturer data.
     *  Attempting to send packets over this limit will result in a failure with error code
     *  AdvertiseCallback.ADVERTISE_FAILED_DATA_TOO_LARGE. Catch this error in the
     *  onStartFailure() method of an AdvertiseCallback implementation.
     */
    public static AdvertiseData build(PacketTypes packetType, Struct[] structs) {
        ArrayList<Byte> rawBytes = new ArrayList<>();
        rawBytes.add((byte) packetType.ordinal());

        for(Struct s : structs) {
            rawBytes.add((byte) s.getLength());

            rawBytes.add((byte) s.getType().ordinal());

            byte[] data = s.getData().getBytes();
            for(byte d : data) {
                rawBytes.add(d);
            }
        }

        int part1;
        byte[] part2;
        if(rawBytes.size() <= 1) {
            part1 = rawBytes.get(0);
            part2 = new byte[0];
        } else {
            part1 = 256 * rawBytes.get(1) + rawBytes.get(0);
            part2 = new byte[rawBytes.size()-2];
            for(int i=0; i<part2.length; ++i) {
                part2[i] = rawBytes.get(i+2);
            }
        }

        AdvertiseData.Builder dataBuilder = new AdvertiseData.Builder();
        dataBuilder.addManufacturerData(part1, part2);

        return dataBuilder.build();
    }
}
