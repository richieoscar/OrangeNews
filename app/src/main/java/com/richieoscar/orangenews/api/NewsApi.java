package com.richieoscar.orangenews.api;

import com.richieoscar.orangenews.model.JsonResult;
import com.richieoscar.orangenews.model.SourceJsonResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    @GET("everything")
    Call<JsonResult> getResults(@Query("q") String source,
                                @Query("sortBy") String sortBy,
                                @Query("from") String from,
                                @Query("to") String to,
                                @Query("pageSize") int pageSize,
                                @Query("apiKey") String apiKey);

    @GET("top-headlines")
    Call<JsonResult> getHeadlines(@Query("country") String country,
                                  @Query("category") String category,
                                  @Query("pageSize") int source,
                                  @Query("apiKey") String apiKey);

    @GET("top-headlines")
    Call<JsonResult> getSportsNews(@Query("country") String country,
                                   @Query("category") String sport,
                                   @Query("language") String lang,
                                   @Query("pageSize") int pageSize,
                                   @Query("apiKey") String apiKey);

    @GET("top-headlines")
    Call<JsonResult> getEntertainmentNews(@Query("country") String country,
                                          @Query("category") String entertainment,
                                          @Query("pageSize") int pageSize,
                                          @Query("apiKey") String apiKey);

    @GET("top-headlines")
    Call<JsonResult> getTechNews(@Query("country") String country,
                                 @Query("category") String tech,
                                 @Query("pageSize") int pageSize,
                                 @Query("apiKey") String apiKey);

    @GET("top-headlines")
    Call<JsonResult> getBusinessNews(@Query("country") String country,
                                     @Query("category") String business,
                                     @Query("pageSize") int pageSize,
                                     @Query("apiKey") String apiKey);

    @GET("everything")
    Call<JsonResult> getSearch(@Query("q") String query,
                               @Query("sortBy") String sortBy,
                               @Query("pageSize") int pageSize,
                               @Query("apiKey") String apiKey);

    @GET("everything")
    Call<JsonResult> getFromSource(
                               @Query("domains") String domain,
                              // @Query("pageSize") int pageSize,
                               @Query("apiKey") String apiKey);

    @GET("sources")
    Call<SourceJsonResult> getAllSources(
            @Query("apiKey") String apiKey);
}
