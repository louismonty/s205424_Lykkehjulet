package dtu.opgave.s205424lykkehjulet.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dtu.opgave.s205424lykkehjulet.Model.HighscoreModel
import dtu.opgave.s205424lykkehjulet.R

class HighscoreAdapter (private val dataset: List<HighscoreModel>) : RecyclerView.Adapter<HighscoreAdapter.ItemViewHolder>(){

    //from Lesson 05 slide 30

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.highscore_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val highscoreModel = dataset[position]
        holder.textView.text = highscoreModel.name+" : "+highscoreModel.score.toString()
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = itemView.findViewById(R.id.textView3)
    }
}