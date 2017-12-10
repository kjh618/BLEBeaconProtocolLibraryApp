package kr.hs.gshs.blebeaconprotocollibrary;

import android.bluetooth.le.ScanResult;

import java.util.ArrayList;

/**
 * Created by kjh on 2017-12-09.
 */

public class ScanResultParser {
    private ScanResultParser() {
    }

    public static Object[] parse(ScanResult scanResult) {
        Object[] ret = new Object[2];
        byte[] rawBytes = scanResult.getScanRecord().getBytes();

        try {
            ret[0] = PacketTypes.fromOrdinal(rawBytes[0]);

            ArrayList<Struct> structs = new ArrayList<>();

            for(int i=1; i<rawBytes.length; ++i) {
                int structLength = rawBytes[i];
                ++i;

                StructTypes structType = StructTypes.fromOrdinal(rawBytes[i]);
                ++i;

                byte[] structData = new byte[structLength-1];
                int j;
                for (j=0; j<structLength-1; ++j) {
                    structData[j] = rawBytes[i+j];
                }
                i += j;

                structs.add(new Struct(structType, new String(structData)));
            }

            ret[1] = structs;
        } catch (ArrayIndexOutOfBoundsException e) {
            ret[0] = ret[1] = null;
        }

        return ret;
    }
}
