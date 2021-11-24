package dtu.opgave.s205424lykkehjulet

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
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

class GameFragment : Fragment() {
    private var randomWord : String = "TEST"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }



    val data = ArrayList<WordModel>()
    private val viewModel: WordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_game, container, false)

        val sharedPreferences: SharedPreferences? = this.activity?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()


        viewModel.score.value = sharedPreferences?.getInt("score",0)


        viewModel.score.observe(viewLifecycleOwner,androidx.lifecycle.Observer { newInt->
            view.findViewById<TextView>(R.id.score).text ="score" + newInt.toString()
            editor?.apply {
                putInt("score", newInt!!)
            }?.apply()
        })
        for(letter in randomWord) {
            data.add(WordModel(letter.toString(), false))
        }

        lives.add(WordModel("t",true))
        lives.add(WordModel("t",true))
        lives.add(WordModel("t",true))
        lives.add(WordModel("t",true))
        lives.add(WordModel("t",true))



        val button:Button = view.findViewById(R.id.button)
        button.setOnClickListener{
            var guessRight = false
            for(letter in data) {
                if(letter.letter == view.findViewById<EditText>(R.id.editTextTextPersonName).text.toString()) {
                    viewModel.score.value = viewModel.score.value!! + 1
                    letter.visablity = true;
                    guessRight = true
                }
            }
            if(!guessRight){
                lives.removeAt(lives.size-1)
            }
            createTodayView(view)
            createHearts(view)
        }



        createTodayView(view)
        createHearts(view)


        return view
    }


    override fun onSaveInstanceState(outState: Bundle) { // Here You have to save count value
        super.onSaveInstanceState(outState)
        outState.putInt("score",viewModel.score.value!!)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) { // Here You have to restore count value
        super.onActivityCreated(savedInstanceState)
        if(savedInstanceState != null ){
            viewModel.score.value = savedInstanceState.getInt("score")
        }else{
            viewModel.score.value = 0
        }

    }


    private fun createTodayView(view: View){

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
    private fun getRandomWord():String{

        return "test"
    }
    fun reload(){}

}