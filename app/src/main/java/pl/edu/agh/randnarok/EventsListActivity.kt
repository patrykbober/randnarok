package pl.edu.agh.randnarok

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.event_layout.*
import pl.edu.agh.randnarok.model.Event
import org.json.JSONObject
import java.net.URL

class EventsListActivity : AppCompatActivity() {
    var eventsList = mutableListOf<Event>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.event_layout)
        getEvents()
    }

    private fun onEventsReady(){
        Log.d("TAG", eventsList.toString())
//        loadImage(imageView, "http://student.agh.edu.pl/~bociepka/randnarok/cooking.jpg")
    }

    private fun loadImage(imageView: ImageView, url: String){
        val myThread = Thread {

            val url = URL(url)
            val fullBitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            runOnUiThread { imageView.setImageBitmap(fullBitmap) }
        }
        myThread.start()
    }

    private fun getEvents() {
        eventsList.removeAll{true}
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
                    eventsList.add(departure)
                }
                onEventsReady()
            }, Response.ErrorListener {Log.e("Volley error", it.toString())
            }) {}
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(directionsRequest)
    }

    fun test(){
        val intent = Intent(this, EventActivity::class.java).apply {
            putExtra("name", "aaaaaaaa")
            putExtra("date", "bbbbaaaa")
            putExtra("price", "ddddaaaa")
            putExtra("time", "bbbbaaaa")
            putExtra("location", "wololo")
            putExtra("description", "ccccaaaa")
        }
        startActivity(intent)
    }
}