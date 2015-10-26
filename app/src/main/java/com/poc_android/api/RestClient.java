package com.poc_android.api;

/**
 * Created by vanden on 10/19/15.
 */

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.IOException;

import retrofit.Converter;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class RestClient {

    private static WeatherAPI weatherApiInterface ;
    private static MockyAPI mockyApiInterface ;
    private static String baseUrlWeatherAPI = "http://api.openweathermap.org";
    private static String baseUrlMockyAPI ="http://www.mocky.io";
    private static OkHttpClient okClient;

    private static void createOKClient(){

        //Set globant Proxy for make API calls inside Globant Company
        //TODO: Change the location of this code
        //System.setProperty("http.proxyHost", "proxy.corp.globant.com");
        //System.setProperty("http.proxyPort", "3128");

        okClient = new OkHttpClient();
        okClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                return response;
            }
        });
    }

    public static WeatherAPI getWeatherAPIClient() {
        if (okClient == null)
            createOKClient();
        if (weatherApiInterface == null) {
            Retrofit client = new Retrofit.Builder()
                    .baseUrl(baseUrlWeatherAPI)
                    .addConverterFactory(GsonConverterFactory.create())
                            .client(okClient)
                            .build();
            weatherApiInterface = client.create(WeatherAPI.class);
        }
        return weatherApiInterface ;
    }

    public static MockyAPI getMockyAPIClient() {
        if (okClient == null)
            createOKClient();
        if (mockyApiInterface == null) {
            Retrofit client = new Retrofit.Builder()
                    .baseUrl(baseUrlMockyAPI)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okClient)
                    .build();
            mockyApiInterface = client.create(MockyAPI.class);
        }
        return mockyApiInterface ;
    }
}

