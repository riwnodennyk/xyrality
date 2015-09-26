package ua.kulku.xyrality.data.model;

import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

import auto.parcel.AutoParcel;

@AutoParcel
@JsonDeserialize(builder = AutoParcel_WorldsListResponse.Builder.class)
public abstract class WorldsListResponse implements Parcelable {

    @JsonProperty("allAvailableWorlds")
    public abstract List<World> allAvailableWorlds();

    @AutoParcel.Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static abstract class Builder {

        @JsonProperty("allAvailableWorlds")
        public abstract Builder allAvailableWorlds(List<World> allAvailableWorlds);

        public abstract WorldsListResponse build();
    }
}
