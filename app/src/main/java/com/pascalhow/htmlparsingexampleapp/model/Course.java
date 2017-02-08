package com.pascalhow.htmlparsingexampleapp.model;

import com.pascalhow.htmlparsingexampleapp.utils.Constants;

import java.util.Collections;
import java.util.List;

/**
 * Created by pascal on 02/01/2017.
 */

public class Course {

    private final String code;
    private final String title;
    private final String link;
    private List<Unit> unitList;

    public static class Builder {

        private String code = "";
        private String title = "";
        private String link = "";
        private List<Unit> unitList;

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

        public Builder setUnitList(List<Unit> unitList) {
            this.unitList = unitList;
            return this;
        }

        public Course build() {
            return new Course(this);
        }
    }

    private Course(Builder builder) {
        this.code = builder.code;
        this.title = builder.title;
        this.link = builder.link;
        this.unitList = builder.unitList;
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

    public List<Unit> getUnitList() {
        return (this.unitList.isEmpty() ? Collections.emptyList() : this.unitList);
    }

    public void addUnitList(List<Unit> unitList) {
        this.unitList = unitList;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("----- Course -----\n");
        sb.append("Code: " + this.code + "\n");
        sb.append("Title: " + this.title + "\n");
        sb.append("Link: " + this.link + "\n");

        return sb.toString();
    }
}
