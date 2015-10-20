package com.poc_android.data;

import com.poc_android.data.User;

import io.realm.Realm;

/**
 * Created by vanden on 10/20/15.
 */
public class StorageService {

    private Realm context;
    public StorageService(Realm r){
        context = r;
    }

    public void saveUser(User user){
        // Transactions give you easy thread-safety
        context.beginTransaction();
        User userContext = context.createObject(User.class);
        user.setName("Leandro");
        context.commitTransaction();
    }

    public User getUser(){
        return  new User();
    }

}
