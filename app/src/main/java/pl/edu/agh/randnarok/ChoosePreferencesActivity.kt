package pl.edu.agh.randnarok

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_choose_preferences.*
import org.json.JSONObject
import pl.edu.agh.randnarok.adapters.ChoosePreferencesAdapter
import pl.edu.agh.randnarok.model.Event

class ChoosePreferencesActivity : AppCompatActivity() {

    lateinit var choosePreferencesAdapter: ChoosePreferencesAdapter
    private var dataList: ArrayList<Event> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_preferences)

        getEvents()
    }

    private fun getEvents() {
        dataList.removeAll { true }
        val url = "http://student.agh.edu.pl/~bociepka/randnarok/data.json"

        val directionsRequest =
            object : StringRequest(Method.GET, url, Response.Listener<String> { response ->
                val jsonResponse = JSONObject(response)
                val eventsArray = jsonResponse.getJSONArray("events")
                for (i in 0..eventsArray.length() - 1) {
                    val jsonEvent = eventsArray.getJSONObject(i)
                    var name = jsonEvent.get("name").toString()
                    var date = jsonEvent.get("date").toString()
                    var startTime = jsonEvent.get("startTime").toString()
                    var endTime = jsonEvent.get("endTime").toString()
                    var city = jsonEvent.get("city").toString()
                    var address = jsonEvent.get("address").toString()
                    var desc = jsonEvent.get("description").toString()
                    var price = jsonEvent.get("price").toString()
                    var picture = jsonEvent.get("picture").toString()
                    val idx = jsonEvent.get("idx").toString().toInt()
                    val departure = Event(
                        idx,
                        name,
                        date,
                        startTime,
                        endTime,
                        city,
                        address,
                        desc,
                        price,
                        picture
                    )
                    dataList.add(departure)
                }

                choosePreferencesAdapter = ChoosePreferencesAdapter(this, dataList)
                choose_preferences_recycler_layout.apply {
                    layoutManager = LinearLayoutManager(this@ChoosePreferencesActivity)
                    adapter = choosePreferencesAdapter
                }
                choose_preferences_recycler_layout.adapter = choosePreferencesAdapter
                choosePreferencesAdapter.notifyDataSetChanged()

//                choose_preferences_recycler_layout.onItemClickListener =
//                    AdapterView.OnItemClickListener { parent, view, position, id ->
//                        val pickedEvent = dataList.get(position)
//
//                    }

            }, Response.ErrorListener {
                Log.e("Volley error", it.toString())
            }) {}
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(directionsRequest)
    }


}