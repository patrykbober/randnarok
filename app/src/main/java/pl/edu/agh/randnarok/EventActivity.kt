package pl.edu.agh.randnarok

import android.content.res.Resources
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.event_layout.*
import java.net.URI

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
        val picture = intent.getStringExtra("picture")

        event_name.text = name
        event_date.text = date
        event_time.text = time
        event_price.text = price
        event_location.text = location
        event_description.text = description

        var photo = imageView
        
        pay_button.visibility = View.VISIBLE


        var uri = URI.create(picture);
        var path = uri.getPath();
        path = path.substring(path.lastIndexOf('/') + 1);
        path = path.substring(0, path.length - 4).toLowerCase()

        val resources: Resources = this.resources
        val resourceId: Int = resources.getIdentifier(
            path, "drawable",
            this.packageName
        )

        val icon = BitmapFactory.decodeResource(
            this.resources,
            resourceId
        )
        photo.setImageBitmap(icon)
    }
}