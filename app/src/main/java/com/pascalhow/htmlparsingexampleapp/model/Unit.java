package com.pascalhow.htmlparsingexampleapp.model;

import com.pascalhow.htmlparsingexampleapp.utils.Constants;

/**
 * Created by pascal on 04/01/2017.
 */

public class Unit {
    private final String code;
    private final String title;
    private final String link;

    public static class Builder {

        private String code = "";
        private String title = "";
        private String link = "";

        public Builder setCode(String code) {
            this.code = code;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setLink(String link) {
            this.link = link;
            return this;
        }

        public Unit build() {
            return new Unit(this);
        }
    }

    private Unit(Builder builder) {
        this.code = builder.code;
        this.title = builder.title;
        this.link = builder.link;
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }

    public String getLink() {
        // The url stored is without the base url, http:training.gov needs to be prepend
        // THe links are usually /training/details/....
        return Constants.BASE_URL + this.link;
    }
    @Override
    public String toString() {
        String unitToString = "----- Unit -----\n";
        unitToString += "Code: " + this.code + "\n";
        unitToString += "Title: " + this.title + "\n";
        unitToString += "Link: " + this.link + "\n";

        return unitToString;
    }
}