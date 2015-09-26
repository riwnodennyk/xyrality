package ua.kulku.xyrality.data.model;

import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import auto.parcel.AutoParcel;

@AutoParcel
@JsonDeserialize(builder = AutoParcel_WorldStatus.Builder.class)
public abstract class WorldStatus implements Parcelable {
    public abstract String id();

    public abstract String description();

    @AutoParcel.Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static abstract class Builder {
        @JsonProperty("id")
        public abstract Builder id(String id);

        @JsonProperty("description")
        public abstract Builder description(String description);

        public abstract WorldStatus build();
    }
}
