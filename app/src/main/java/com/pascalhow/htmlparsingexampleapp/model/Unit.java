package com.pascalhow.htmlparsingexampleapp.model;

import com.pascalhow.htmlparsingexampleapp.utils.Constants;

import java.util.Collections;
import java.util.List;

/**
 * Created by pascal on 04/01/2017.
 */

public class Unit {
    private final String code;
    private final String title;
    private final String link;
    private List<PerformanceCriteria> performanceCriteriaList;

    public static class Builder {

        private String code = "";
        private String title = "";
        private String link = "";
        private List<PerformanceCriteria> performanceCriteriaList;

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

        public Builder setPerformanceCriteriaList(List<PerformanceCriteria> performanceCriteriaList) {
            this.performanceCriteriaList = performanceCriteriaList;
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
        this.performanceCriteriaList = builder.performanceCriteriaList;
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

    public List<PerformanceCriteria> getPerformanceCriteriaList() {
        return (this.performanceCriteriaList.isEmpty()) ? Collections.emptyList() : this.performanceCriteriaList;
    }

    public void addPerformanceCriteriaList(List<PerformanceCriteria> performanceCriteriaList) {
        this.performanceCriteriaList = performanceCriteriaList;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("----- Unit -----\n");
        sb.append("Code: " + this.code + "\n");
        sb.append("Title: " + this.title + "\n");
        sb.append("Link: " + this.link + "\n");

        return sb.toString();
    }
}
