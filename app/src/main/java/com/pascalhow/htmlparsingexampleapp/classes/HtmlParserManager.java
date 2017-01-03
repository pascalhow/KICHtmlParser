package com.pascalhow.htmlparsingexampleapp.classes;

import android.util.Log;

import com.pascalhow.htmlparsingexampleapp.model.Course;
import com.pascalhow.htmlparsingexampleapp.model.Criteria;
import com.pascalhow.htmlparsingexampleapp.model.PerformanceCriteria;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by pascal on 03/01/2017.
 */

public class HtmlParserManager {

    private static final String TAG = "htmp_parser_manager";

    public static ArrayList<Course> scanPageForCourses(String url, String table) {
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Chrome")
                    .get();

            // only get from the resultant table, with ID resultsBodyQualification, and in the body element, ignore header and footer
            Elements paragraphs = doc.select(table + " tbody tr");

            ArrayList<Course> courseList = buildCourseList(paragraphs);

            return courseList;
        }
        catch(IOException error) {
            // If it fails it would throw an error and return an empty array
            Log.d(TAG, "failed to scan page");
            return new ArrayList<Course>();
        }
    }

    public static ArrayList<Course> buildCourseList(Elements rows) {

        ArrayList<Course> courseList = new ArrayList<>();

        // Build the court list based on the tables
        for (Element r : rows) {
            Element firstCol = r.child(0);
            Element secondCol = r.child(1);
            String code = firstCol.child(0).ownText();
            String link = firstCol.child(0).attr("href").toString();
            String title = secondCol.ownText();

            Course course = new Course.Builder()
                    .setCode(code)
                    .setTitle(title)
                    .setLink(link)
                    .build();

            //  We now have both code and title for a given course so add to the list
            courseList.add(course);

        }

        return courseList;
    }

    public static ArrayList<PerformanceCriteria> scanPageForPerformanceCriteria(String url) {
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Chrome")
                    .get();


            Elements tables = doc.select(".ait-table tbody");
            ArrayList<PerformanceCriteria> criteriasList = new ArrayList<PerformanceCriteria>();

            for (Element table : tables) {
                // iterating through each table to find the performance criteria table.
                // the only way is to check the table
                // the first child returns the row
                Element rows = table.child(0);
                // Get the first column in the row, by getting the child of the row
                Element firstCol = rows.child(0);

                // Remove everything except for alphabets. It has a space, that needs to be removed, to compare the words
                String firstColString = firstCol.text().replaceAll("[^A-Za-z]+", "");
                // Get the content of the first column, if it matches element, means it the correct table
                if(firstColString.equalsIgnoreCase("element")) {
                    // Get the second column in the row
                    String secondColString = rows.child(1).text().replaceAll("[^A-Za-z]+", "");
                    if(secondColString.equalsIgnoreCase("PERFORMANCECRITERIA")) {
                        criteriasList = buildPerformanceCriteriaList(table);
                    }

                }

            }



            return criteriasList;
        }
        catch(IOException error) {
            Log.d(TAG, "failed to scan page");
            return new ArrayList<PerformanceCriteria>();
        }
    }

    public static ArrayList<PerformanceCriteria> buildPerformanceCriteriaList(Element table) {

        ArrayList<PerformanceCriteria> elementsCrtieriasList = new ArrayList<>();

        Elements rows = table.children();
        for (int i = 1; i < rows.size(); i ++) {
            // get first column's text
//            rows.get(i).child(0)
            Elements secondColumnArray = rows.get(i).child(1).children();
            ArrayList<String> performances = new ArrayList<String>();
            for (Element item: secondColumnArray) {
                performances.add(item.text());
            }
            Criteria criteria = new Criteria(rows.get(i).child(0).text(), performances);

            PerformanceCriteria performanceCriteria = new PerformanceCriteria.Builder()
                    .addCriteria(criteria)
                    .build();

            System.out.print(criteria.toString());
            //  We now have both code and title for a given course so add to the list
            elementsCrtieriasList.add(performanceCriteria);
        }


        return elementsCrtieriasList;
    }
}
