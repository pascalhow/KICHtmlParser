package com.pascalhow.htmlparsingexampleapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.pascalhow.htmlparsingexampleapp.MainFragment.MainFragment;
import com.pascalhow.htmlparsingexampleapp.NewFragment.NewFragment;

public class MainActivity extends AppCompatActivity {

    private static final String FRAGMENT_MAIN = "main";
    private static final String FRAGMENT_NEW = "new";

    protected FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        setOnBackStackListener();

        loadFragment(new MainFragment(), FRAGMENT_MAIN);

    }

    /**
     * Handle screen display when navigating between fragment
     * @param fragment The current fragment
     */
    private void updateFragmentTitle(Fragment fragment) {
        String fragClassName = fragment.getClass().getName();

        if (fragClassName.equals(MainFragment.class.getName())) {
            setTitle(getResources().getString(R.string.main_fragment_title));
            showFloatingActionButton();
        } else if (fragClassName.equals(NewFragment.class.getName())) {
            setTitle(getResources().getString(R.string.new_fragment_title));
        }
    }

    /**
     * Replaces or adds a new fragment on top of the current fragment
     * @param fragment The new fragment
     * @param tag A tag relating to the new fragment
     */
    public void loadFragment(android.support.v4.app.Fragment fragment, String tag) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (tag) {

            //  Main fragment is the first fragment to be displayed so we don't addToBackStack()
            case FRAGMENT_MAIN:
                fragmentManager.beginTransaction()
                        .replace(R.id.base_fragment, fragment, tag)
                        .commit();

                fab.setOnClickListener(view -> loadFragment(new NewFragment(), FRAGMENT_NEW));
                break;

            case FRAGMENT_NEW:
                fragmentManager.beginTransaction()
                        .add(R.id.base_fragment, fragment, tag)
                        .addToBackStack(FRAGMENT_NEW)
                        .commitAllowingStateLoss();
            default:
                break;
        }
    }

    public void showFloatingActionButton() {
        fab.show();
    }

    public void hideFloatingActionButton() {
        fab.hide();
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
