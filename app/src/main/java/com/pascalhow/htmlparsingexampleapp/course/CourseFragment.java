package com.pascalhow.htmlparsingexampleapp.course;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.pascalhow.htmlparsingexampleapp.R;
import com.pascalhow.htmlparsingexampleapp.R2;
import com.pascalhow.htmlparsingexampleapp.adapter.CourseItemAdapter;
import com.pascalhow.htmlparsingexampleapp.classes.CourseManager;
import com.pascalhow.htmlparsingexampleapp.model.Course;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by pascal on 25/12/2016.
 */
public class CourseFragment extends Fragment {

    @BindView(R2.id.course_list)
    RecyclerView recyclerView;

    @BindView(R2.id.course_progressbar)
    ProgressBar progressBar;

    private CourseItemAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    private CourseManager courseManager = new CourseManager();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_course, container, false);

        ButterKnife.bind(this, rootView);

        getActivity().setTitle(R.string.course_fragment_title);

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

        progressBar.setVisibility(View.VISIBLE);

        courseManager.getCourseObservable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getCourseSubscriber());
    }

    @Override
    public void onResume() {
        getActivity().setTitle(R.string.course_fragment_title);
        super.onResume();
    }

    public Subscriber<List<Course>> getCourseSubscriber() {
        return new Subscriber<List<Course>>() {

            @Override
            public void onCompleted() {
                Timber.d("onCompleted");
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable e) {
                Timber.e("onError");
            }

            @Override
            public void onNext(List<Course> courseList) {
                Timber.d("onNext");
                mAdapter.setItemList(courseList);
            }
        };
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
