package pl.edu.agh.randnarok

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import pl.edu.agh.randnarok.fragments.EventsFragment
import pl.edu.agh.randnarok.fragments.SearchEventsFormFragment
import pl.edu.agh.randnarok.fragments.SettingsFragment


class MainActivity : AppCompatActivity() {
    var bottomNavigation: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation = findViewById(R.id.bottom_nav)
        openFragment(EventsFragment.newInstance(), false)
        bottomNavigation!!.setOnNavigationItemSelectedListener(navigationItemSelectedListener)

    }

    @SuppressLint("SourceLockedOrientationActivity")
    fun openFragment(
        fragment: Fragment?,
        twoOrientations: Boolean
    ) {
        val transaction =
            supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment!!)
        transaction.addToBackStack(null)
        requestedOrientation =
            if (twoOrientations) ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED else ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        transaction.commit()
    }

    private var navigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_events -> {
                    openFragment(EventsFragment.newInstance(), false)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_form -> {
                    openFragment(SearchEventsFormFragment.newInstance(), false)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_settings -> {
                    openFragment(SettingsFragment.newInstance(), false)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
}