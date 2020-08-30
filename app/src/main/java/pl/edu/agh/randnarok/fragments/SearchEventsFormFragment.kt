package pl.edu.agh.randnarok.fragments

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_search_events_form.*
import pl.edu.agh.randnarok.EventsListActivity
import pl.edu.agh.randnarok.R
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [SearchEventsFormFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchEventsFormFragment : Fragment() {

    private lateinit var dateFromPickerListener: OnDateSetListener
    private lateinit var dateToPickerListener: OnDateSetListener

    private lateinit var locationEdit: EditText
    private lateinit var dateFromEdit: EditText
    private lateinit var dateToEdit: EditText
    private lateinit var priceFromEdit: EditText
    private lateinit var priceToEdit: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity!!.toolbar_title.text = "Search for events"
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_events_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        locationEdit = view.findViewById(R.id.search_events_form_location_edit_text)
        dateFromEdit = view.findViewById(R.id.search_events_form_date_from_edit_text)
        dateToEdit = view.findViewById(R.id.search_events_form_date_to_edit_text)
//        priceFromEdit = view.findViewById(R.id.search_events_form_price_from_edit_text)
        priceFromEdit = search_events_form_price_from_edit_text
//        priceToEdit = view.findViewById(R.id.search_events_form_price_to_edit_text)
        priceToEdit = search_events_form_price_to_edit_text

        setButtonListener()
        setDatePickerOpener()
    }

    fun setButtonListener() {
        search_events_form_submit_button.setOnClickListener(View.OnClickListener {
            openEventsListActivity(it)
        })
    }

    fun setDatePickerOpener() {
        dateFromPickerListener =
            OnDateSetListener { view, year, month, dayOfMonth ->
                var month = month
                month += 1
                var monthText = month.toString() + ""
                if (month < 10) monthText = "0$monthText"
                dateFromEdit.setText("$dayOfMonth.$monthText.$year")
            }
        dateToPickerListener =
            OnDateSetListener { view, year, month, dayOfMonth ->
                var month = month
                month += 1
                var monthText = month.toString() + ""
                if (month < 10) monthText = "0$monthText"
                dateToEdit.setText("$dayOfMonth.$monthText.$year")
            }
        dateFromEdit.setOnClickListener(View.OnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar[Calendar.YEAR]
            val month = calendar[Calendar.MONTH]
            val day = calendar[Calendar.DAY_OF_MONTH]
            val dialog = DatePickerDialog(
                activity!!,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                dateFromPickerListener,
                year, month, day
            )
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
        })
        dateToEdit.setOnClickListener(View.OnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar[Calendar.YEAR]
            val month = calendar[Calendar.MONTH]
            val day = calendar[Calendar.DAY_OF_MONTH]
            val dialog = DatePickerDialog(
                activity!!,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                dateToPickerListener,
                year, month, day
            )
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
        })
    }

    fun openEventsListActivity(view: View) {
        val intent = Intent(activity, EventsListActivity::class.java)
        intent.putExtra("SEARCH_EVENTS_FORM_LOCATION", locationEdit.text.toString())
        intent.putExtra("SEARCH_EVENTS_FORM_DATE_FROM", dateFromEdit.text.toString())
        intent.putExtra("SEARCH_EVENTS_FORM_DATE_TO", dateFromEdit.text.toString())
        intent.putExtra("SEARCH_EVENTS_FORM_PRICE_FROM", priceFromEdit.text.toString())
        intent.putExtra("SEARCH_EVENTS_FORM_PRICE_TO", priceToEdit.text.toString())
        startActivity(intent)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EventsFragment().apply {
                arguments = Bundle().apply { }
            }

        @JvmStatic
        fun newInstance () = SearchEventsFormFragment()
    }

}