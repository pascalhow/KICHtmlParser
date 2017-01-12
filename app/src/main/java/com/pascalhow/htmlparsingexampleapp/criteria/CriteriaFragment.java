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

import com.pascalhow.htmlparsingexampleapp.MainActivity;
import com.pascalhow.htmlparsingexampleapp.R;
import com.pascalhow.htmlparsingexampleapp.R2;
import com.pascalhow.htmlparsingexampleapp.adapter.UnitItemAdapter;
import com.pascalhow.htmlparsingexampleapp.classes.CourseManager;
import com.pascalhow.htmlparsingexampleapp.model.Unit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by pascal on 12/01/2017.
 */

public class CriteriaFragment extends Fragment {

    private UnitItemAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private CourseManager courseManager;

    private MainActivity mainActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_criteria, container, false);

        ButterKnife.bind(this, rootView);

        mainActivity = (MainActivity) getActivity();
        mainActivity.setTitle(R.string.criteria_fragment_title);

//        mLayoutManager = new LinearLayoutManager(getActivity());
//
//        mAdapter = new UnitItemAdapter(getContext());

        courseManager = new CourseManager();
//
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(mAdapter);
//        recyclerView.setLayoutManager(mLayoutManager);

        setCriteriaObservable();

        setHasOptionsMenu(true);

        return rootView;
    }

    public void setCriteriaObservable() {
        courseManager.getUnitObservable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getCriteriaSubscriber());
    }

    public Subscriber<List<Unit>> getCriteriaSubscriber() {
        return new Subscriber<List<Unit>>() {

            @Override
            public void onCompleted() {
                Timber.d("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Timber.e("onError");
            }

            @Override
            public void onNext(List<Unit> unitList) {
                Timber.d("onNext");
//                mAdapter.setItemList(unitList);
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

