package udhaya.example.bottomnav.ui.viewModel


import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import udhaya.example.bottomnav.realmmanager.RealmManager
import udhaya.example.bottomnav.model.DetailsRealm
import udhaya.example.bottomnav.ui.view.application.SaveApplication

open class Realm_ViewModel(): ViewModel() {

    init {
        var myApp: SaveApplication =
            SaveApplication()
    }

    val readResult = MutableLiveData<List<DetailsRealm>>()
    val writeSuccess = MutableLiveData<Boolean>()

    var realmManager=RealmManager()
    var userid = ObservableField("")
    var user_email = ObservableField("")
    var user_deptt = ObservableField("")

    fun onSave() =
        writeSuccess.postValue(insertupdateUser(userid.get()?: userid.toString(), user_email.get()?:user_email.toString(), user_deptt.get()?:user_deptt.toString() ))


    fun insertupdateUser(id: String, email: String, deptt: String):Boolean {
        if (id.isNotEmpty() && email.isNotEmpty() && deptt.isNotEmpty()) {
            val user = DetailsRealm(id, email, deptt)
            realmManager.add(user)
        }
        getUserRealm()
        return true

    }

    fun getUserRealm() {
        val allusers: List<DetailsRealm> = realmManager.findAll()
        readResult.postValue(allusers)
    }
}
