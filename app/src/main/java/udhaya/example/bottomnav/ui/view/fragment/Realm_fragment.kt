package udhaya.example.bottomnav.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import udhaya.example.bottomnav.R

import udhaya.example.bottomnav.model.DetailsRealm
import udhaya.example.bottomnav.ui.view.application.SaveApplication
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_realm.*

import udhaya.example.bottomnav.databinding.FragmentRealmBinding
import udhaya.example.bottomnav.ui.viewModel.Realm_ViewModel


class Realm_fragment : Fragment() {

    private lateinit var realm: Realm
    private lateinit var RealmVM: Realm_ViewModel
    private lateinit var UserList: List<DetailsRealm>
    private var views: Views? = null
    private class Views(val binding: FragmentRealmBinding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        myApp.initRealm()

        val binder: FragmentRealmBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_realm,
            container,
            false
        )
        var myApp: SaveApplication =
            SaveApplication()

        RealmVM = ViewModelProviders.of(this).get(Realm_ViewModel::class.java)
        binder.vm=RealmVM
        binder.lifecycleOwner=this
        return binder.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//      views?.binding?.apply {
//            vm = RealmVM
//        }
        RealmVM.writeSuccess.observe(viewLifecycleOwner, Observer {

            if (RealmVM.writeSuccess.value == true) {
                UserList = RealmVM.readResult.value as List<DetailsRealm>
                for (user in UserList)
                    tv_response.append("--->  ${user.userId} " + " ${user.emailId} " + " ${user.deptt} ")
            } else {
                tv_response.append("Cannot save to DB")

            }

        })

    }
}
