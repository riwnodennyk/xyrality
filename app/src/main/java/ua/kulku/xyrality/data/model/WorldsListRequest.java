package ua.kulku.xyrality.data.model;

import android.os.Parcelable;

import auto.parcel.AutoParcel;

/**
 * Created by aindrias on 26.09.2015.
 */
@AutoParcel
public abstract class WorldsListRequest implements Parcelable {
    public static Builder builder() {
        return new AutoParcel_WorldsListRequest.Builder();
    }


    abstract String login();

    abstract String password();

    abstract String deviceType();

    abstract String deviceId();

    @AutoParcel.Builder
    public abstract static class Builder {
        /**
         * @param login The device identifier or email address used to sign in.
         * @return
         */
        abstract public Builder login(String login);

        /**
         * @param password The corresponding password for the login.
         * @return
         */
        public abstract Builder password(String password);

        /**
         * @param deviceType A string describing the hardware and operating system of a device.
         * @return
         */
        public abstract Builder deviceType(String deviceType);

        /**
         * @param deviceId A unique identifier that identifies the device.
         * @return
         */
        public abstract Builder deviceId(String deviceId);

        public abstract WorldsListRequest build();
    }
}
