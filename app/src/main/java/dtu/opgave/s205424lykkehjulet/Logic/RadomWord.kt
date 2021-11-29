package dtu.opgave.s205424lykkehjulet.Logic

import android.content.res.Resources
import dtu.opgave.s205424lykkehjulet.Model.WordModel
import dtu.opgave.s205424lykkehjulet.R
import kotlin.random.Random

class RadomWord {

    fun findRandomWord( stringCategory: String,res:Resources): String? {
        var stringArray:Array<String>

        if(stringCategory == "planets_array") {
            stringArray = res.getStringArray(R.array.planets_array)
        }else{
            stringArray = res.getStringArray(R.array.some_shit)
        }


        return  stringArray[Random.nextInt(0, stringArray.size)]
    }


}