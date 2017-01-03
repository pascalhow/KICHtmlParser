package com.pascalhow.htmlparsingexampleapp.course;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.pascalhow.htmlparsingexampleapp.MainActivity;
import com.pascalhow.htmlparsingexampleapp.R;
import com.pascalhow.htmlparsingexampleapp.R2;
import com.pascalhow.htmlparsingexampleapp.adapter.CourseItemAdapter;
import com.pascalhow.htmlparsingexampleapp.classes.HtmlParserManager;
import com.pascalhow.htmlparsingexampleapp.model.Course;
import com.pascalhow.htmlparsingexampleapp.model.PerformanceCriteria;
import com.pascalhow.htmlparsingexampleapp.utils.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by pascal on 25/12/2016.
 */
public class CourseFragment extends Fragment {

    @BindView(R2.id.course_list)
    RecyclerView recyclerView;

    private MainActivity mainActivity;
    private CourseItemAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    private static final String TAG = "CourseFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_course, container, false);

        ButterKnife.bind(this, rootView);

        mainActivity = (MainActivity) getActivity();
        mainActivity.setTitle(R.string.course_fragment_title);

        mLayoutManager = new LinearLayoutManager(getActivity());

        mAdapter = new CourseItemAdapter(getContext());

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(mLayoutManager);

        setHasOptionsMenu(true);

        return rootView;
    }

    @OnClick(R2.id.load_course_btn)
    public void onLoadButtonClick() {
        loadDummyCourse();
    }

    private void loadDummyCourse() {

        sampleObservable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sampleSubscriber());
    }

    public Observable<ArrayList<Course>> sampleObservable() {

        return Observable.create(
                new Observable.OnSubscribe<ArrayList<Course>>() {
                    @Override
                    public void call(Subscriber<? super ArrayList<Course>> sub) {

                        loadCriteria();

                        ArrayList<Course> courseList = new ArrayList<>();

                        for (int i = 0; i < 20; i++) {
                            courseList.add(new Course.Builder().setCode(String.valueOf(i))
                                    .setTitle("Course " + String.valueOf(i))
                                    .build());
                        }

                        sub.onNext(courseList);
                        sub.onCompleted();
                    }
                }
        );
    }

    public Subscriber<ArrayList<Course>> sampleSubscriber() {
        return new Subscriber<ArrayList<Course>>() {

            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError");
            }

            @Override
            public void onNext(ArrayList<Course> courseList) {
                Log.d(TAG, "onNext");

                mAdapter.setItemList(courseList);
            }
        };
    }

    public void loadCriteria() {
        String url = Constants.COURSE_URL;
        // Scan first page to get qualifications
        ArrayList<Course> qualificationList = HtmlParserManager.scanPageForCourses(url, "#resultsBodyQualification");

        for(Course course : qualificationList) {
            // iterate through list to get the second pages,which list the units
            ArrayList<Course> unitList = HtmlParserManager.scanPageForCourses(course.getLink(),"#tableUnits");
            for(Course unit : unitList) {
                // The last page where it dispalys the performance and criteria pages
                ArrayList<PerformanceCriteria> criterasList = HtmlParserManager.scanPageForPerformanceCriteria(unit.getLink());
                Log.d(TAG, criterasList.toString());
            }
        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_settings);
        item.setVisible(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_settings:
                //  Save new trip
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
