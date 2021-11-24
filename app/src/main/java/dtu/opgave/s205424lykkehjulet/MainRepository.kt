package dtu.opgave.s205424lykkehjulet

import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import dtu.opgave.s205424lykkehjulet.View.WordViewModel

class MainRepository() {
    var score = MutableLiveData<String>()

    fun getScore(): String {
        return score.value.toString()
    }

    fun setScore(input : String){
        score.value = input
    }

    fun getMutable():MutableLiveData<String>{
        return score
    }

}