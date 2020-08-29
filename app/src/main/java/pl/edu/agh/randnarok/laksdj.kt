package com.agh.edu.pankracy.adapters
//import com.agh.edu.pankracy.data.PlantContract;
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import pl.edu.agh.randnarok.R
import java.sql.SQLException
import java.text.ParseException
import java.util.*

class PlantListAdapter(
    private val context: Context
) : BaseAdapter() {
    private val mData: LinkedHashMap<Int, Int>
    private val mKeys: Array<Int>

    private inner class ViewHolder {
        var icon: ImageView? = null
        var waterIcon: ImageView? = null
        var isOutsideIcon: ImageView? = null
        var title: TextView? = null
    }

    override fun getCount(): Int {
        return mData.size
    }

    override fun getItem(position: Int): Int {
        return mData[mKeys[position]]!!
    }

    override fun getItemId(position: Int): Long {
        return mKeys[position].toLong()
    }

    override fun getView(
        position: Int,
        convertView: View,
        parent: ViewGroup
    ): View {
        var convertView = convertView
        var holder: ViewHolder? = null
        val mInflater =
            context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_plant, null)
            holder = ViewHolder()
            holder.title = convertView.findViewById(R.id.title)
            holder.icon = convertView.findViewById(R.id.icon)
            holder.waterIcon =
                convertView.findViewById(R.id.water_it_icon)
            holder.isOutsideIcon =
                convertView.findViewById(R.id.is_outside_icon)
            convertView.tag = holder
        } else {
            holder =
                convertView.tag as ViewHolder
        }
        val plant: Plant = getItem(position)
        holder.title.setText(plant.getName())
        try {
            if (plant.getLastWatering() != null && DateUtils.getNumberOfDaysBetweenGivenDateAndNextWatering(
                    Date(),
                    DateUtils.sdf.parse(plant.getLastWatering()),
                    plant.getWatering()
                ) < 0
            ) {
                //changeColor
                holder!!.icon!!.setColorFilter(
                    ContextCompat.getColor(
                        context,
                        R.color.colorRedDimmed
                    )
                )
                holder.waterIcon!!.setColorFilter(
                    ContextCompat.getColor(
                        context,
                        R.color.colorRedDimmed
                    )
                )
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        if (plant.getIsOutside() === 0) {
            holder!!.isOutsideIcon!!.setColorFilter(
                ContextCompat.getColor(
                    context,
                    R.color.colorPrimary
                )
            )
        }
        holder!!.isOutsideIcon!!.setOnClickListener {
            plant.setIsOutside(if (plant.getIsOutside() === 0) 1 else 0)
            try {
                dbHelper.createOrUpdate(plant)
            } catch (e: SQLException) {
                e.printStackTrace()
            }
            lopFragment.listGetter()
            lopFragment.chosenPlantIntentCreator()
        }
        holder.waterIcon!!.setOnClickListener {
            plant.setLastWatering(DateUtils.sdf.format(Date()))
            try {
                dbHelper.createOrUpdate(plant)
            } catch (e: SQLException) {
                e.printStackTrace()
            }
            lopFragment.listGetter()
            lopFragment.chosenPlantIntentCreator()
        }
        return convertView
    }

    init {
        dbHelper = DBHelper(context)
        mData = items
        mKeys = mData.keys.toTypedArray()
        this.lopFragment = lopFragment
    }
}