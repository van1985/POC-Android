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
