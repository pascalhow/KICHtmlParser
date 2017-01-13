package com.pascalhow.htmlparsingexampleapp.classes;

import com.pascalhow.htmlparsingexampleapp.model.Course;
import com.pascalhow.htmlparsingexampleapp.model.Criteria;
import com.pascalhow.htmlparsingexampleapp.model.PerformanceCriteria;
import com.pascalhow.htmlparsingexampleapp.model.Unit;
import com.pascalhow.htmlparsingexampleapp.utils.StringHelper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import timber.log.Timber;

/**
 * Created by pascal on 03/01/2017.
 */

public class HtmlParserManager {

    private static final String TAG = "html_parser_manager";

    public static ArrayList<Course> scanPageForCourses(String url, String table) {
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Chrome")
                    .get();

            // only get from the resultant table, with ID resultsBodyQualification, and in the body element, ignore header and footer
            Elements paragraphs = doc.select(table + " tbody tr");

            return buildCourseList(paragraphs);
        }
        catch(IOException error) {
            // If it fails it would throw an error and return an empty array
             Timber.d("failed to scan page");
            return new ArrayList<>();
        }
    }

    private static ArrayList<Course> buildCourseList(Elements rows) {

        ArrayList<Course> courseList = new ArrayList<>();

        // Build the court list based on the tables
        for (Element r : rows) {
            Element firstCol = r.child(0);
            Element secondCol = r.child(1);
            String code = firstCol.child(0).ownText();
            String link = firstCol.child(0).attr("href");
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

    public static ArrayList<Unit> scanPageForUnits(String url, String table) {
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Chrome")
                    .get();

            // only get from the resultant table, with ID resultsBodyQualification, and in the body element, ignore header and footer
            Elements paragraphs = doc.select(table + " tbody tr");

            return buildUnitList(paragraphs);
        }
        catch(IOException error) {
            // If it fails it would throw an error and return an empty array
             Timber.d("failed to scan page");
            return new ArrayList<>();
        }
    }

    private static ArrayList<Unit> buildUnitList(Elements rows) {

        ArrayList<Unit> unitList = new ArrayList<>();

        // Build the court list based on the tables
        for (Element r : rows) {
            Element firstCol = r.child(0);
            Element secondCol = r.child(1);
            String code = firstCol.child(0).ownText();
            String link = firstCol.child(0).attr("href");
            String title = secondCol.ownText();

            Unit unit = new Unit.Builder()
                    .setCode(code)
                    .setTitle(title)
                    .setLink(link)
                    .build();

            //  We now have both code and title for a given unit so add to the list
            unitList.add(unit);
        }

        return unitList;
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
                    break;
                }

            }



            return criteriasList;
        }
        catch(IOException error) {
             Timber.d("failed to scan page");
            return new ArrayList<>();
        }
    }

    private static ArrayList<PerformanceCriteria> buildPerformanceCriteriaList(Element table) {

        ArrayList<PerformanceCriteria> elementsCriteriaList = new ArrayList<>();

        Elements rows = table.children();
        for (int i = 1; i < rows.size(); i ++) {

            Elements secondColumnArray = rows.get(i).child(1).children();
            ArrayList<String> performances = new ArrayList<>();
            for (Element item: secondColumnArray) {
                performances.add(item.text());
            }
            Criteria criteria = new Criteria(rows.get(i).child(0).text(), performances);

            PerformanceCriteria performanceCriteria = new PerformanceCriteria.Builder()
                    .setCriteriaList(criteria)
                    .build();

            Timber.d(criteria.toString());

            if (isValid(criteria)){
                elementsCriteriaList.add(performanceCriteria);
            }
        }

        return elementsCriteriaList;
    }

    /**
     * If criteria does not contain not null or empty string
     * Then check if first character of element can be converted to integer
     * @param criteria Criteria item from performance and criteria table
     * @return true if criteria is valid
     */
    private static boolean isValid(Criteria criteria) {

        if (!StringHelper.isNullOrEmpty(criteria.getElement()) || !StringHelper.isNullOrEmpty(criteria.getPerformances().get(0))) {
            if (StringHelper.isInteger(criteria.getElement().substring(0, 1))) {
                return true;
            }
        }
        return false;
    }
}
