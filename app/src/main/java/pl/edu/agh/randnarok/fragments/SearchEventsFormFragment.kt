package pl.edu.agh.randnarok.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import pl.edu.agh.randnarok.EventsListActivity
import pl.edu.agh.randnarok.R

/**
 * A simple [Fragment] subclass.
 * Use the [SearchEventsFormFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchEventsFormFragment : Fragment() {

    private lateinit var locationEdit: EditText
    private lateinit var dateFromEdit: EditText
    private lateinit var dateToEdit: EditText
    private lateinit var priceFromEdit: EditText
    private lateinit var priceToEdit: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_events_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        locationEdit = view.findViewById(R.id.search_events_form_location_edit_text)
        dateFromEdit = view.findViewById(R.id.search_events_form_date_from_edit_text)
        dateToEdit = view.findViewById(R.id.search_events_form_date_to_edit_text)
        priceFromEdit = view.findViewById(R.id.search_events_form_price_from_edit_text)
        priceToEdit = view.findViewById(R.id.search_events_form_price_to_edit_text)

    }

    fun openEventsListActivity(view: View) {
        val intent = Intent(activity, EventsListActivity::class.java)
        intent.putExtra("SEARCH_EVENTS_FORM_LOCATION", locationEdit.text.toString())
        intent.putExtra("SEARCH_EVENTS_FORM_DATE_FROM", dateFromEdit.text.toString())
        intent.putExtra("SEARCH_EVENTS_FORM_DATE_TO", dateFromEdit.text.toString())
        intent.putExtra(
            "SEARCH_EVENTS_FORM_PRICE_FROM",
            Integer.parseInt(priceFromEdit.text.toString())
        )
        intent.putExtra("SEARCH_EVENTS_FORM_PRICE_TO", priceToEdit.text.toString())
        startActivity(intent)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EventsFragment().apply {
                arguments = Bundle().apply { }
            }
    }

}