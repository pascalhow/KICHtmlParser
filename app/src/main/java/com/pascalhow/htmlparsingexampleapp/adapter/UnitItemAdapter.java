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
import com.pascalhow.htmlparsingexampleapp.criteria.PerformanceCriteriaFragment;
import com.pascalhow.htmlparsingexampleapp.model.Unit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pascal on 02/01/2017.
 */

public class UnitItemAdapter extends RecyclerView.Adapter<UnitItemAdapter.ViewHolder> {

    private List<Unit> unitList;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        int itemType;
        TextView unit_item_code;
        TextView unit_item_title;

        public ViewHolder(final View itemView, int ViewType) {
            super(itemView);
            this.itemType = ViewType;
            unit_item_code = (TextView) itemView.findViewById(R.id.unit_code);
            unit_item_title = (TextView) itemView.findViewById(R.id.unit_title);
        }
    }

    public UnitItemAdapter(Context context) {
        this.unitList = new ArrayList<>();
        this.context = context;
    }

    public void setItemList(List<Unit> unitList) {
        this.unitList = unitList;
        notifyDataSetChanged();
    }

    @Override
    public UnitItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.unit_list_item, parent, false);
        return new ViewHolder(v, viewType);
    }

    @Override
    public void onBindViewHolder(final UnitItemAdapter.ViewHolder holder, final int position) {
        final Unit unit = unitList.get(position);
        holder.unit_item_code.setText(unit.getCode());
        holder.unit_item_title.setText(unit.getTitle());

        //  TODO: Add onSelectableClick(unitList.get(position)) if need to pass bundle
        holder.itemView.setOnClickListener(v -> onSelectableClick());
    }

    private void onSelectableClick() {
        PerformanceCriteriaFragment fragmentUnit = new PerformanceCriteriaFragment();
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.base_fragment, fragmentUnit, getClass().getSimpleName())
                .addToBackStack(getClass().getSimpleName())
                .commitAllowingStateLoss();
    }

    @Override
    public int getItemCount() {
        return unitList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}