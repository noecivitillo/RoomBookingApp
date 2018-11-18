package e.test.roombookingapp.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import e.test.roombookingapp.R
import e.test.roombookingapp.data.model.Room
import e.test.roombookingapp.ui.activity.presenter.BookPresentation
import e.test.roombookingapp.ui.activity.presenter.BookPresenter
import e.test.roombookingapp.ui.activity.time_adapter.HourAdapter
import e.test.roombookingapp.ui.activity.time_adapter.TimeAdapter
import e.test.roombookingapp.utils.getAllSingleTimesAvail
import e.test.roombookingapp.utils.initDefaultTimes
import e.test.roombookingapp.utils.initTimeAvailObjFrom
import kotlinx.android.synthetic.main.detail_activity.*
import javax.inject.Inject

/**
 * Created by Noe on 16/11/2018.
 */
class BookActivity : AppCompatActivity(), BookPresentation {


    @Inject
    lateinit var presenter: BookPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.detail_activity)

        val room = intent.extras.getParcelable<Room>("room")

        //quick cancel
        cancel_book.setOnClickListener {
            finish()
        }

        presenter.onCreate(this)
        presenter.initDataRoom(room)
    }

    override fun showDataRoom(room: Room) {
        room_name_detail.text = room.name
        room_location_detail.text = room.location
        room_size_detail_fill.text = room.size
        room_capacity_detail_fill.text = room.capacity.toString()

        val list: List<String> = room.equipment!!

        for (equip in list) {
            room_equip_detail_fill.append(equip)
            room_equip_detail_fill.append(", ")
        }

        val listImages: List<String> = room.images!!
        getImageFromServer(listImages[0], image_room)
        getImageFromServer(listImages[1], image_room2)


        val listTimes = initDefaultTimes()
        val listAvailTimes = initTimeAvailObjFrom(room.avail!!)
        val listSingleTimesAvail = getAllSingleTimesAvail(listAvailTimes)

        recycler_times.setHasFixedSize(true)
        recycler_times.adapter = TimeAdapter(listTimes, listSingleTimesAvail)

        recycler_hours.setHasFixedSize(true)
        recycler_hours.bringToFront()
        recycler_hours.adapter = HourAdapter()

    }

    private fun getImageFromServer(assets: String, imageView: ImageView) {
        val url = getString(R.string.endpoint) + assets
        Picasso.get().load(url).resize(400, 300).placeholder(R.color.blue_light_100).into(imageView)
    }
}