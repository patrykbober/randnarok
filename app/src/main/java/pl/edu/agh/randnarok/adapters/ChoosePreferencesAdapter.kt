package pl.edu.agh.randnarok.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.edu.agh.randnarok.ChoosePreferencesActivity
import pl.edu.agh.randnarok.R
import pl.edu.agh.randnarok.model.Event

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

        holder.bind(event)


    }

    class ChoosePreferencesViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val eventName: TextView = itemView.findViewById(R.id.choose_preferences_event_name)
        private val eventDescription: TextView =
            itemView.findViewById(R.id.choose_preferences_event_description)
        private val photo: ImageView = itemView.findViewById(R.id.choose_preferences_image)

        fun bind(event: Event) {
            eventName.text = event.name
            eventDescription.text = event.desc
            //TODO set photo
        }
    }

}
//
//    ArrayAdapter<Event?>(mContext, 0, list as List<Event?>) {
//    private var eventList: List<Event> = ArrayList()
//
//    init {
//        eventList = list
//    }
//
//    @NonNull
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        var listItem = convertView
//        if (listItem == null) {
//            listItem = LayoutInflater.from(mContext).inflate(R.layout.listitem_choose_preferences, parent, false)
//        }
//        val currentEvent: Event = eventList[position]
//        val eventName = listItem!!.findViewById<View>(R.id.choose_preferences_event_name) as TextView
//        eventName.text = currentEvent.name
//        val description = listItem.findViewById<View>(R.id.choose_preferences_event_description) as TextView
//        description.text = currentEvent.desc
//        val photo = listItem.findViewById<View>(R.id.choose_preferences_image) as ImageView
//
//        when {
//            currentEvent.name.contains("ang") -> {
//                val icon = BitmapFactory.decodeResource(
//                    context.resources,
//                    R.drawable.union_jack
//                )
//                photo.setImageBitmap(icon)
//            }
//            currentEvent.name.contains("tań") -> {
//                val icon = BitmapFactory.decodeResource(
//                    context.resources,
//                    R.drawable.dance
//                )
//                photo.setImageBitmap(icon)
//            }
//            currentEvent.name.contains("gar") -> {
//                val icon = BitmapFactory.decodeResource(
//                    context.resources,
//                    R.drawable.garncarstwo
//                )
//                photo.setImageBitmap(icon)
//            }
//            currentEvent.name.contains("bungee") -> {
//                val icon = BitmapFactory.decodeResource(
//                    context.resources,
//                    R.drawable.bungee
//                )
//                photo.setImageBitmap(icon)
//            }
//            currentEvent.name.contains("got") -> {
//                val icon = BitmapFactory.decodeResource(
//                    context.resources,
//                    R.drawable.cooking
//                )
//                photo.setImageBitmap(icon)
//            }
//            currentEvent.name.toLowerCase().contains("surprise") -> {
//                val icon = BitmapFactory.decodeResource(
//                    context.resources,
//                    R.drawable.question_mark_wtf
//                )
//                photo.setImageBitmap(icon)
//            }
//        }
//
//        return listItem
//    }
//}