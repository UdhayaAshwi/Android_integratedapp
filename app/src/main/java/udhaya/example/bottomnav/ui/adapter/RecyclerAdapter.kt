package udhaya.example.bottomnav.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.custom_layout.view.*
import udhaya.example.bottomnav.R
import udhaya.example.bottomnav.model.DataSource


class RecyclerAdapter(
    val inputList: List<DataSource>,
    val customClickListener: OnCustomClickListener
) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener, View.OnLongClickListener { //, View.OnClickListener{

        var movie_title = itemView.findViewById(R.id.movietitle) as TextView
        var imageview: ImageView = itemView.imageView

        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
            movie_title = itemView.findViewById(R.id.movietitle)
        }

        override fun onClick(v: View?) {
            customClickListener.onPress(inputList[adapterPosition])
        }

        override fun onLongClick(v: View?): Boolean {
            customClickListener.onLongPress(inputList[adapterPosition])
            return true
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val viewhol =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_layout, parent, false)
        return ViewHolder(viewhol)
    }

    override fun getItemCount(): Int {
        return inputList.size
    }

    val imagelink: List<String> = arrayListOf(
        "https://picsum.photos/200",
        "https://picsum.photos/300",
        "https://picsum.photos/700",
        "https://picsum.photos/400",
        "https://picsum.photos/500",
        "https://picsum.photos/600"
    )

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val movies: DataSource = inputList[position]
        val img: String = imagelink[position]
        val item = inputList.get(holder.adapterPosition)
        holder.movie_title.text = movies.text1
        Glide.with(holder.itemView).load(img).error(
            R.drawable.ic_favorite_black_24dp
        ).diskCacheStrategy(DiskCacheStrategy.NONE)
            .placeholder(R.drawable.ic_dashboard_black_24dp).into(holder.imageview)

    }


    open interface OnCustomClickListener {
        fun onPress(dataSource: DataSource)
        fun onLongPress(dataSource: DataSource)
    }
}