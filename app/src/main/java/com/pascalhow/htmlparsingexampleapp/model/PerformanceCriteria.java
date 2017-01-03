package com.pascalhow.htmlparsingexampleapp.model;

import java.util.ArrayList;

/**
 * Created by pascal on 03/01/2017.
 */

public class PerformanceCriteria {

    private final ArrayList<Criteria> criterias;

    public static class Builder {

        private ArrayList<Criteria> criterias = new ArrayList<>();

        public PerformanceCriteria.Builder addCriteria(Criteria criteria) {
            this.criterias.add(criteria);
            return this;
        }

        public PerformanceCriteria build() {
            return new PerformanceCriteria(this);
        }
    }

    private PerformanceCriteria(PerformanceCriteria.Builder builder) {
        this.criterias = builder.criterias;
    }

    @Override
    public String toString() {
        String unitsToString = "----- Performance criteria -----\n";
        for (Criteria crit : criterias) {
            unitsToString += crit.getElement();
            unitsToString +=  "\n";
        }
        return unitsToString;
    }
}