package dtu.opgave.s205424lykkehjulet

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import dtu.opgave.s205424lykkehjulet.Adapter.HeartAdapter
import dtu.opgave.s205424lykkehjulet.Adapter.WordAdapter
import dtu.opgave.s205424lykkehjulet.Logic.Guees
import dtu.opgave.s205424lykkehjulet.Logic.RadomWord
import dtu.opgave.s205424lykkehjulet.Logic.Wheel
import dtu.opgave.s205424lykkehjulet.Model.WordModel
import dtu.opgave.s205424lykkehjulet.Model.WordModelCollection
import dtu.opgave.s205424lykkehjulet.UI.GameOver
import dtu.opgave.s205424lykkehjulet.View.WordViewModel
import kotlin.collections.ArrayList

class GameFragment : Fragment() {

    private var hasSpun = false
    private var reward:Int = 0

    private lateinit var data:WordModelCollection
    private lateinit var lives:WordModelCollection
    private var lives_collection = ArrayList<WordModel>()
    private var data_word = ArrayList<WordModel>()
    private val viewModel: WordViewModel by viewModels()

    private val wheel:Wheel = Wheel()
    private val randomWordclas:RadomWord = RadomWord()

    private  val guess:Guees = Guees()

    val gson:Gson = Gson();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        // Inflate the layout for gameFragment
        val view = inflater.inflate(R.layout.fragment_game, container, false)

        //shared preferences to store data
        val sharedPreferences: SharedPreferences = view.context.getSharedPreferences("test", Context.MODE_PRIVATE)
        //shared preferences to edit when data needs to be added
        val editor = sharedPreferences.edit()
        //finds the category thats stred sharedPreferences
        val category = sharedPreferences.getString("category","")
        //sets the text to show category
        view.findViewById<TextView>(R.id.gameText).text = "category : "+category

        //gets random words from catogory
        var randomWord = randomWordclas.findRandomWord(category!!,resources)!!


        var json = sharedPreferences.getString("data",null)
        if (json == null){
            createData(sharedPreferences,randomWord)
            json = sharedPreferences.getString("data",null)
        }
        data = gson.fromJson<WordModelCollection>(json,WordModelCollection::class.java)


        json = sharedPreferences.getString("lives",null)
        if (json == null){
            createHearts(sharedPreferences)
            UpdateHearts(sharedPreferences)
            json = sharedPreferences.getString("lives",null)
        }
        lives = gson.fromJson<WordModelCollection>(json,WordModelCollection::class.java)


        //if no data creates a new word
        if(data == null){
            val res: Resources = resources

        }

        //gets score
        viewModel.score.value = sharedPreferences.getInt("score",0)

        
        //makes score live data
        viewModel.score.observe(viewLifecycleOwner,androidx.lifecycle.Observer { newInt->
            view.findViewById<TextView>(R.id.score).text ="score" + newInt.toString()
            editor?.apply {
                putInt("score", newInt!!)
            }?.apply()
        })

        //creates model to make recycle view (letter wont be shown)



        //spins the wheel and show text
        val spinButton:Button = view.findViewById(R.id.spin)
        spinButton.setOnClickListener{
            if(!hasSpun){
                reward = wheel.spinwhele(view,viewModel,lives.list)
                if(reward !=0) {
                    hasSpun = true
                }
            }
        }

        //checks a guees
        val button:Button = view.findViewById(R.id.button)
        button.setOnClickListener{
            if (hasSpun) {

                guess.Guess(view,viewModel,lives.list,data.list,reward)

                var guessAll = true
                if (lives.list.size == 0) {
                    //saves score and start gameover activity
                    sharedPreferences.edit().putInt("score",viewModel.score.value!!).apply()
                    val intent = Intent(getActivity(), GameOver::class.java)
                    getActivity()?.startActivity(intent)


                }
                //checks if all letters are guessed
                for (letter in data.list){
                    if(!letter.visablity){
                        guessAll = false
                    }
                }
                //if all words are guessed finds new word
                if(guessAll){
                    randomWord = randomWordclas.findRandomWord(category!!,resources)!!
                    createData(sharedPreferences,randomWord)
                }
                //recreates recycle view
                UpdateData(sharedPreferences,data)
                UpdateHearts(sharedPreferences)
                createHeartsView(view)
                createWordView(view,randomWord)
                hasSpun =false
            }

        }


        //creates recycle view
        createWordView(view, randomWord)
        createHeartsView(view)


        return view
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //saves data
        outState.putInt("score",viewModel.score.value!!)


        val json:String = gson.toJson(data);
        outState.putString("highScore", json);


    }



    private fun createWordView(view: View,randomWord:String){

        val Word_recyclerview = view.findViewById<RecyclerView>(R.id.displayText)

        Word_recyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val adapter = WordAdapter(data.list)

        Word_recyclerview.adapter = adapter
    }



    private fun createHeartsView(view: View){

        val Heart_recyclerview = view.findViewById<RecyclerView>(R.id.lives)

        Heart_recyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val adapter = HeartAdapter(lives.list)

        Heart_recyclerview.adapter = adapter
    }


    private fun createData(sharedPreferences: SharedPreferences,randomWord: String){
        data_word = ArrayList<WordModel>()
        for(letter in randomWord) {
            data_word.add(WordModel(letter, false))
        }
        data = WordModelCollection(
            data_word
        )

        val editor = sharedPreferences.edit();
        val json:String = gson.toJson(data);
        editor.putString("data", json);
        editor.commit();
    }

    private fun UpdateData(sharedPreferences: SharedPreferences,data:WordModelCollection){
        val editor = sharedPreferences.edit();
        val json:String = gson.toJson(data);
        editor.putString("data", json);
        editor.commit();
    }


    private fun createHearts(sharedPreferences: SharedPreferences){
        lives_collection = ArrayList<WordModel>()

        lives_collection.add(WordModel('t',true))
        lives_collection.add(WordModel('t',true))
        lives_collection.add(WordModel('t',true))
        lives_collection.add(WordModel('t',true))
        lives_collection.add(WordModel('t',true))

        lives = WordModelCollection(
            lives_collection
        )

        val editor = sharedPreferences.edit();
        val json:String = gson.toJson(lives);
        editor.putString("lives", json);
        editor.commit();
    }

    private fun UpdateHearts(sharedPreferences: SharedPreferences){
        val editor = sharedPreferences.edit();
        val json:String = gson.toJson(lives);
        editor.putString("lives", json);
        editor.commit();
    }




}