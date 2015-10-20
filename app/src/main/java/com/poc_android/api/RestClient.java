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

/**
 * Created by vanden on 10/19/15.
 */

public class RestClient {

    private static WeatherAPI weatherApiInterface ;
    private static String baseUrl = "http://api.openweathermap.org" ;

    public static WeatherAPI getClient() {
        if (weatherApiInterface == null) {

            OkHttpClient okClient = new OkHttpClient();
            okClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    return response;
                }
            });

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                            .client(okClient)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
            weatherApiInterface = client.create(WeatherAPI.class);
        }
        return weatherApiInterface ;
    }
}

