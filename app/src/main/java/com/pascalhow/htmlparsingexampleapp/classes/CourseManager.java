package com.pascalhow.htmlparsingexampleapp.classes;

import com.pascalhow.htmlparsingexampleapp.model.Course;
import com.pascalhow.htmlparsingexampleapp.model.Criteria;
import com.pascalhow.htmlparsingexampleapp.model.Unit;
import com.pascalhow.htmlparsingexampleapp.utils.Constants;

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
    private List<Criteria> criteriaList = new ArrayList<>();

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

    public Observable<List<Criteria>> getCriteriaObservable() {
        return Observable.create(
                new Observable.OnSubscribe<List<Criteria>>() {
                    @Override
                    public void call(Subscriber<? super List<Criteria>> sub) {

                        loadCourseMaterials();
                        sub.onNext(criteriaList);
                        sub.onCompleted();
                    }
                }
        );
    }

    public void loadCourseMaterials() {
        String url = Constants.COURSE_URL;
        // Scan first page to get qualifications
        courseList = HtmlParserManager.scanPageForCourses(url, "#resultsBodyQualification");

        unitList = HtmlParserManager.scanPageForUnits(courseList.get(0).getLink(),"#tableUnits");
//        for(Course course : courseList) {
//
//            // iterate through list to get the second pages,which list the units
//            unitList = HtmlParserManager.scanPageForUnits(course.getLink(),"#tableUnits");
//
//            for(Unit unit : unitList) {
//                // The last page where it displays the performance and criteria pages
//                ArrayList<PerformanceCriteria> criteriaList = HtmlParserManager.scanPageForPerformanceCriteria(unit.getLink());
//                Log.d(TAG, criteriaList.toString());
//            }
//        }
    }
}
