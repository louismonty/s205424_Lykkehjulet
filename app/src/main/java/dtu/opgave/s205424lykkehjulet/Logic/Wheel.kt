package dtu.opgave.s205424lykkehjulet.Logic

import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModel
import dtu.opgave.s205424lykkehjulet.GameFragment
import dtu.opgave.s205424lykkehjulet.Model.WordModel
import dtu.opgave.s205424lykkehjulet.R
import dtu.opgave.s205424lykkehjulet.View.WordViewModel
import kotlin.random.Random

class Wheel {
    fun spinwhele(view: View, viewModel: WordViewModel,lives:ArrayList<WordModel>):Int{
        val spin = Random.nextInt(1,14)
        var ret:Int
        if(spin<11){
            view.findViewById<TextView>(R.id.spinText).text =   "Du får:" + (spin*100).toString()
            ret = spin *100
        }else if(spin == 11){
            view.findViewById<TextView>(R.id.spinText).text =   "Du gik bankrupt"
            viewModel.score.value = 0
            ret = 0
        }else if(spin == 12){
            view.findViewById<TextView>(R.id.spinText).text =   "Du fik en ekstra tur"
            ret = 0
            lives.add(WordModel('t',true))
        }else{
            view.findViewById<TextView>(R.id.spinText).text =   "Du mistede en tur"
            ret = 0
            lives.removeAt(lives.size - 1)
        }
        return ret

    }
}