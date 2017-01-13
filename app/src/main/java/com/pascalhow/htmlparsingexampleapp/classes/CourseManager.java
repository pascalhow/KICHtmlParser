package com.pascalhow.htmlparsingexampleapp.classes;

import com.pascalhow.htmlparsingexampleapp.model.Course;
import com.pascalhow.htmlparsingexampleapp.model.PerformanceCriteria;
import com.pascalhow.htmlparsingexampleapp.model.Unit;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by pascal on 10/01/2017.
 */

public class CourseManager {
    private List<Course> courseList = new ArrayList<>();
    private List<Unit> unitList = new ArrayList<>();
    private List<PerformanceCriteria> performanceCriteriaList = new ArrayList<>();

    public Observable<List<Course>> getCourseObservable() {
        return Observable.create(
                new Observable.OnSubscribe<List<Course>>() {
                    @Override
                    public void call(Subscriber<? super List<Course>> sub) {

                        loadCourseMaterials();
                        sub.onNext(courseList);
                        sub.onCompleted();
                    }
                }
        );
    }

    public Observable<List<Unit>> getUnitObservable() {
        return Observable.create(
                new Observable.OnSubscribe<List<Unit>>() {
                    @Override
                    public void call(Subscriber<? super List<Unit>> sub) {

                        loadCourseMaterials();
                        sub.onNext(unitList);
                        sub.onCompleted();
                    }
                }
        );
    }

    public Observable<List<PerformanceCriteria>> getCriteriaObservable() {
        return Observable.create(
                new Observable.OnSubscribe<List<PerformanceCriteria>>() {
                    @Override
                    public void call(Subscriber<? super List<PerformanceCriteria>> sub) {

                        loadCourseMaterials();
                        sub.onNext(performanceCriteriaList);
                        sub.onCompleted();
                    }
                }
        );
    }

    public void loadCourseMaterials() {

        courseList = getDummyCourseList();
        unitList = getDummyUnitList();
        performanceCriteriaList = getDummyPerformanceCriteriaList();

//        String url = Constants.COURSE_URL;
        // Scan first page to get qualifications
//        courseList = HtmlParserManager.scanPageForCourses(url, "#resultsBodyQualification");
//
//        unitList = HtmlParserManager.scanPageForUnits(courseList.get(0).getLink(), "#tableUnits");
//
//        performanceCriteriaList = HtmlParserManager.scanPageForPerformanceCriteria(unitList.get(0).getLink());
//        for(Course course : courseList) {
//
//            // iterate through list to get the second pages,which list the units
//            unitList = HtmlParserManager.scanPageForUnits(course.getLink(),"#tableUnits");
//
//            for(Unit unit : unitList) {
//                // The last page where it displays the performance and criteria pages
//                ArrayList<PerformanceCriteria> performanceCriteriaList = HtmlParserManager.scanPageForPerformanceCriteria(unit.getLink());
//                Log.d(TAG, performanceCriteriaList.toString());
//            }
//        }
    }

    /**
     * @return Dummy Course List
     */
    private ArrayList<Course> getDummyCourseList() {

        ArrayList<Course> dummyCourseList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            dummyCourseList.add(new Course.Builder().setCode("Course Code " + String.valueOf((i + 1)))
                    .setTitle("Course Name " + String.valueOf((i + 1)))
                    .build());
        }

        return dummyCourseList;
    }

    /**
     * @return Dummy Unit List
     */
    private ArrayList<Unit> getDummyUnitList() {

        ArrayList<Unit> dummyUnitList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            dummyUnitList.add(new Unit.Builder().setCode("Unit Code " + String.valueOf((i + 1)))
                    .setTitle("Unit Name " + String.valueOf((i + 1)))
                    .build());
        }

        return dummyUnitList;
    }

    /**
     * @return Dummy Performance PerformanceCriteria List
     */
    private ArrayList<PerformanceCriteria> getDummyPerformanceCriteriaList() {

//        ArrayList<PerformanceCriteria> dummyPerformanceCriteriaList = new ArrayList<>();
        ArrayList<PerformanceCriteria> performanceCriteriaList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {

            ArrayList<String> performanceList = new ArrayList<>();

            for (int j = 0; j < 3; j++) {
                performanceList.add("Performance " + String.valueOf((j + 1)));
            }

            performanceCriteriaList.add(new PerformanceCriteria.Builder().setElement("Element " + String.valueOf((i + 1)))
                    .setPerformanceList(performanceList)
                    .build());

//            dummyPerformanceCriteriaList.add(new PerformanceCriteria.Builder().setCriteriaList(performanceCriteriaList.get(i)).build());
        }
        return performanceCriteriaList;
    }
}
