package com.udhaya.integratedapp.ui.view.fragment

import android.app.Activity
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.udhaya.integratedapp.R
import com.udhaya.integratedapp.model.DataSource
import com.udhaya.integratedapp.ui.adapter.PlacesListAdapter
import com.udhaya.integratedapp.ui.viewModel.LoginViewModel

/* Fragment to list the places in a recyclerview
* and to display the options menu in the actionbar
* */
class PlacesListFragment : Fragment(), PlacesListAdapter.OnCustomClickListener {
    var weatherData = ArrayList<DataSource>()
    var listObject: LoginViewModel = LoginViewModel()
    lateinit var placesListAdapter: PlacesListAdapter
    private var position: Int = 0
    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_placeslist, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.movielistview)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        weatherData = listObject.generateList(10)
        placesListAdapter = PlacesListAdapter(weatherData, this)
        recyclerView.adapter = placesListAdapter
        return view
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        navigationController = findNavController()
        when (item.itemId) {
            R.id.item1 -> navigationController.navigate(R.id.action_bnv_fragment_to_settings)
            R.id.item2 -> navigationController.navigate(R.id.action_bnv_fragment_to_home2_fragment)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPress(dataSource: DataSource) {
        navigationController = findNavController()
        navigationController.navigate(R.id.action_bnv_fragment_to_fragmentActivity)
    }

    override fun onLongPress(dataSource: DataSource) {
        position = weatherData.indexOf(dataSource)
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity as Activity)
        builder.setTitle("Change Title")
        val customLayout: View = layoutInflater.inflate(R.layout.edit_layout, null)
        builder.setMessage("Change ${dataSource.placeName} to : - ")
        builder.setView(customLayout)

        builder.setPositiveButton("OK",
            DialogInterface.OnClickListener { dialog, which ->
                // send data from the AlertDialog to the Activity
                val editText: EditText = customLayout.findViewById(R.id.editText)
                if (editText.text.toString() != "") {
                    weatherData[position].placeName = editText.text.toString()
                    placesListAdapter.notifyDataSetChanged()
                    Toast.makeText(
                        view?.context,
                        "Changed to ${dataSource.placeName}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        builder.setNegativeButton("Cancel", { dialog, which ->
            dialog.dismiss()
        })
        // create and show the alert dialog
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}