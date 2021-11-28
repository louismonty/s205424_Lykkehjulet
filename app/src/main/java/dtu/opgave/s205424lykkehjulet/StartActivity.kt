package dtu.opgave.s205424lykkehjulet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        val button: Button = findViewById(R.id.button2)
        button.setOnClickListener {
            startActivity(Intent(this, FramgentContainerActivity::class.java))
        }


    }
}