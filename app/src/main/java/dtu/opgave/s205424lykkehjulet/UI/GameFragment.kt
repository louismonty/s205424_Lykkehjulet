package dtu.opgave.s205424lykkehjulet

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.view.get
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dtu.opgave.s205424lykkehjulet.Adapter.HeartAdapter
import dtu.opgave.s205424lykkehjulet.Adapter.WordAdapter
import dtu.opgave.s205424lykkehjulet.Model.WordModel
import dtu.opgave.s205424lykkehjulet.View.WordViewModel
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class GameFragment : Fragment() {

    var hasSpun = false
    var reward:Int = 0

    var data = ArrayList<WordModel>()
    private val viewModel: WordViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_game, container, false)

        val sharedPreferences: SharedPreferences = view.context.getSharedPreferences("test", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val category = sharedPreferences.getString("category","")
        view.findViewById<TextView>(R.id.gameText).text = category

        var randomWord = findRandomWord(category!!)!!

        if(data.size == 0){
            createData(randomWord)
        }

        viewModel.score.value = sharedPreferences.getInt("score",0)

        

        viewModel.score.observe(viewLifecycleOwner,androidx.lifecycle.Observer { newInt->
            view.findViewById<TextView>(R.id.score).text ="score" + newInt.toString()
            editor?.apply {
                putInt("score", newInt!!)
            }?.apply()
        })


        lives.add(WordModel('t',true))
        lives.add(WordModel('t',true))
        lives.add(WordModel('t',true))
        lives.add(WordModel('t',true))
        lives.add(WordModel('t',true))


        val spinButton:Button = view.findViewById(R.id.spin)
        spinButton.setOnClickListener{
            if(!hasSpun){
                reward = spinwhele(view)
                if(reward !=0) {
                    hasSpun = true
                }
            }
        }


        val button:Button = view.findViewById(R.id.button)
        button.setOnClickListener{
            if (hasSpun){
                var guessRight = false
                var guessAll = true
                for (letter in data) {
                    if(!letter.visablity) {
                        if (letter.letter.toString() == view.findViewById<EditText>(R.id.editTextTextPersonName).text.toString()) {
                            viewModel.score.value = viewModel.score.value!! + reward
                            letter.visablity = true;
                            guessRight = true
                        }
                    }
                }
                if (!guessRight) {
                    lives.removeAt(lives.size - 1)
                }
                for (letter in data){
                    if(!letter.visablity){
                        guessAll = false
                    }
                }
                if(guessAll){
                    randomWord = findRandomWord(category!!)!!
                    createData(randomWord)
                    createTodayView(view,randomWord)
                }
                createTodayView(view, randomWord)
                createHearts(view)
                hasSpun = false
            }
            if(lives.size == 0){
                val intent = Intent (getActivity(), GameOver::class.java)
                getActivity()?.startActivity(intent)
            }
        }



        createTodayView(view, randomWord)
        createHearts(view)


        return view
    }


    override fun onSaveInstanceState(outState: Bundle) { // Here You have to save count value
        super.onSaveInstanceState(outState)
        outState.putInt("score",viewModel.score.value!!)


    }



    private fun createTodayView(view: View,randomWord:String){

        val today_event_recyclerview = view.findViewById<RecyclerView>(R.id.displayText)

        today_event_recyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)




        val adapter = WordAdapter(data)

        today_event_recyclerview.adapter = adapter
    }

    val lives = ArrayList<WordModel>()

    private fun createHearts(view: View){

        val today_event_recyclerview = view.findViewById<RecyclerView>(R.id.lives)

        today_event_recyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        val adapter = HeartAdapter(lives)

        today_event_recyclerview.adapter = adapter
    }


    fun findRandomWord( stringCategory: String): String? {
        var stringArray:Array<String>
        val res: Resources = resources
        if(stringCategory == "planets_array") {
            stringArray = res.getStringArray(R.array.planets_array)
        }else{
            stringArray = res.getStringArray(R.array.some_shit)
        }


        return  stringArray[Random.nextInt(0, stringArray.size)]
    }

    fun createData(randomWord:String) {
        data = ArrayList<WordModel>()
        for(letter in randomWord) {
            data.add(WordModel(letter, false))
        }

    }
    fun spinwhele(view:View):Int{
       val spin = Random.nextInt(1,14)
        var ret:Int
        if(spin<11){
            view.findViewById<TextView>(R.id.gameText).text =   "Du får:" + (spin*100).toString()
            ret = spin *100
        }else if(spin == 11){
            view.findViewById<TextView>(R.id.gameText).text =   "Du gik bankrupt"
            viewModel.score.value = 0
            ret = 0
        }else if(spin == 12){
            view.findViewById<TextView>(R.id.gameText).text =   "Du fik en ekstra tur"
            ret = 0
            lives.add(WordModel('t',true))
            createHearts(view)
        }else{
            view.findViewById<TextView>(R.id.gameText).text =   "Du mistede en tur"
            ret = 0
            lives.removeAt(lives.size - 1)
            createHearts(view)
        }
        return ret

    }

}