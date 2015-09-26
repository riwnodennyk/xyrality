package ua.kulku.xyrality.data.model;

import android.os.Parcelable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

import auto.parcel.AutoParcel;

@AutoParcel
@JsonDeserialize(as = AutoParcel_WorldsListResponse.class)
public abstract class WorldsListResponse implements Parcelable {
    public abstract String serverVersion();

    public abstract List<World> allAvailableWorlds();
}
