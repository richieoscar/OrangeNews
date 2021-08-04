package com.richieoscar.orangenews.repository;

import com.richieoscar.orangenews.api.ApiClient;
import com.richieoscar.orangenews.api.NewsApi;
import com.richieoscar.orangenews.model.SourceJsonResult;

import retrofit2.Call;

public class SourcesRepository {
    private static final String API_KEY = "11aa189af1c04dc5a5c9ee37aa43ef9f";



    public Call<SourceJsonResult> fetchAllSources() {
            NewsApi connect = ApiClient.getApiInstance().create(NewsApi.class);
            Call<SourceJsonResult> call = connect.getAllSources( API_KEY);
         return  call;
    }


}
