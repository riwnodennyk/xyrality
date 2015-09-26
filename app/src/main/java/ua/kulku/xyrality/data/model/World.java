package ua.kulku.xyrality.data.model;

import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.net.URL;

import auto.parcel.AutoParcel;

@AutoParcel
@JsonDeserialize(builder = AutoParcel_World.Builder.class)
public abstract class World implements Parcelable {
    @JsonProperty("id")
    public abstract String id();

    @JsonProperty("language")
    public abstract String language();

    @JsonProperty("url")
    public abstract URL url();

    @JsonProperty("country")
    public abstract String country();

    @JsonProperty("worldStatus")
    public abstract WorldStatus worldStatus();

    @JsonProperty("mapURL")
    public abstract URL mapURL();

    @JsonProperty("name")
    public abstract String name();

    @AutoParcel.Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static abstract class Builder {

        @JsonProperty("id")
        public abstract Builder id(String id);

        @JsonProperty("language")
        public abstract Builder language(String language);

        @JsonProperty("url")
        public abstract Builder url(URL url);

        @JsonProperty("country")
        public abstract Builder country(String country);

        @JsonProperty("worldStatus")
        public abstract Builder worldStatus(WorldStatus worldStatus);

        @JsonProperty("mapURL")
        public abstract Builder mapURL(URL mapURL);

        @JsonProperty("name")
        public abstract Builder name(String name);

        public abstract World build();
    }
}
