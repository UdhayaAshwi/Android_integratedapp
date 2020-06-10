package udhaya.example.bottomnav.ui.viewModel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import udhaya.example.bottomnav.model.DataSource
import udhaya.example.bottomnav.ui.view.fragment.weatherdata

class Login_ViewModel : ViewModel() {


    val loginresult = MutableLiveData<Boolean>()
    var username = ObservableField("")
    var password = ObservableField("")

    fun performLogin(username: String, password: String) {
        loginresult.postValue(checkCredentials(username, password))
    }

    fun checkCredentials(username: String, password: String): Boolean {
        return username == "Udhaya" && password == "1234"
    }

    fun checkBlank(username: String, password: String): Int {
        var returnval: Int = 0
        if (username.isEmpty() || password.isEmpty()) {
            returnval = 1
        }
        if (password.isEmpty()) {
            returnval = 2
        }

        return returnval
    }

    fun generateList(size: Int): ArrayList<DataSource> {
        weatherdata = ArrayList()
        weatherdata.add(DataSource("Canada"))
        weatherdata.add(DataSource("Australia"))
        weatherdata.add(DataSource("India"))
        weatherdata.add(DataSource("France"))
        weatherdata.add(DataSource("Japan"))
        weatherdata.add(DataSource("Malaysia"))
        return weatherdata

    }
}

