package dtu.opgave.s205424lykkehjulet.UI

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import dtu.opgave.s205424lykkehjulet.Adapter.HeartAdapter
import dtu.opgave.s205424lykkehjulet.Adapter.HighscoreAdapter
import dtu.opgave.s205424lykkehjulet.Model.HighscoreModel
import dtu.opgave.s205424lykkehjulet.Model.HighscoreModelCollection
import dtu.opgave.s205424lykkehjulet.Model.WordModelCollection
import dtu.opgave.s205424lykkehjulet.R


class HighScoreFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_high_score, container, false)

        val sharedPreferences: SharedPreferences = view.context.getSharedPreferences("test", Context.MODE_PRIVATE)


        val gson:Gson = Gson();
        val json = sharedPreferences.getString("highScore","")
        val data = gson.fromJson<HighscoreModelCollection>(json,HighscoreModelCollection::class.java)


        if(data == null) {
            createData(sharedPreferences)
        }

        createHighScore(view,data)

        return view




}
    private fun createData(sharedPreferences: SharedPreferences){
        val data = HighscoreModelCollection(arrayListOf<HighscoreModel>(
            HighscoreModel("Louis",100),
            HighscoreModel("Louis",1000)
        ))

        val editor = sharedPreferences.edit();
        val gson: Gson = Gson()
        val json:String = gson.toJson(data);
        editor.putString("highScore", json);
        editor.commit();
    }

    private fun UpdateData(sharedPreferences: SharedPreferences, data: HighscoreModelCollection){
        val editor = sharedPreferences.edit();
        val gson: Gson = Gson()
        val json:String = gson.toJson(data);
        editor.putString("highScore", json);
        editor.commit();
    }

    //creates recycle view
    private fun createHighScore(view: View,data: HighscoreModelCollection){

        val Highscore_recyclerView = view.findViewById<RecyclerView>(R.id.highscore_recycleview)

        Highscore_recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val adapter = HighscoreAdapter(data.list)

        Highscore_recyclerView.adapter = adapter
    }
}