package dtu.opgave.s205424lykkehjulet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dtu.opgave.s205424lykkehjulet.Adapter.WordAdapter
import dtu.opgave.s205424lykkehjulet.Model.WordModel
import java.util.ArrayList

class GameFragment : Fragment() {
    private val randomWord : String = "Test"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_game, container, false)
        createTodayView(view)

        return view
    }

    private fun createTodayView(view: View){

        val today_event_recyclerview = view.findViewById<RecyclerView>(R.id.displayText)

        today_event_recyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val data = ArrayList<WordModel>()





        data.add(WordModel("T",true))
        data.add(WordModel("e",false))
        data.add(WordModel("s",true))
        data.add(WordModel("T",false))

        val adapter = WordAdapter(data)

        today_event_recyclerview.adapter = adapter
    }
    private fun getRandomWord():String{
        return "test"
    }
    fun reload(){}

}