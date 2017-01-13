package com.pascalhow.htmlparsingexampleapp.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pascalhow.htmlparsingexampleapp.MainActivity;
import com.pascalhow.htmlparsingexampleapp.R;
import com.pascalhow.htmlparsingexampleapp.criteria.CriteriaFragment;
import com.pascalhow.htmlparsingexampleapp.model.Criteria;
import com.pascalhow.htmlparsingexampleapp.model.PerformanceCriteria;
import com.pascalhow.htmlparsingexampleapp.model.Unit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pascal on 02/01/2017.
 */

public class PerformanceCriteriaItemAdapter extends RecyclerView.Adapter<PerformanceCriteriaItemAdapter.ViewHolder> {

    private List<Criteria> criteriaList;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        int itemType;
        TextView criteria;
        TextView performances;

        public ViewHolder(final View itemView, int ViewType) {
            super(itemView);
            this.itemType = ViewType;
            criteria = (TextView) itemView.findViewById(R.id.performance_criteria_criteria);
            performances = (TextView) itemView.findViewById(R.id.performance_criteria_performances);
        }
    }

    public PerformanceCriteriaItemAdapter(Context context) {
        this.criteriaList = new ArrayList<>();
        this.context = context;
    }

    public void setItemList(List<Criteria> performanceCriteriaList) {
        this.criteriaList = performanceCriteriaList;
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
        final Criteria performanceCriteria = criteriaList.get(position);
        holder.criteria.setText(performanceCriteria.getElement());
        holder.performances.setText(performanceCriteria.getPerformancesString());
    }

    @Override
    public int getItemCount() {
        return criteriaList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}