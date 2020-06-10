package udhaya.example.bottomnav.model


import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class DetailsRealm(
    @PrimaryKey
    var userId: String ?=null,
    var emailId: String ? = null,
    var deptt: String ? = null
): RealmObject()