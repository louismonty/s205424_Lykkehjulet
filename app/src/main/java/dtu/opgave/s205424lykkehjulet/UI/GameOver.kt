package dtu.opgave.s205424lykkehjulet.UI

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import dtu.opgave.s205424lykkehjulet.Adapter.HighscoreAdapter
import dtu.opgave.s205424lykkehjulet.Model.HighscoreModel
import dtu.opgave.s205424lykkehjulet.Model.HighscoreModelCollection
import dtu.opgave.s205424lykkehjulet.R
import dtu.opgave.s205424lykkehjulet.UI.StartActivity
import org.w3c.dom.Text

class GameOver : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        //checks if a score has been updated
        var hasScoreChange = true

        //loads score
        val sharedpreference = getSharedPreferences("test", Context.MODE_PRIVATE)
        val score = sharedpreference.getInt("score", 0)

        val hisgscoreText = findViewById<TextView>(R.id.textView2)
        hisgscoreText.text = "Highscore : "+ score

        //loads highscore as a objekt gson
        val gson:Gson = Gson();
        val json = sharedpreference.getString("highScore","")
        val data = gson.fromJson<HighscoreModelCollection>(json,HighscoreModelCollection::class.java)

        //if no data creates the data from scratch
        if(data == null) {
            createData(sharedpreference)
        }

        //if click restarts the gamer
        val button: Button =  findViewById(R.id.restartGame)
        createHighScore(data )
        button.setOnClickListener {
            startActivity(Intent(this, StartActivity::class.java))
        }

        //takes the name enteret and shows
        val textIpunt: EditText = findViewById(R.id.name)
        val AddHighScore:Button = findViewById(R.id.addhighscore)
        AddHighScore.setOnClickListener {
            if (hasScoreChange) {
                val name = textIpunt.text.toString()
                val score = sharedpreference.getInt("score", 0)
                data.list.add(HighscoreModel(name, score))
                //recreates recycle view
                createHighScore(data)
                UpdateData(sharedpreference,data)
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

    //creates recycle view
    private fun createHighScore(data: HighscoreModelCollection){
        val today_event_recyclerview = findViewById<RecyclerView>(R.id.highscore)

        today_event_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val adapter = HighscoreAdapter(data.list)

        today_event_recyclerview.adapter = adapter
    }
}