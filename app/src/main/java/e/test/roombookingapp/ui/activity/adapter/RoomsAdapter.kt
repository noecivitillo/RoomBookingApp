package e.test.roombookingapp.ui.activity.adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import e.test.roombookingapp.R
import e.test.roombookingapp.data.model.Room
import e.test.roombookingapp.ui.activity.presenter.RoomsPresenter
import e.test.roombookingapp.ui.activity.time_adapter.HourAdapter
import e.test.roombookingapp.ui.activity.time_adapter.TimeAdapter
import e.test.roombookingapp.utils.getAllSingleTimesAvail
import e.test.roombookingapp.utils.initDefaultTimes
import e.test.roombookingapp.utils.initTimeAvailObjFrom
import kotlinx.android.synthetic.main.single_room.view.*
import org.jetbrains.anko.doAsyncResult

/**
 * Created by Noe on 13/11/2018.
 */
class RoomsAdapter(var presenter: RoomsPresenter, val listener: (Room) -> Boolean) : RecyclerView.Adapter<RoomsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = parent.inflate(R.layout.single_room)
        return ViewHolder(v, presenter)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(presenter.listRooms[position], listener)


    }
    override fun getItemCount(): Int {
        return presenter.listRooms.size
    }

    fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }
    class ViewHolder(itemView: View, var presenter: RoomsPresenter) : RecyclerView.ViewHolder(itemView){
        fun bind(room: Room, listener: (Room) -> Boolean) = with(itemView) {

            itemView.room_name_text.text = room.name
            itemView.room_location_text.text = room.location
            itemView.room_capacity_text_fill.text =context.getString(R.string.concatenate_attendees, room.capacity.toString())
            itemView.room_size_text_fill.text = room.size


            //init default times
            val listTimes =  doAsyncResult{ initDefaultTimes()
            }.get()
            //get avail times
            val listAvailTimes =
                    doAsyncResult{ initTimeAvailObjFrom(room.avail!!)
                    }.get()
            //store avail times in a single list
            val listSingleTimesAvail = doAsyncResult{ getAllSingleTimesAvail(listAvailTimes)
            }.get()



            //init minutes recycler
            itemView.recycler_times.setHasFixedSize(true)
            itemView.recycler_times.adapter = TimeAdapter(listTimes, listSingleTimesAvail)
            itemView.recycler_times.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


            //init hours recycler
            itemView.recycler_hours.setHasFixedSize(true)
            itemView.recycler_hours.bringToFront()
            itemView.recycler_hours.adapter = HourAdapter()
            itemView.recycler_hours.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


            setOnClickListener { listener (room)
                //open room detail
                presenter.onOpenRoom(room)
            }
        }

    }


}


