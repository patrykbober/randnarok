package pl.edu.agh.randnarok.model

import android.R.attr.name
import android.content.Context
import android.content.res.Resources
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
import java.net.URI
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

        var uri = URI.create(currentEvent.picture);
        var path = uri.getPath();
        path = path.substring(path.lastIndexOf('/') + 1);
        path = path.substring(0, path.length - 4).toLowerCase()

        val resources: Resources = context.resources
        val resourceId: Int = resources.getIdentifier(
            path, "drawable",
            context.packageName
        )

        val icon = BitmapFactory.decodeResource(
            context.resources,
            resourceId
        )
        photo.setImageBitmap(icon)

        return listItem
    }

    init {
        eventList = list
    }
}