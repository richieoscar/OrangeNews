package com.richieoscar.orangenews.repository;

import com.richieoscar.orangenews.api.ApiClient;
import com.richieoscar.orangenews.api.NewsApi;
import com.richieoscar.orangenews.model.JsonResult;
import com.richieoscar.orangenews.utils.AppUtils;

import retrofit2.Call;

public class DataRepository {
    /*
    TODO
    CREATE A NEW BRANCH TO MAKE MODIFICATION
    1.Move all Mutablelive data to viewmodel
    2. Make fetch methods return call and do the enqueue in viewmodel
    3. Use a backward compatible api for the date
    4. Use Pagination from the api instead of page size
     */
    private static final String API_KEY = "7c1cc89351ca4ed8bd5abc6e7d26f63a";
    private static final String TAG = "DataRepository";
    private static final int PAGE_SIZE = 50;
    private String from = AppUtils.fromDate();
    private String to = AppUtils.toDate();
    private String query;

    private String filter = "publishedAt";


    public void setFilter(String filter){
        this.filter = filter;
    }
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }


    public Call<JsonResult> fetchHeadlineNews() {
            NewsApi connect = ApiClient.getApiInstance().create(NewsApi.class);
            Call<JsonResult> call = connect.getHeadlines("ng", "general", PAGE_SIZE, API_KEY);
            return call;

    }

    public Call<JsonResult> fetchLatestNews() {
            NewsApi connect = ApiClient.getApiInstance().create(NewsApi.class);
            Call<JsonResult> call = connect.getResults("general", "popularity", from, to, PAGE_SIZE, API_KEY);
       return call;
    }

    public Call<JsonResult> fetchLocalSportsNews() {
            NewsApi connect = ApiClient.getApiInstance().create(NewsApi.class);
            Call<JsonResult> call = connect.getSportsNews("ng", "sports", null, PAGE_SIZE, API_KEY);
           return call;
    }

    public Call<JsonResult> fetchUkSportsNews() {
            NewsApi connect = ApiClient.getApiInstance().create(NewsApi.class);
            Call<JsonResult> call = connect.getSportsNews("gb", "sports", null,  PAGE_SIZE, API_KEY);
            return call;
    }

    public Call<JsonResult> fetchUSSportsNews() {

            NewsApi connect = ApiClient.getApiInstance().create(NewsApi.class);
            Call<JsonResult> call = connect.getSportsNews("us", "sports",null, PAGE_SIZE, API_KEY);
            return call;
    }

    public Call<JsonResult> fetchEntertainmentNews() {

            NewsApi connect = ApiClient.getApiInstance().create(NewsApi.class);
            Call<JsonResult> call = connect.getEntertainmentNews("us", "entertainment", PAGE_SIZE, API_KEY);
           return call;
    }

    public Call<JsonResult> fetchTechNews() {

            NewsApi connect = ApiClient.getApiInstance().create(NewsApi.class);
            Call<JsonResult> call = connect.getTechNews("us", "technology", PAGE_SIZE, API_KEY);
            return call;
    }

    public Call<JsonResult> fetchBusinessNews() {

            NewsApi connect = ApiClient.getApiInstance().create(NewsApi.class);
            Call<JsonResult> call = connect.getBusinessNews("us", "business", PAGE_SIZE, API_KEY);
            return call;
    }

    public Call<JsonResult> searchNews() {
            NewsApi connect = ApiClient.getApiInstance().create(NewsApi.class);
            Call<JsonResult> call = connect.getSearch(getQuery(), filter, PAGE_SIZE, API_KEY);
           return call;
    }

}
