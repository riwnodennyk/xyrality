package ua.kulku.xyrality.data.api;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Headers;
import retrofit.http.POST;
import ua.kulku.xyrality.data.model.WorldsListResponse;

/**
 * Created by aindrias on 26.09.2015.
 */
public interface XyralityApi {
    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    @POST("/XYRALITY/WebObjects/BKLoginServer.woa/wa/worlds")
    Call<WorldsListResponse> listWorlds(
            @Field("login") String login,
            @Field("password") String password,
            @Field("deviceType") String deviceType,
            @Field("deviceId") String deviceId
    );
}
