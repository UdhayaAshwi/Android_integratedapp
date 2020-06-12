package com.udhaya.integratedapp.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.udhaya.integratedapp.R

/* Fragment to display the favorite places */
class FavoriteFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var button: Button = view.findViewById(R.id.signoutfromfav)
        val navController: NavController = Navigation.findNavController(view)
        button.setOnClickListener {
            navController.navigate(R.id.action_favorite_to_bnv_fragment3)
        }
    }


}
