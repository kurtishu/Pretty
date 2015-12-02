package com.dreamfactory.kurtishu.pretty.db;

import com.dreamfactory.kurtishu.pretty.PrettyApp;
import com.dreamfactory.kurtishu.pretty.db.convert.EntityConvert;
import com.dreamfactory.kurtishu.pretty.db.entity.ClasslfyEntity;
import com.dreamfactory.kurtishu.pretty.model.Galleryclass;
import com.orhanobut.logger.Logger;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by kurtishu on 12/1/15.
 */
public class DBManager {

    public static void saveClasslfy(final List<Galleryclass> list) {

        Realm realm = Realm.getInstance(PrettyApp.getContext());

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                if (list.size() == realm.where(ClasslfyEntity.class).findAll().size()) {
                    return;
                }
                for (Galleryclass galleryclass : list) {
                    ClasslfyEntity classlfyEntity = realm.createObject(ClasslfyEntity.class);
                    EntityConvert.toDBEntity(classlfyEntity, galleryclass);
                }
            }
        }, new Realm.Transaction.Callback(){
            @Override
            public void onSuccess() {
                super.onSuccess();
                Logger.i("Insert Data to database successfully!");
            }

            @Override
            public void onError(Exception e) {
                super.onError(e);
                Logger.i("Insert Data to database failure with error message:%s", e.getMessage());
            }
        });
    }

    public static List<ClasslfyEntity> getClasslfyList() {
        Realm realm = Realm.getInstance(PrettyApp.getContext());
        return realm.where(ClasslfyEntity.class).findAllAsync();
    }
}
