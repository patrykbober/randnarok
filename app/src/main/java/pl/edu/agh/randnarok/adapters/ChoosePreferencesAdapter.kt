package pl.edu.agh.randnarok.adapters

import android.content.Context
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.edu.agh.randnarok.ChoosePreferencesActivity
import pl.edu.agh.randnarok.R
import pl.edu.agh.randnarok.model.Event
import java.net.URI

class ChoosePreferencesAdapter(
    private val activity: ChoosePreferencesActivity,
    private val list: ArrayList<Event>
) : RecyclerView.Adapter<ChoosePreferencesAdapter.ChoosePreferencesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoosePreferencesViewHolder {
        val listItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.listitem_choose_preferences, parent, false) as View

        return ChoosePreferencesViewHolder(listItem)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ChoosePreferencesViewHolder, position: Int) {
        val event: Event = list[position]

        holder.bind(event, activity)


    }

    class ChoosePreferencesViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val eventName: TextView = itemView.findViewById(R.id.choose_preferences_event_name)
        private val eventDescription: TextView =
            itemView.findViewById(R.id.choose_preferences_event_description)
        private val eventImage = itemView.findViewById<ImageView>(R.id.choose_preferences_image)
        private val photo: ImageView = itemView.findViewById(R.id.choose_preferences_image)

        fun bind(event: Event, context: Context) {
            eventName.text = event.name
            eventDescription.text = event.desc

            var uri = URI.create(event.picture);
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
            eventImage.setImageBitmap(icon)
        }
    }
}