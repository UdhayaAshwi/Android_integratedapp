package com.udhaya.integratedapp.model.repo


import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/* to create a subclass of the RealmObject class
* */
@RealmClass
open class DetailsRealm(
    @PrimaryKey
    var userId: String? = null,
    var state: String? = null,
    var place: String? = null
) : RealmObject()