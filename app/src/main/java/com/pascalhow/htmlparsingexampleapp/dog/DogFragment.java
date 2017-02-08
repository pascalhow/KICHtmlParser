package com.pascalhow.htmlparsingexampleapp.dog;

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
import android.widget.TextView;

import com.pascalhow.htmlparsingexampleapp.R;
import com.pascalhow.htmlparsingexampleapp.R2;
import com.pascalhow.htmlparsingexampleapp.adapter.UnitItemAdapter;
import com.pascalhow.htmlparsingexampleapp.classes.CourseManager;
import com.pascalhow.htmlparsingexampleapp.model.Dog;
import com.pascalhow.htmlparsingexampleapp.model.Person;
import com.pascalhow.htmlparsingexampleapp.model.Unit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by pascal on 25/12/2016.
 */

public class DogFragment extends Fragment {

    @BindView(R2.id.person)
    TextView person;

    @BindView(R2.id.dog)
    TextView dog;

    // Get a Realm instance for this thread
    Realm realm = Realm.getDefaultInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_dog, container, false);

        ButterKnife.bind(this, rootView);

        getActivity().setTitle("Dogz");

        loadScreen();

        setHasOptionsMenu(true);

        return rootView;
    }

    public void loadScreen() {
        Dog dog = new Dog();
        dog.setName("Rex");
        dog.setAge(1);

        // Query Realm for all dogs younger than 2 years old
        final RealmResults<Dog> puppies = realm.where(Dog.class).lessThan("age", 2).findAll();
        puppies.size(); // => 0 because no dogs have been added to the Realm yet

        // Persist your data in a transaction
        realm.beginTransaction();
        final Dog managedDog = realm.copyToRealm(dog); // Persist unmanaged objects
        Person person = realm.createObject(Person.class, 1); // Create managed objects directly
        person.getDogs().add(managedDog);
        realm.commitTransaction();

        // Listeners will be notified when data changes
        puppies.addChangeListener(results -> {
            // Query results are updated in real time
            puppies.size(); // => 1
        });

        // Asynchronously update objects on a background thread
        realm.executeTransactionAsync(bgRealm -> {
            Dog dog1 = bgRealm.where(Dog.class).equalTo("age", 1).findFirst();
            dog1.setAge(3);
        }, () -> {
            // Original queries and Realm objects are automatically updated.
            puppies.size(); // => 0 because there are no more puppies younger than 2 years old
            managedDog.getAge();   // => 3 the dogs age is updated
        });
    }

    @Override
    public void onResume() {
        getActivity().setTitle(R.string.unit_fragment_title);
        super.onResume();
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

    @Override
    public void onStop() {
        super.onStop();
    }
}
