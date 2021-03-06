package pl.edu.agh.randnarok.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import pl.edu.agh.randnarok.ChoosePreferencesActivity
import pl.edu.agh.randnarok.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        toolbar_title.text = "Sign up / sign in"
    }

    fun openMainActivity(view: View) {
        val choosePreferencesActivity = Intent(this, ChoosePreferencesActivity::class.java)
        startActivity(choosePreferencesActivity)
        finish()


    }
}