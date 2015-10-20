package com.poc_android.data;

import com.poc_android.data.User;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by vanden on 10/20/15.
 */
public final class StorageService {

    private static Realm context;
    public static void setContext(Realm r){
        context = r;
    }

    public static void saveUser(String name, int age, int sessionID){
        // Transactions give you easy thread-safety
        context.beginTransaction();
        User user = context.createObject(User.class);
        user.setName(name);
        user.setAge(age);
        user.setSessionId(sessionID);
        context.commitTransaction();
    }

    public static User getUser(String name){
        RealmQuery<User> query = context.where(User.class)
                .equalTo("name",name);

        return  query.findFirst();
    }

}
