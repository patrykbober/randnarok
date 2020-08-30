package pl.edu.agh.randnarok.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import pl.edu.agh.randnarok.EventActivity
import pl.edu.agh.randnarok.R
import pl.edu.agh.randnarok.model.Event
import pl.edu.agh.randnarok.model.EventAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private var listView: ListView? = null
private var adapter: EventAdapter? = null
private var dataList: ArrayList<Event> = ArrayList<Event>()

/**
 * A simple [Fragment] subclass.
 * Use the [EventsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EventsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        activity!!.toolbar_title.text = "My events"
        val list: ArrayList<Event> = ArrayList<Event>()
        val view = inflater.inflate(R.layout.fragment_events, container, false)
        getEvents(view)
        return view
    }

    private fun getEvents(view: View) {
        dataList.removeAll{true}
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
                    val departure = Event(idx, name, date, startTime, endTime, city, address, desc, price, picture)
                    dataList.add(departure)
                }
                listView = view.findViewById<ListView>(R.id.events_list)
                adapter = EventAdapter(activity!!, dataList)
                listView!!.adapter = adapter
                adapter!!.notifyDataSetChanged()

                listView!!.onItemClickListener =
                    OnItemClickListener { parent, view, position, id ->
                        val pickedEvent = dataList.get(position)
                        val intent =
                            Intent(activity, EventActivity::class.java).apply {
                            putExtra("name", pickedEvent.name)
                            putExtra("date", pickedEvent.date)
                            putExtra("price", pickedEvent.price)
                            putExtra("time", pickedEvent.startTime)
                            putExtra("location", pickedEvent.address + " " + pickedEvent.city)
                            putExtra("description", pickedEvent.desc)
                        }
                        startActivity(intent)
                    }

            }, Response.ErrorListener {
                Log.e("Volley error", it.toString())
            }) {}
        val requestQueue = Volley.newRequestQueue(activity)
        requestQueue.add(directionsRequest)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EventsFragment.
         */
        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            EventsFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
        @JvmStatic
        fun newInstance (): EventsFragment {
            return EventsFragment()
        }

    }
}