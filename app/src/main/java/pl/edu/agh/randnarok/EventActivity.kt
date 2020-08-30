package pl.edu.agh.randnarok

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.event_layout.*

class EventActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_layout)
        setFieldValues()
        pay_button.setOnClickListener { pay_button.visibility = View.INVISIBLE }
    }

    fun setFieldValues(){
        val name = intent.getStringExtra("name")
        val date = intent.getStringExtra("date")
        val time = intent.getStringExtra("time")
        val price = intent.getStringExtra("price")
        val location = intent.getStringExtra("location")
        val description = intent.getStringExtra("description")

        event_name.text = name
        event_date.text = date
        event_time.text = time
        event_price.text = price
        event_location.text = location
        event_description.text = description

        var photo = imageView
        
        pay_button.visibility = View.VISIBLE


        when {
            name.contains("ang") -> {
                val icon = BitmapFactory.decodeResource(
                    this.resources,
                    R.drawable.union_jack
                )
                photo.setImageBitmap(icon)
            }
            name.contains("tań") -> {
                val icon = BitmapFactory.decodeResource(
                    this.resources,
                    R.drawable.dance
                )
                photo.setImageBitmap(icon)
            }
            name.contains("gar") -> {
                val icon = BitmapFactory.decodeResource(
                    this.resources,
                    R.drawable.garncarstwo
                )
                photo.setImageBitmap(icon)
            }
            name.contains("bungee") -> {
                val icon = BitmapFactory.decodeResource(
                    this.resources,
                    R.drawable.bungee
                )
                photo.setImageBitmap(icon)
            }
            name.contains("got") -> {
                val icon = BitmapFactory.decodeResource(
                    this.resources,
                    R.drawable.cooking
                )
                photo.setImageBitmap(icon)
            }
            name.toLowerCase().contains("surprise") -> {
                val icon = BitmapFactory.decodeResource(
                    this.resources,
                    R.drawable.question_mark_wtf
                )
                photo.setImageBitmap(icon)
            }
        }
    }
}