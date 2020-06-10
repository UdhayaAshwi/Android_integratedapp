package udhaya.example.bottomnav.ui.view.fragment

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
import udhaya.example.bottomnav.R
import udhaya.example.bottomnav.model.DataSource
import udhaya.example.bottomnav.ui.adapter.RecyclerAdapter
import udhaya.example.bottomnav.ui.viewModel.Login_ViewModel
var weatherdata = ArrayList<DataSource>()
var listobject: Login_ViewModel = Login_ViewModel()
private lateinit var navigationController: NavController

class bnv : Fragment(), RecyclerAdapter.OnCustomClickListener {

    private var pos: Int = 0
    lateinit var mAdapter: RecyclerAdapter

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
        val v: View = inflater.inflate(R.layout.fragment_recyclerview, container, false)
        val recyclerView: RecyclerView = v.findViewById(R.id.movielistview)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        weatherdata = listobject.generateList(10)
        mAdapter = RecyclerAdapter(weatherdata, this)
        recyclerView.adapter = mAdapter
        return v

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
        pos = weatherdata.indexOf(dataSource)
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity as Activity)
        builder.setTitle("Change Title")
        val customLayout: View = layoutInflater.inflate(R.layout.edit_layout, null)
        builder.setMessage("Change ${dataSource.text1} to : - ")
        builder.setView(customLayout)

        builder.setPositiveButton("OK",
            DialogInterface.OnClickListener { dialog, which ->
                // send data from the AlertDialog to the Activity

                val editText: EditText = customLayout.findViewById(R.id.editText)
                if (editText.text.toString() != "") {
                    weatherdata[pos].text1 = editText.text.toString()
                    mAdapter.notifyDataSetChanged()
                    Toast.makeText(
                        view?.context,
                        "Changed to ${dataSource.text1}",
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

    // do something with the data coming from the AlertDialog
    private fun sendDialogDataToActivity(data: String) {
        Toast.makeText(view?.context, data, Toast.LENGTH_SHORT).show()
    }

}



