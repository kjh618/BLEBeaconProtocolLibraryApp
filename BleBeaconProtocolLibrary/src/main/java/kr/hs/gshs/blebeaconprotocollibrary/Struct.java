package kr.hs.gshs.blebeaconprotocollibrary;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kjh on 2017-12-09.
 */

public class Struct implements Parcelable {
    private StructTypes type;
    private String data;

    public Struct(StructTypes type, String data) {
        this.type = type;
        this.data = data;
    }

    public int getLength() {
        return data.length() + 1;
    }

    public StructTypes getType() {
        return type;
    }

    public String getData() {
        return data;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Struct))
            return false;

        Struct s = (Struct) obj;
        return (type == s.type) && data.equals(s.data);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type.name());
        dest.writeString(data);
    }

    public static final Parcelable.Creator<Struct> CREATOR = new Parcelable.Creator<Struct>() {
        public Struct createFromParcel(Parcel in) {
            return new Struct(in);
        }

        public Struct[] newArray(int size) {
            return new Struct[size];
        }
    };

    private Struct(Parcel in) {
        type = StructTypes.valueOf(in.readString());
        data = in.readString();
    }
}
