package dtu.opgave.s205424lykkehjulet.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dtu.opgave.s205424lykkehjulet.Model.WordModel
import dtu.opgave.s205424lykkehjulet.R

class WordAdapter (private val mList: List<WordModel>) : RecyclerView.Adapter<WordAdapter.ViewHolder>(){
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.word_item, parent, false)

        return ViewHolder(view)
    }
    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wordModel = mList[position]
        holder.textView.text = wordModel.letter.toString();
        if(wordModel.visablity){
            holder.textView.visibility = View.VISIBLE
        }else{
            holder.textView.visibility = View.INVISIBLE
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }


    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
    }
}