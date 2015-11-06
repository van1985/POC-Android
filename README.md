# POC-Android  [Under development]

Simple android example to show android and libraries capabilities.

## Getting Started  

### Libraries Used  
android.support.design: `com.android.support:design:22.2.0+`  
appcompat: `com.android.support:appcompat-v7:22.1.0+`  
gson: `com.google.code.gson:gson:2.3`  
retrofit: `com.squareup.retrofit:retrofit:2.0.0-beta2`  
converter-gson: `com.squareup.retrofit:converter-gson:2.0.0-beta2`  
okhttp: `com.squareup.okhttp:okhttp:2.4.0`  
realm.io `io.realm:realm-android:0.73.1`

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

For this case, I create a clienta that manage all the calls that the app make to the api. This adapter was made with OkHttp library.

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


#### Realm.io

Realm is a mobile database that runs directly inside phones, tablets or wearables. This repository holds the source code for the Java version of Realm, which currently runs only on Android.

For this library , I created a RealmObject that represent the object in the database. In this case a User object:

```java
public class User extends RealmObject {

    private String name;
    private int age;

    @Ignore
    private int sessionId;

    // Standard getters & setters generated by your IDE…
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public int getSessionId() { return sessionId; }
    public void setSessionId(int sessionId) { this.sessionId = sessionId; }
}
```

For manage the data, I created a StorageService that contains a **Realm** object for manage the context. With this variable we can make queries like save, get, commit transactions among others. For example:

**Return a specific user by name**
```java
RealmQuery<User> query = context.where(User.class)
                .equalTo("name",name);

        return  query.findFirst();
```  

**Save an user**
```java
context.beginTransaction();
        User user = context.createObject(User.class);
        user.setName(name);
        user.setAge(age);
        user.setSessionId(sessionID);
        context.commitTransaction();
```


## Android Project Structure

###The Team

Android Projects have a few key elements and each has a role to play:   

Java: The Professional  
Resources: The Artist  
AndroidManifest.xml: The Boss  
Intent: The Job itself 

### Code Structure Folder

**activities**  
Contains all the activities and fragments. Classes are all named with Activity or Fragment at the end. That way, you can immediately know what it is when reading Java code that doesn't have its full package name.  

**api**  
Contains all classes related to syncing. I use a RestClient class to pull data from an HTTP API with OkHttp library. In this package, also are included the API definitions.

**data**  
Contains all classes related to data management and models for persistence. A StorageService class manage all the transactions.

**helpers**  
Contains helper classes and constants. A helper class is a place to put code that is used in more than one place. Most of the methods are static.

**models**  
Contains all local models. When syncing from an HTTP API I parse the JSON into these Java objects using GSON and implementing Parcelable interface.

**receivers**  
Contains all BroadcastReceiver definition. This classes will catch the response from IntentService that run in background.

**services**  
Contains all IntentService definition.
Contains Intent and IntentService 

## Material Design

For material design, we used the following libraries:  

android.support.design: `com.android.support:design:22.2.0+`  
appcompat: `com.android.support:appcompat-v7:22.1.0+`  

###Login
<img src="https://github.com/van1985/POC-Android/blob/master/img/login-img1.png" width="300px" height="600px"/>
<img src="https://github.com/van1985/POC-Android/blob/master/img/login-img2.png" width="300px" height="600px"/>


## Fabric

### Crashlytics
On the Android side, we analyze your crashes and automatically deobfuscates stack traces, beginning with on-device exception handling. Once the crash report reaches our system, we process the stack frames against your application’s mapping file that was automatically uploaded to our servers at build time.

More info: https://fabric.io/kits/android/crashlytics/summary

## card.io SDK for Android
card.io provides fast, easy credit card scanning in mobile apps.

####Add in build.gradle
 ```
 compile 'io.card:android-sdk:5.1.2'
 ```
 
####Add in AndroidManifest
 ```
     <!-- Permission to use camera - required -->
     <uses-permission android:name="android.permission.CAMERA" />
 
     <!-- Camera features - recommended -->
     <uses-feature android:name="android.hardware.camera" android:required="false" />
     <uses-feature android:name="android.hardware.camera.autofocus" android:required="false" />
     <uses-feature android:name="android.hardware.camera.flash" android:required="false" />
 ```
 
### Sample code

First, we'll assume that you're going to launch the scanner from a button called ```onScanPress(Vie view)``` 
Then, add the method as:
```java 
    private void onScanPress(View v) {
                Intent scanIntent = new Intent(getActivity(), CardIOActivity.class);
                scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true); // default: false
                scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false); // default: false
                scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false); // default: false
                // hides the manual entry button
                // if set, developers should provide their own manual entry mechanism in the app
                scanIntent.putExtra(CardIOActivity.EXTRA_SUPPRESS_MANUAL_ENTRY, false); // default: false
                scanIntent.putExtra(CardIOActivity.EXTRA_HIDE_CARDIO_LOGO, true);
                // matches the theme of your application
                scanIntent.putExtra(CardIOActivity.EXTRA_KEEP_APPLICATION_THEME, false); // default: false
        
                // MY_SCAN_REQUEST_CODE is arbitrary and is only used within this activity.
                startActivityForResult(scanIntent, MY_SCAN_REQUEST_CODE);
    }
``` 

Next, we'll override ```onActivityResult()``` to get the scan result.

```java
 @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String resultStr;

        if(data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
            CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);

            resultStr = "Card Number: " + scanResult.getRedactedCardNumber();
        } else {
            resultStr = getResources().getString(R.string.scan_result_cancel_text);
        }

        mTextView.setText(resultStr);
    }
```
 
>>>>>>> 1b2f404851f4a7f86511fa8eed370f39964dce37
