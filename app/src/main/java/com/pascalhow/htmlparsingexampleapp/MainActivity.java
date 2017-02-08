package com.pascalhow.htmlparsingexampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.pascalhow.htmlparsingexampleapp.course.CourseFragment;
import com.pascalhow.htmlparsingexampleapp.criteria.PerformanceCriteriaFragment;
import com.pascalhow.htmlparsingexampleapp.unit.UnitFragment;

public class MainActivity extends AppCompatActivity {

    public static final int FRAGMENT_COURSE = 0;
    public static final int FRAGMENT_UNIT = 1;
    public static final int FRAGMENT_PERFORMANCE_CRITERIA = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setOnBackStackListener();

        loadFragment(new CourseFragment(), FRAGMENT_COURSE);

    }

    /**
     * Handle screen display when navigating between fragment
     *
     * @param fragment The current fragment
     */
    private void updateFragmentTitle(Fragment fragment) {
        String fragClassName = fragment.getClass().getName();

        if (fragClassName.equals(CourseFragment.class.getName())) {
            setTitle(getResources().getString(R.string.course_fragment_title));
        } else if (fragClassName.equals(UnitFragment.class.getName())) {
            setTitle(getResources().getString(R.string.unit_fragment_title));
        } else if (fragClassName.equals(PerformanceCriteriaFragment.class.getName())) {
            setTitle(getResources().getString(R.string.performance_criteria_fragment_title));
        }
    }

    /**
     * Replaces or adds a new fragment on top of the current fragment
     *
     * @param fragment The new fragment
     * @param tag      A tag relating to the new fragment
     */
    public void loadFragment(Fragment fragment, int tag) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (tag) {

            case FRAGMENT_COURSE:
                fragmentManager.beginTransaction()
                        .replace(R.id.base_fragment, fragment, getClass().getSimpleName())
                        .commit();
                break;

            case FRAGMENT_UNIT:
                fragmentManager.beginTransaction()
                        .add(R.id.base_fragment, fragment, getClass().getSimpleName())
                        .addToBackStack(getClass().getSimpleName())
                        .commitAllowingStateLoss();

            case FRAGMENT_PERFORMANCE_CRITERIA:
                fragmentManager.beginTransaction()
                        .add(R.id.base_fragment, fragment, getClass().getSimpleName())
                        .addToBackStack(getClass().getSimpleName())
                        .commitAllowingStateLoss();
            default:
                break;
        }
    }

    /**
     * Handles backStackListener when user navigates between fragments
     */
    private void setOnBackStackListener() {
        getSupportFragmentManager().addOnBackStackChangedListener(
                () -> {
                    // Update your UI here.
                    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.base_fragment);
                    if (fragment != null) {
                        updateFragmentTitle(fragment);
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
    }
}
