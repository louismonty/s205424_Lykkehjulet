package dtu.opgave.s205424lykkehjulet

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import dtu.opgave.s205424lykkehjulet.Adapter.HeartAdapter
import dtu.opgave.s205424lykkehjulet.Adapter.HighscoreAdapter
import dtu.opgave.s205424lykkehjulet.Model.HighscoreModel
import dtu.opgave.s205424lykkehjulet.Model.HighscoreModelCollection
import dtu.opgave.s205424lykkehjulet.Model.WordModel
import java.util.ArrayList

class GameOver : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        var hasScoreChange = true

        val sharedpreference = getSharedPreferences("test", Context.MODE_PRIVATE)


        val gson:Gson = Gson();
        val json = sharedpreference.getString("highScore","")
        val data = gson.fromJson<HighscoreModelCollection>(json,HighscoreModelCollection::class.java)
        createData(sharedpreference)
        if(data == null) {
            createData(sharedpreference)
        }



        val button: Button =  findViewById(R.id.restartGame)
        createHighScore(data )
        button.setOnClickListener {
            startActivity(Intent(this, StartActivity::class.java))
        }

        val textIpunt: EditText = findViewById(R.id.name)
        val AddHighScore:Button = findViewById(R.id.addhighscore)
        AddHighScore.setOnClickListener {
            if (hasScoreChange) {
                val name = textIpunt.text.toString()
                val score = sharedpreference.getInt("Score", 0)
                data.list.add(HighscoreModel(name, score))
                createHighScore(data)
                hasScoreChange = false
            }
        }


    }

    private fun createData(sharedPreferences: SharedPreferences){
        val data = HighscoreModelCollection(arrayListOf<HighscoreModel>(HighscoreModel("Louis",100),HighscoreModel("Louis",1000)))

        val editor = sharedPreferences.edit();
        val gson:Gson = Gson()
        val json:String = gson.toJson(data);
        editor.putString("highScore", json);
        editor.commit();
    }

    private fun UpdateData(sharedPreferences: SharedPreferences,data:HighscoreModelCollection){
        val editor = sharedPreferences.edit();
        val gson:Gson = Gson()
        val json:String = gson.toJson(data);
        editor.putString("highScore", json);
        editor.commit();
    }

    private fun createHighScore(data: HighscoreModelCollection){


        val today_event_recyclerview = findViewById<RecyclerView>(R.id.highscore)

        today_event_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        val adapter = HighscoreAdapter(data.list)

        today_event_recyclerview.adapter = adapter
    }
}