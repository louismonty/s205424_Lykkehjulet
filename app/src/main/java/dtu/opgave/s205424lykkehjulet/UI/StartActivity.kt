package dtu.opgave.s205424lykkehjulet.UI

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import dtu.opgave.s205424lykkehjulet.R

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        //https://developer.android.com/guide/topics/ui/controls/spinner
        val spinner: Spinner = findViewById(R.id.spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.Category,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        val sharedpreference = getSharedPreferences("test", Context.MODE_PRIVATE)
        val button: Button = findViewById(R.id.button2)
        //sets data to null
        sharedpreference.edit().putInt("score",0).apply()
        sharedpreference.edit().putString("data",null).apply()
        sharedpreference.edit().putString("lives",null).apply()
        button.setOnClickListener {

            sharedpreference.edit().putString("category",spinner.selectedItem.toString()).apply()

            startActivity(Intent(this, FramgentContainerActivity::class.java))
        }



    }
}