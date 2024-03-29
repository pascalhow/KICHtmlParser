package com.pascalhow.htmlparsingexampleapp.criteria;

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

import com.pascalhow.htmlparsingexampleapp.MainActivity;
import com.pascalhow.htmlparsingexampleapp.R;
import com.pascalhow.htmlparsingexampleapp.R2;
import com.pascalhow.htmlparsingexampleapp.adapter.PerformanceCriteriaItemAdapter;
import com.pascalhow.htmlparsingexampleapp.classes.CourseManager;
import com.pascalhow.htmlparsingexampleapp.model.PerformanceCriteria;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by pascal on 12/01/2017.
 */

public class PerformanceCriteriaFragment extends Fragment {

    @BindView(R2.id.performance_criteria_progressbar)
    ProgressBar progressBar;

    @BindView(R2.id.performance_criteria_list)
    RecyclerView recyclerView;

    private PerformanceCriteriaItemAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    private Subscription subscription;
    private CourseManager courseManager;

    private MainActivity mainActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_performance_criteria, container, false);

        ButterKnife.bind(this, rootView);

        mainActivity = (MainActivity) getActivity();
        getActivity().setTitle(R.string.performance_criteria_fragment_title);

        progressBar.setVisibility(View.VISIBLE);

        mLayoutManager = new LinearLayoutManager(getActivity());

        mAdapter = new PerformanceCriteriaItemAdapter(getContext());

        courseManager = new CourseManager();

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(mLayoutManager);

        setCriteriaObservable();

        setHasOptionsMenu(true);

        return rootView;
    }

    public void setCriteriaObservable() {
        this.subscription = courseManager.getCriteriaObservable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getPerformanceCriteriaSubscriber());
    }

    public Subscriber<List<PerformanceCriteria>> getPerformanceCriteriaSubscriber() {
        return new Subscriber<List<PerformanceCriteria>>() {

            @Override
            public void onCompleted() {
                Timber.d("onCompleted");
                progressBar.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable e) {
                Timber.e("onError");
            }

            @Override
            public void onNext(List<PerformanceCriteria> performanceCriteriaList) {
                Timber.d("onNext");
                mAdapter.setItemList(performanceCriteriaList);
            }
        };
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_settings);
        item.setVisible(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public void onResume() {
        mainActivity.setTitle(R.string.performance_criteria_fragment_title);
        super.onResume();
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

    @Override
    public void onStop() {
        super.onStop();

        if (this.subscription != null) {
            this.subscription.unsubscribe();
        }
    }
}

