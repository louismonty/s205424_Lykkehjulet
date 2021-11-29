package dtu.opgave.s205424lykkehjulet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dtu.opgave.s205424lykkehjulet.UI.StartActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, StartActivity::class.java))




    }

    }
