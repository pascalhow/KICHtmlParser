package com.pascalhow.htmlparsingexampleapp.model;

import io.realm.RealmObject;

/**
 * Created by pascal on 08/02/2017.
 */

public class Dog extends RealmObject {
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
