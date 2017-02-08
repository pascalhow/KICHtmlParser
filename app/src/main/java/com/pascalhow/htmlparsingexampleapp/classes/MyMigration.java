package com.pascalhow.htmlparsingexampleapp.classes;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;

/**
 * Created by pascal on 08/02/2017.
 */

public class MyMigration implements RealmMigration {

    @Override
    public void migrate(final DynamicRealm realm, long oldVersion, long newVersion) {
        // no op
    }

    @Override
    public boolean equals(Object object) {
        return object != null && object instanceof MyMigration;
    }

    @Override
    public int hashCode() {
        return MyMigration.class.hashCode();
    }
}
