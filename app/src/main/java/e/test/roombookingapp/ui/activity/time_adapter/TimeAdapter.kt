package e.test.roombookingapp.ui.activity.time_adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import e.test.roombookingapp.R
import kotlinx.android.synthetic.main.single_time.view.*


/**
 * Created by Noe on 14/11/2018.
 */
class TimeAdapter(var listTimes: List<java.sql.Time>, var listAvail: List<java.sql.Time>) : RecyclerView.Adapter<TimeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = parent.inflate(R.layout.single_time)
        return ViewHolder(v, listAvail)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listTimes[position])
    }

    override fun getItemCount(): Int {
        return listTimes.size
    }

    fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }

    class ViewHolder(itemView: View, var listAvail: List<java.sql.Time>) : RecyclerView.ViewHolder(itemView) {
        fun bind(time: java.sql.Time) = with(itemView) {
            //If time is avail change color
            for (avail in listAvail) {
                if (time == avail) {
                    itemView.text_minute.setBackgroundColor(ContextCompat.getColor(context, R.color.green_300))
                }
            }
        }
    }


}