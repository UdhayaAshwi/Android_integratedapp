package com.udhaya.integratedapp.ui.viewModel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udhaya.integratedapp.model.repo.DetailsRealm
import com.udhaya.integratedapp.model.repo.RealmManager

/* ViewModel class that holds the business logic
needed to perform Realm operations
* */
open class SaveDataViewModel : ViewModel() {

    val readResult = MutableLiveData<List<DetailsRealm>>()
    val writeSuccess = MutableLiveData<Boolean>()
    var realmManager = RealmManager()
    var userId = ObservableField("")
    var state = ObservableField("")
    var place = ObservableField("")

    //Function to save the input data to the RealmDB
    fun onSave() =
        writeSuccess.postValue(
            insertupdateUser(
                userId.get() ?: userId.toString(),
                state.get() ?: state.toString(),
                place.get() ?: place.toString()
            )
        )

    fun insertupdateUser(id: String, statename: String, placename: String): Boolean {
        if (id.isNotEmpty() && statename.isNotEmpty() && placename.isNotEmpty()) {
            val user = DetailsRealm(
                id,
                statename,
                placename
            )
            realmManager.add(user)
        }
        getUserRealm()
        return true
    }

    fun getUserRealm() {
        //Function to display the input values
        val allUsers: List<DetailsRealm> = realmManager.findAll()
        readResult.postValue(allUsers)
    }
}
