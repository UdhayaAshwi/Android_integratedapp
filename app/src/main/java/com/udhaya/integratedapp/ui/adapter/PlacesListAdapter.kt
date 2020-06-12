package com.udhaya.integratedapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.udhaya.integratedapp.R
import com.udhaya.integratedapp.model.DataSource
import kotlinx.android.synthetic.main.custom_layout.view.*

/* Adapter and ViewHolder to render the
   recyclerview items
 */
class PlacesListAdapter(
    val inputList: List<DataSource>,
    val customClickListener: OnCustomClickListener
) :
    RecyclerView.Adapter<PlacesListAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener, View.OnLongClickListener {
        var movieTitle = itemView.findViewById(R.id.movietitle) as TextView
        var imageView: ImageView = itemView.imageView

        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
            movieTitle = itemView.findViewById(R.id.movietitle)
        }

        override fun onClick(v: View?) {
            customClickListener.onPress(inputList[adapterPosition])
        }

        override fun onLongClick(v: View?): Boolean {
            customClickListener.onLongPress(inputList[adapterPosition])
            return true
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlacesListAdapter.ViewHolder {
        //Function to initialize viewholder items
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        //Function to return the count of the recyclerview items
        return inputList.size
    }

    val imageLink: List<String> = arrayListOf(
        "https://picsum.photos/200",
        "https://picsum.photos/300",
        "https://picsum.photos/700",
        "https://picsum.photos/400",
        "https://picsum.photos/500",
        "https://picsum.photos/600"
    )

    override fun onBindViewHolder(holder: PlacesListAdapter.ViewHolder, position: Int) {
        // Function where the data to the viewholder is passed
        val movies: DataSource = inputList[position]
        val img: String = imageLink[position]
        holder.movieTitle.text = movies.placeName
        Glide.with(holder.itemView).load(img).error(
            R.drawable.ic_favorite_black_24dp
        ).diskCacheStrategy(DiskCacheStrategy.NONE)
            .placeholder(R.drawable.ic_dashboard_black_24dp).into(holder.imageView)
    }

    open interface OnCustomClickListener {
        fun onPress(dataSource: DataSource)
        fun onLongPress(dataSource: DataSource)
    }
}