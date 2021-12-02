package dtu.opgave.s205424lykkehjulet.View

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WordViewModel:ViewModel() {
    //live data
    val score: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
}