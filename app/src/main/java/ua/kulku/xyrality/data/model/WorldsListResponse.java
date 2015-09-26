package ua.kulku.xyrality.data.model;

import android.os.Parcelable;

import java.util.List;

import auto.parcel.AutoParcel;

@AutoParcel
public abstract class WorldsListResponse implements Parcelable {
    abstract String serverVersion();
    abstract List<World> allAvailableWorlds();
}
