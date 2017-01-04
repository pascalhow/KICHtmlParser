package com.pascalhow.htmlparsingexampleapp.model;

import java.util.ArrayList;

/**
 * Created by pascal on 03/01/2017.
 */

public class PerformanceCriteria {

    private final ArrayList<Criteria> criteria;

    public static class Builder {

        private ArrayList<Criteria> criteria = new ArrayList<>();

        public Builder addCriteria(Criteria criteria) {
            this.criteria.add(criteria);
            return this;
        }

        public PerformanceCriteria build() {
            return new PerformanceCriteria(this);
        }
    }

    private PerformanceCriteria(Builder builder) {
        this.criteria = builder.criteria;
    }

    @Override
    public String toString() {
        String performanceCriteriaToString = "----- Performance criteria -----\n";
        for (Criteria crit : criteria) {
            performanceCriteriaToString += crit.getElement();
            performanceCriteriaToString +=  "\n";
        }
        return performanceCriteriaToString;
    }
}