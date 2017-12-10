package kr.hs.gshs.blebeaconprotocollibrary;

import android.bluetooth.le.ScanResult;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by kjh on 2017-12-09.
 */

public class ScanResultParser {
    private static final String TAG = ScanResultParser.class.getSimpleName();

    private ScanResultParser() {
    }

    public static PacketData parse(ScanResult scanResult) {
        PacketData ret;

        byte[] rawBytes = new byte[26];
        System.arraycopy(scanResult.getScanRecord().getBytes(), 5, rawBytes, 0, 26);

        PacketTypes packetType;
        ArrayList<Struct> structs = new ArrayList<>();

        try {
            packetType = PacketTypes.fromOrdinal(rawBytes[0]);

            for(int i=1; i<rawBytes.length; ++i) {
                int structLength = rawBytes[i];
                if(structLength <= 0) {
                    break;
                }
                Log.d(TAG, "Parsing...: Current pos in rawBytes: " + i + ", Current structLength: " + structLength);
                ++i;

                StructTypes structType = StructTypes.fromOrdinal(rawBytes[i]);
                ++i;

                byte[] structData = new byte[structLength-1];
                int j;
                for (j=0; j<structLength-1; ++j) {
                    structData[j] = rawBytes[i+j];
                }
                i += j - 1;

                structs.add(new Struct(structType, new String(structData)));
            }

            ret = new PacketData(true, packetType, structs);
        } catch (ArrayIndexOutOfBoundsException | NegativeArraySizeException e) {
            ret = new PacketData(false, null, null);
        }

        return ret;
    }
}
