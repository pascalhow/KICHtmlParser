package com.pascalhow.htmlparsingexampleapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pascalhow.htmlparsingexampleapp.R;
import com.pascalhow.htmlparsingexampleapp.model.PerformanceCriteria;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pascal on 02/01/2017.
 */

public class PerformanceCriteriaItemAdapter extends RecyclerView.Adapter<PerformanceCriteriaItemAdapter.ViewHolder> {

    private List<PerformanceCriteria> performanceCriteriaList;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        int itemType;
        TextView criteriaElement;
        TextView criteriaPerformances;

        public ViewHolder(final View itemView, int ViewType) {
            super(itemView);
            this.itemType = ViewType;
            criteriaElement = (TextView) itemView.findViewById(R.id.performance_criteria_element);
            criteriaPerformances = (TextView) itemView.findViewById(R.id.performance_criteria_performances);
        }
    }

    public PerformanceCriteriaItemAdapter(Context context) {
        this.performanceCriteriaList = new ArrayList<>();
        this.context = context;
    }

    public void setItemList(List<PerformanceCriteria> performanceCriteriaList) {
        this.performanceCriteriaList = performanceCriteriaList;
        notifyDataSetChanged();
    }

    @Override
    public PerformanceCriteriaItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.criteria_list_item, parent, false);
        return new ViewHolder(v, viewType);
    }

    @Override
    public void onBindViewHolder(final PerformanceCriteriaItemAdapter.ViewHolder holder, final int position) {
        final PerformanceCriteria performanceCriteria = performanceCriteriaList.get(position);
        holder.criteriaElement.setText(performanceCriteria.getElement());
        holder.criteriaPerformances.setText(performanceCriteria.getPerformanceListString());
    }

    @Override
    public int getItemCount() {
        return performanceCriteriaList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}