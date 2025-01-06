package com.udhaya.integratedapp.ui.viewModel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udhaya.integratedapp.model.DataSource

/* ViewModel class that holds the business logic
needed to perform login screen validation
 */
class LoginViewModel : ViewModel() {

    val loginResult = MutableLiveData<Boolean>()
    var userName = ObservableField("")
    var password = ObservableField("")
    var weatherData = ArrayList<DataSource>()
    fun performLogin(username: String, password: String) {
        loginResult.postValue(checkCredentials(username, password))
    }

    fun checkCredentials(username: String, password: String): Boolean {
        //Function to match the input credentials
        return username == "Udhaya" && password == "1234"
    }

    fun checkBlank(username: String, password: String): Int {
        //Function to check if any of the input field in login screen is blank
        var isEmptyFlag: Int = 0
        if (username.isEmpty() || password.isEmpty()) {
            isEmptyFlag = 1
        }
        if (password.isEmpty()) {
            isEmptyFlag = 2
        }
        return isEmptyFlag
    }

    fun generateList(size: Int): ArrayList<DataSource> {
        //Function to provide the data to be displayed in the recyclerview
        weatherData = ArrayList()
        weatherData.add(DataSource("Canada"))
        weatherData.add(DataSource("Australia"))
        weatherData.add(DataSource("India"))
        weatherData.add(DataSource("France"))
        weatherData.add(DataSource("Japan"))
        weatherData.add(DataSource("Malaysia"))
        return weatherData

    }
}

