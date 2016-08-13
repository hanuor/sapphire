package com.hanuor.sapphiredemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shantanu Johri on 30-07-2016.
 */
public class DemoObject implements Parcelable {

    String name;

    protected DemoObject(Parcel in) {
        readFromParcel(in);
    }
    public DemoObject(String name){
        this.name = name;

    }

    public static final Creator<DemoObject> CREATOR = new Creator<DemoObject>() {
        @Override
        public DemoObject createFromParcel(Parcel in) {
            return new DemoObject(in);
        }

        @Override
        public DemoObject[] newArray(int size) {
            return new DemoObject[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
    }
    private void readFromParcel(Parcel in) {

        // We just need to read back each
        // field in the order that it was
        // written to the parcel
        this.name = in.readString();

    }
}
