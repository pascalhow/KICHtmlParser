package com.pascalhow.htmlparsingexampleapp.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pascalhow.htmlparsingexampleapp.R;
import com.pascalhow.htmlparsingexampleapp.model.Course;
import com.pascalhow.htmlparsingexampleapp.unit.UnitFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pascal on 02/01/2017.
 */

public class CourseItemAdapter extends RecyclerView.Adapter<CourseItemAdapter.ViewHolder> {

    private List<Course> courseList;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        int itemType;
        TextView course_item_code;
        TextView course_item_title;

        public ViewHolder(final View itemView, int ViewType) {
            super(itemView);
            this.itemType = ViewType;
            course_item_code = (TextView) itemView.findViewById(R.id.course_code);
            course_item_title = (TextView) itemView.findViewById(R.id.course_title);
        }
    }

    public CourseItemAdapter(Context context) {
        this.courseList = new ArrayList<>();
        this.context = context;
    }

    public void setItemList(List<Course> courseList) {
        this.courseList = courseList;
        notifyDataSetChanged();
    }

    @Override
    public CourseItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_list_item, parent, false);
        return new ViewHolder(v, viewType);
    }

    @Override
    public void onBindViewHolder(final CourseItemAdapter.ViewHolder holder, final int position) {
        final Course course = courseList.get(position);
        holder.course_item_code.setText(course.getCode());
        holder.course_item_title.setText(course.getTitle());

        //  TODO: Add onSelectableClick(courseList.get(position)) if need to pass bundle
        holder.itemView.setOnClickListener(v -> onSelectableClick());
    }

    private void onSelectableClick() {

        UnitFragment fragmentUnit = new UnitFragment();
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.base_fragment, fragmentUnit, getClass().getSimpleName())
                .addToBackStack(getClass().getSimpleName())
                .commitAllowingStateLoss();
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}