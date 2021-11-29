package dtu.opgave.s205424lykkehjulet.Logic

import android.view.View
import android.widget.EditText
import dtu.opgave.s205424lykkehjulet.Model.WordModel
import dtu.opgave.s205424lykkehjulet.R
import dtu.opgave.s205424lykkehjulet.View.WordViewModel



class Guees {
    fun Guess(view: View,viewModel:WordViewModel,lives:ArrayList<WordModel>,data:ArrayList<WordModel>,reward:Int) {

        var guessRight = false
        var guessAll = true
        for (letter in data) {
            if (!letter.visablity) {
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
    }

}