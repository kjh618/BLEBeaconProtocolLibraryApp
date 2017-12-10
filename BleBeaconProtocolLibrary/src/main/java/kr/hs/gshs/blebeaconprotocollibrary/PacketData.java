package kr.hs.gshs.blebeaconprotocollibrary;

import java.util.ArrayList;

/**
 * Created by kjh on 2017-12-10.
 */

public class PacketData {
    private boolean isSupportedPacket;
    private PacketTypes packetType;
    private ArrayList<Struct> structs;

    public PacketData(boolean isSupportedPacket, PacketTypes packetType, ArrayList<Struct> structs) {
        this.isSupportedPacket = isSupportedPacket;
        this.packetType = packetType;
        this.structs = structs;
    }

    public boolean isSupportedPacket() {
        return isSupportedPacket;
    }

    public PacketTypes getPacketType() {
        return packetType;
    }

    public ArrayList<Struct> getStructs() {
        return structs;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof PacketData))
            return false;

        PacketData p = (PacketData) obj;
        return (isSupportedPacket == p.isSupportedPacket) && (packetType == p.packetType) && structs.equals(p.structs);
    }
}