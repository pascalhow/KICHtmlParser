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
import android.widget.TextView;

import com.pascalhow.htmlparsingexampleapp.MainActivity;
import com.pascalhow.htmlparsingexampleapp.R;
import com.pascalhow.htmlparsingexampleapp.R2;
import com.pascalhow.htmlparsingexampleapp.adapter.CourseItemAdapter;
import com.pascalhow.htmlparsingexampleapp.model.Course;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pascal on 25/12/2016.
 */
public class CourseFragment extends Fragment {

    @BindView(R2.id.course_list)
    RecyclerView recyclerView;

    private MainActivity mainActivity;
    private CourseItemAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

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

        ArrayList<Course> courselist = new ArrayList<>();

        courselist.add(new Course.Builder().setCode("001")
                .setTitle("Course 1")
                .build());

        courselist.add(new Course.Builder().setCode("002")
                .setTitle("Course 2")
                .build());

        courselist.add(new Course.Builder().setCode("003")
                .setTitle("Course 3")
                .build());

        courselist.add(new Course.Builder().setCode("004")
                .setTitle("Course 4")
                .build());

        courselist.add(new Course.Builder().setCode("005")
                .setTitle("Course 5")
                .build());

        courselist.add(new Course.Builder().setCode("006")
                .setTitle("Course 6")
                .build());

        courselist.add(new Course.Builder().setCode("007")
                .setTitle("Course 7")
                .build());

        courselist.add(new Course.Builder().setCode("008")
                .setTitle("Course 8")
                .build());

        courselist.add(new Course.Builder().setCode("009")
                .setTitle("Course 9")
                .build());

        courselist.add(new Course.Builder().setCode("010")
                .setTitle("Course 10")
                .build());

        courselist.add(new Course.Builder().setCode("011")
                .setTitle("Course 11")
                .build());

        courselist.add(new Course.Builder().setCode("012")
                .setTitle("Course 12")
                .build());

        courselist.add(new Course.Builder().setCode("013")
                .setTitle("Course 13")
                .build());

        courselist.add(new Course.Builder().setCode("014")
                .setTitle("Course 14")
                .build());

        mAdapter.setItemList(courselist);
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
