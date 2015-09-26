package ua.kulku.xyrality.data.model;

import android.os.Parcelable;

import java.net.URL;

import auto.parcel.AutoParcel;

@AutoParcel
abstract class World implements Parcelable{
    abstract String id();
    abstract String language();
    abstract URL url();
    abstract String country();
    abstract WorldStatus worldStatus();
    abstract URL mapURL();
    abstract String name();
}
