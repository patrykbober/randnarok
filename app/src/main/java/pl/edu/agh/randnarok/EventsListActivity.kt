package pl.edu.agh.randnarok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import pl.edu.agh.randnarok.model.Event
import org.json.JSONObject

class EventsListActivity : AppCompatActivity() {
    var eventsList = mutableListOf<Event>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView()
    }


    private fun getEvents() {
        eventsList.removeAll{true}
        val url = "http://student.agh.edu.pl/~bociepka/randnarok/data.json"

        val directionsRequest =
            object : StringRequest(Method.GET, url, Response.Listener<String> { response ->
                val jsonResponse = JSONObject(response)
                val eventsArray = jsonResponse.getJSONArray("events")
//                for (i in 0..departuresArray.length() - 1) {
//                    val jsonDeparture = departuresArray.getJSONObject(i)
//                    val displayTime = jsonDeparture.get("display_time").toString()
//                    val lineNumber = jsonDeparture.get("line_number").toString()
//                    val direction = jsonDeparture.get("direction").toString()
//                    val category = jsonDeparture.get("category").toString()
//                    val delay = jsonDeparture.get("delay").toString()
//                    val departure = Departure(displayTime, lineNumber, direction, category, delay)
//                    departuresList.add(departure)
//                }
//                onDeparturesReady()
            }, Response.ErrorListener {
            }) {}
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(directionsRequest)
    }

}