package ua.kulku.xyrality.data.api;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;
import ua.kulku.xyrality.data.model.WorldsListRequest;
import ua.kulku.xyrality.data.model.WorldsListResponse;

/**
 * Created by aindrias on 26.09.2015.
 */
public interface XyralityApi {
    @POST("/XYRALITY/WebObjects/BKLoginServer.woa/wa/worlds")
    Call<WorldsListResponse> listWorlds(@Body WorldsListRequest worldsListRequest);
}
