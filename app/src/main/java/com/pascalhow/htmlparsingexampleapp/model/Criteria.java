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
        return this.element;
    }

    public ArrayList<String> getPerformances() {
        return this.performances;
    }

    public String getPerformancesString() {
        if (!performances.isEmpty() || performances != null) {
            StringBuilder sb = new StringBuilder();

            for (String s : performances) {
                sb.append(s + "\n");
            }
            return sb.toString();
        } else {
            return "";
        }
    }
}