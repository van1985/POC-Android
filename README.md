# POC-Android

Simple android example to show android and libraries capabilities.

## Getting Started  

### Libraries Used
appcompat: `com.android.support:appcompat-v7:22.1.0+`  
gson: `com.google.code.gson:gson:2.3`  
retrofit: `com.squareup.retrofit:retrofit:2.0.0-beta2`  
converter-gson: `com.squareup.retrofit:converter-gson:2.0.0-beta2`  
okhttp: `com.squareup.okhttp:okhttp:2.4.0`  

*We used Android Studio for develop this POC.*

#### Retrofit

Retrofit turns your HTTP API into a Java interface. See the following link for more information: http://square.github.io/retrofit/  

For this POC, we used 2.0 version that is a little different from 1.9. I ended using Retrofit in sync mode and execute the sync calls on an IntentService.

**`Sync Call`**  
```java
        WeatherAPI service = RestClient.getClient();
        Call<WeatherData> call = service.getWeatherFromApiSync("London", appId);
        try {
            Response<WeatherData> response=call.execute();
            BroadcastResult(response.body());
        } catch (IOException e) {
            e.printStackTrace();
        }
```

**`Async Call (Not used in the POC)`** 
```java
        WeatherAPI service = RestClient.getClient();
        Call<WeatherData> call = service.getWeatherFromApiSync("London", appId);
        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Response<WeatherData> response, Retrofit retrofit) {
                Log.d("MainActivity", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    WeatherData result = response.body();
                    String resultJSON = new Gson().toJson(result);
                    Log.d("MainActivity", "response = " + resultJSON);
                    BroadcastResult(resultJSON);
                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors

                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("Error: " + t.getMessage().toString());
            }
        });
```

#### OkHttp

OkHttp and Retrofit - which are projects from Square - can work together. OkHttp handles the lower-level HTTP connection details, while Retrofit simplifies using REST APIs. Retrofit can be used on top of OkHttp, but it is not required. For more details about OkHttp, follow this link: http://square.github.io/okhttp/

For this case, I create an adapter that manage all the calls to the api. This adapter was made with OkHttp library.

```java
 OkHttpClient okClient = new OkHttpClient();
            okClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    return response;
                }
            });
```

OkHttp have a lot of features like: create interceptors and recipes, manage different types of connections (URLs, Addresses, Connections) among other things. For more information, take a look to the wiki.

OkHttp Wiki: https://github.com/square/okhttp/wiki
