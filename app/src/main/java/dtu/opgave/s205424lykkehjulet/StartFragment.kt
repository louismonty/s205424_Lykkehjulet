package dtu.opgave.s205424lykkehjulet

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class StartFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_game, container, false)


        val startButton:Button = view.findViewById<Button>(R.id.nav_start)


        startButton.setOnClickListener{
            startActivity(Intent(this, GameActivity::class.java))
        }

        return view
    }

}