package dtu.opgave.s205424lykkehjulet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.get
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dtu.opgave.s205424lykkehjulet.Adapter.WordAdapter
import dtu.opgave.s205424lykkehjulet.Model.WordModel
import dtu.opgave.s205424lykkehjulet.View.WordViewModel
import java.util.*

class GameFragment : Fragment() {
    private val randomWord : String = "Test"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    private val viewModel: WordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_game, container, false)
        viewModel.score.value = 0
        viewModel.score.observe(viewLifecycleOwner,androidx.lifecycle.Observer { newInt->
            view.findViewById<TextView>(R.id.score).text = newInt.toString()
        })
        val button:Button = view.findViewById(R.id.button)
        button.setOnClickListener{
            viewModel.score.value = viewModel.score.value!! +1
            if (getFragmentManager() != null) {

                getFragmentManager()
                    ?.beginTransaction()
                    .detach(this)
                    .attach(this)
                    .commit();
            }
        }
        createTodayView(view)


        return view
    }

    private fun createTodayView(view: View){

        val today_event_recyclerview = view.findViewById<RecyclerView>(R.id.displayText)

        today_event_recyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val data = ArrayList<WordModel>()




        for(letter in randomWord) {
            data.add(WordModel(letter.toString(), false))
        }

        val adapter = WordAdapter(data)

        today_event_recyclerview.adapter = adapter
    }
    private fun getRandomWord():String{

        return "test"
    }
    fun reload(){}

}