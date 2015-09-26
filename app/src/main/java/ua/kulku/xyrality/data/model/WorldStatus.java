package ua.kulku.xyrality.data.model;

import android.os.Parcelable;

import auto.parcel.AutoParcel;

/**
 * Created by aindrias on 26.09.2015.
 */
@AutoParcel
abstract class WorldStatus implements Parcelable {
    abstract String id();
    abstract String description();
}
