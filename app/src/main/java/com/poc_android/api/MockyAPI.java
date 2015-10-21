package com.poc_android.api;

import com.poc_android.models.UserData;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by vanden on 10/19/15.
 */
public interface MockyAPI {

    /*
        Mock response:
        {
            "username": "jhon.doe",
            "userID": "20678123",
            "name": "Jhon",
            "lastName": "Doe"
        }
        URL Mocky: http://www.mocky.io/v2/5627918c2700009e1d6eec44
     */
    @GET("/v2/5627918c2700009e1d6eec44")
    Call<UserData> LoginApiSync(
            //@Query("username") String username,
            //@Query("password") String password
    );
}
