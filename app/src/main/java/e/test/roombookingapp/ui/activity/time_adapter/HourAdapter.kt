package e.test.roombookingapp.ui.activity.time_adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import e.test.roombookingapp.R
import kotlinx.android.synthetic.main.single_hour.view.*

/**
 * Created by Noe on 15/11/2018.
 */

class HourAdapter() : RecyclerView.Adapter<HourAdapter.ViewHolder>() {
    //simple list to display hours
    val list = arrayOf("7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = parent.inflate(R.layout.single_hour)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(h: String) = with(itemView) {
            itemView.text_hour.text = h
        }
    }
}