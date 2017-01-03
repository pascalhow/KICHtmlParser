package com.pascalhow.htmlparsingexampleapp.model;

import java.util.ArrayList;

/**
 * Created by pascal on 03/01/2017.
 */

public class Criteria {

    private String element;
    private ArrayList<String> performances;

    public Criteria(String element, ArrayList<String> performances) {
        this.element = element;
        this.performances = performances;
    }

    public String getElement() {
        return this.element.toString();
    }
}