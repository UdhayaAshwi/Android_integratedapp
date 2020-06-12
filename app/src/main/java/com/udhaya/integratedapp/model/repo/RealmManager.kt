package com.udhaya.integratedapp.model.repo


import io.realm.Realm
import io.realm.RealmObject

/* Class with methods to perform Database related operations
 */
class RealmManager {

    fun <T : RealmObject> add(model: T) {
        //Function to add new data or modify already existing data
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { realm1 ->
            realm1.insertOrUpdate(model)
        }
        realm.close()
    }

    inline fun <reified T : RealmObject> findAll(): List<T> {
        //Function to return all the objects that satisfy the query conditions
        val realm = Realm.getDefaultInstance()
        val list = realm.copyFromRealm(
            realm.where(T::class.java)
                .findAll()
        )
        realm.close()
        return list
    }

    inline fun <reified T : RealmObject> clearAll() {
        //Function to delete all the Realm objects that are created so far
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { realm1: Realm ->
            realm1.delete(T::class.java)
        }
        realm.close()
    }
}
