package com.pascalhow.htmlparsingexampleapp.model;

import java.util.Collections;
import java.util.List;

/**
 * Created by pascal on 03/01/2017.
 */

public class PerformanceCriteria {

    private String element;
    private List<String> performanceList;

    public static class Builder {

        private String element = "";
        private List<String> performanceList;

        public Builder setElement(String element) {
            this.element = element;
            return this;
        }

        public Builder setPerformanceList(List<String> performanceList) {
            this.performanceList = performanceList;
            return  this;
        }

        public PerformanceCriteria build() {
            return new PerformanceCriteria(this.element, this.performanceList);
        }
    }

    private PerformanceCriteria(String element, List<String> performances) {
        this.element = element;
        this.performanceList = performances;
    }

    public String getElement() {
        return this.element;
    }

    public List<String> getPerformanceList() {

        return (!this.performanceList.isEmpty()) ? this.performanceList : Collections.emptyList();
    }

    public String getPerformanceListString() {
        if (!performanceList.isEmpty() || performanceList != null) {
            StringBuilder sb = new StringBuilder();

            for (String s : performanceList) {
                sb.append(s + "\n");
            }
            return sb.toString().substring(0, sb.toString().length() - 1);
        } else {
            return "";
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("----- Performance criteriaList -----\n");
        sb.append("Element: ");
        sb.append(element + "\n");

        for(String performances : performanceList) {
            sb.append(performances + "\n");
        }
        return sb.toString();
    }
}