package com.pascalhow.htmlparsingexampleapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pascal on 03/01/2017.
 */

public class PerformanceCriteria {

    private final List<Criteria> criteriaList;

    public static class Builder {

        private ArrayList<Criteria> criteriaList = new ArrayList<>();

        public Builder setCriteriaList(Criteria criteria) {
            this.criteriaList.add(criteria);
            return this;
        }

        public PerformanceCriteria build() {
            return new PerformanceCriteria(this);
        }
    }

    private PerformanceCriteria(Builder builder) {
        this.criteriaList = builder.criteriaList;
    }

    public List<Criteria> getCriteriaList() {
        return this.criteriaList;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("----- Performance criteriaList -----\n");

        for(Criteria crit : criteriaList) {
            sb.append(crit.getElement());
            sb.append("\n");
        }
        return sb.toString();
    }
}