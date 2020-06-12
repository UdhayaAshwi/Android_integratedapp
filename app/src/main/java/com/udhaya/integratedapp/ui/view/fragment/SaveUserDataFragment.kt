package com.udhaya.integratedapp.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.udhaya.integratedapp.R
import com.udhaya.integratedapp.databinding.FragmentSaveuserdataBinding
import com.udhaya.integratedapp.model.repo.DetailsRealm
import com.udhaya.integratedapp.ui.viewModel.SaveDataViewModel
import kotlinx.android.synthetic.main.fragment_saveuserdata.*

/* Fragment to save the user input and display the entered values
using Realm database
* */

class SaveUserDataFragment : Fragment() {

    private lateinit var saveDataViewModel: SaveDataViewModel
    private lateinit var UserList: List<DetailsRealm>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binder: FragmentSaveuserdataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_saveuserdata,
            container,
            false
        )
        saveDataViewModel = ViewModelProviders.of(this).get(SaveDataViewModel::class.java)
        binder.vm = saveDataViewModel
        binder.lifecycleOwner = this
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        saveDataViewModel.writeSuccess.observe(viewLifecycleOwner, Observer {
            if (saveDataViewModel.writeSuccess.value == true) {
                UserList = saveDataViewModel.readResult.value as List<DetailsRealm>
                for (user in UserList)
                    tv_response.append("--->  ${user.userId} " + " ${user.state} " + " ${user.place} ")
            } else {
                tv_response.append("Cannot save to DB")
            }
        })
    }

}
