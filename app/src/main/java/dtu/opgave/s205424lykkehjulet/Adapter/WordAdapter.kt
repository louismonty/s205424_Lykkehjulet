package dtu.opgave.s205424lykkehjulet.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dtu.opgave.s205424lykkehjulet.Model.WordModel
import dtu.opgave.s205424lykkehjulet.R

class WordAdapter (private val dataset: List<WordModel>) : RecyclerView.Adapter<WordAdapter.ItemViewHolder>(){

    //from Lesson 05 slide 30

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.word_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val wordModel = dataset[position]
        holder.textView.text = wordModel.letter.toString();
        if(wordModel.visablity){
            holder.textView.visibility = View.VISIBLE
        }else{
            holder.textView.visibility = View.INVISIBLE
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }


    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = itemView.findViewById(R.id.textView)
    }
}