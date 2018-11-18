package e.test.roombookingapp.ui.activity

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.View
import android.widget.DatePicker
import dagger.android.AndroidInjection
import e.test.roombookingapp.R
import e.test.roombookingapp.data.model.Room
import e.test.roombookingapp.ui.activity.adapter.RoomsAdapter
import e.test.roombookingapp.ui.activity.presenter.RoomsPresentation
import e.test.roombookingapp.ui.activity.presenter.RoomsPresenter
import e.test.roombookingapp.utils.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject


class RoomActivity : AppCompatActivity(), RoomsPresentation, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var presenter: RoomsPresenter
    var listRooms: List<Room> = emptyList()

    lateinit var dateSetListener : DatePickerDialog.OnDateSetListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)

        presenter.onCreate(this)

        swipe_container.setOnRefreshListener(this)
        recycler_rooms.setHasFixedSize(true)
        recycler_rooms.layoutManager = LinearLayoutManager(this)

        dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                presenter.onSelectedDate(calendar.time.time)


            }
        }
        tag_today.setOnClickListener{
            presenter.onClickToday()
        }
        tag_tomorrow.setOnClickListener{
            presenter.onClickTomorrow()
        }
        tag_pick_date.setOnClickListener{
            presenter.onClickPickADate()
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                if (!query.isEmpty()) {
                    presenter.onSearchQuery(query)
                } else {
                    presenter.loadRooms(SELECTED_DATE)
                }
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (!newText.isEmpty()) {
                } else {
                    presenter.loadRooms(SELECTED_DATE)
                    showMessageNoResult(false)
                }
                return true
            }
        })

    }

    override fun onRefresh() {
        if(SELECTED_DATE.isEmpty()) {
            presenter.loadRooms(DEFAULT_DATE)
        }else{
            presenter.loadRooms(SELECTED_DATE)
        }
    }

    override fun initPickerDialog() {
        initDatePickerFragment()
    }

    override fun showBookActivity(room: Room) {
        val i = Intent(this, BookActivity::class.java)
        i.putExtra("room", room)
        startActivity(i)
    }

    override fun showLoading(visibility: Boolean) {
        swipe_container.isRefreshing = visibility
    }

    override fun showRooms(list: List<Room>) {
        recycler_rooms.adapter = RoomsAdapter(presenter, {true})
    }

    override fun showSearchResults(list: List<Room>) {
        if(list.isEmpty()){
            showMessageNoResult(true)
        }else{
            showMessageNoResult(false)
        }
        recycler_rooms.adapter.notifyDataSetChanged()

    }
    override fun showMessage(message: Int) {
        Snackbar.make(main_view, getString(message), Snackbar.LENGTH_LONG).show()
    }

    override fun showTagDateSelected(visibility: Boolean, date: String?) {
        if(visibility) {
            tag_selected_date.visibility= View.VISIBLE
            tag_selected_date.text = date
        }else{
            tag_selected_date.visibility= View.GONE
        }
    }

    override fun showCheckedButtons(button: Int) {
       when(button){
          BUTTON_TODAY -> {
              tag_today.background = ContextCompat.getDrawable(this, R.drawable.rounded_btn_tag_selected)
              tag_pick_date.background = ContextCompat.getDrawable(this, R.drawable.rounded_btn_tag)
              tag_tomorrow.background = ContextCompat.getDrawable(this, R.drawable.rounded_btn_tag)
          }
          BUTTON_TOMORROW-> {
              tag_tomorrow.background = ContextCompat.getDrawable(this, R.drawable.rounded_btn_tag_selected)
              tag_today.background = ContextCompat.getDrawable(this, R.drawable.rounded_btn_tag)
              tag_pick_date.background = ContextCompat.getDrawable(this, R.drawable.rounded_btn_tag)
          }
           BUTTON_PICk_DATE->{
               tag_pick_date.background = ContextCompat.getDrawable(this, R.drawable.rounded_btn_tag_selected)
               tag_today.background = ContextCompat.getDrawable(this, R.drawable.rounded_btn_tag)
               tag_tomorrow.background = ContextCompat.getDrawable(this, R.drawable.rounded_btn_tag)
           }
       }

    }
    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun initDatePickerFragment(){
        val calendar = Calendar.getInstance()
        val dialog = DatePickerDialog(this@RoomActivity,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        )
        dialog.datePicker.setMinDate(calendar.timeInMillis-1000)
        dialog.show()

    }
    private fun showMessageNoResult(visibility: Boolean){
        if(!visibility) {
            text_no_result.visibility = View.GONE
        }else {
            text_no_result.visibility = View.VISIBLE
        }
    }
}
