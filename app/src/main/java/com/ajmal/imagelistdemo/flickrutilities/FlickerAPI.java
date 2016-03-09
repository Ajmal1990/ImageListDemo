package com.ajmal.imagelistdemo.flickrutilities;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ajmal on 8/3/16.
 */
public interface FlickerAPI {

    @GET("rest/?")
    Call<ApiResponse> getPhotoSet(@Query("method") String method,@Query("api_key") String apikey,@Query("format") String format,
                                      @Query("photoset_id") String PhotoSetID,@Query("nojsoncallback") String callback);

}
