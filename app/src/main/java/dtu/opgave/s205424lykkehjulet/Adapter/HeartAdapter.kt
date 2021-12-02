package dtu.opgave.s205424lykkehjulet.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dtu.opgave.s205424lykkehjulet.Model.WordModel
import dtu.opgave.s205424lykkehjulet.R



class HeartAdapter (private val dataset: List<WordModel>) : RecyclerView.Adapter<HeartAdapter.ItemViewHolder>(){

    //from Lesson 05 slide 30

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.heart_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return dataset.size
    }


    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }
}