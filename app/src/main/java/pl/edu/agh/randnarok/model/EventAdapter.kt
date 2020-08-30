package pl.edu.agh.randnarok.model

import android.R.drawable
import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import pl.edu.agh.randnarok.R
import java.util.*


class EventAdapter(
    @param:NonNull private val mContext: Context,
    list: ArrayList<Event>
) :
    ArrayAdapter<Event?>(mContext, 0, list as List<Event?>) {
    private var eventList: List<Event> = ArrayList<Event>()

    @NonNull
    override fun getView(
        position: Int,
        @Nullable convertView: View?,
        @NonNull parent: ViewGroup
    ): View {
        var listItem = convertView
        if (listItem == null) listItem =
            LayoutInflater.from(mContext).inflate(R.layout.listview_event, parent, false)
        val currentEvent: Event = eventList[position]
        val title = listItem!!.findViewById<View>(R.id.title_text) as TextView
        title.setText(currentEvent.name)
        val author =
            listItem.findViewById<View>(R.id.author_text) as TextView
        author.setText(currentEvent.address)
        val category =
            listItem.findViewById<View>(R.id.category_txt) as TextView
        category.setText(currentEvent.city)
        val price = listItem.findViewById<View>(R.id.price_text) as TextView
        price.text = currentEvent.date
        val photo =
            listItem.findViewById<View>(R.id.event_image) as ImageView

        when {
            currentEvent.name.contains("ang") -> {
                val icon = BitmapFactory.decodeResource(
                    context.resources,
                    R.drawable.union_jack
                )
                photo.setImageBitmap(icon)
            }
            currentEvent.name.contains("tań") -> {
                val icon = BitmapFactory.decodeResource(
                    context.resources,
                    R.drawable.dance
                )
                photo.setImageBitmap(icon)
            }
            currentEvent.name.contains("gar") -> {
                val icon = BitmapFactory.decodeResource(
                    context.resources,
                    R.drawable.garncarstwo
                )
                photo.setImageBitmap(icon)
            }
            currentEvent.name.contains("bungee") -> {
                val icon = BitmapFactory.decodeResource(
                    context.resources,
                    R.drawable.bungee
                )
                photo.setImageBitmap(icon)
            }
            currentEvent.name.contains("got") -> {
                val icon = BitmapFactory.decodeResource(
                    context.resources,
                    R.drawable.cooking
                )
                photo.setImageBitmap(icon)
            }
            currentEvent.name.toLowerCase().contains("surprise") -> {
                val icon = BitmapFactory.decodeResource(
                    context.resources,
                    R.drawable.question_mark_wtf
                )
                photo.setImageBitmap(icon)
            }
        }

        return listItem
    }

    init {
        eventList = list
    }
}