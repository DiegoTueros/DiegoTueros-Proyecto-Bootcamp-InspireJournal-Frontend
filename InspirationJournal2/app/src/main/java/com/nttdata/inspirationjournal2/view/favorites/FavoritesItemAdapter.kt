package com.nttdata.inspirationjournal2.view.favorites

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nttdata.inspirationjournal2.R
import com.nttdata.inspirationjournal2.model.InspirationItem
import com.nttdata.inspirationjournal2.utlis.imageInspiration
import com.nttdata.inspirationjournal2.view.formatDate

class FavoritesItemAdapter(
    private val inspirationList: MutableList<InspirationItem>,
    private val onItemClick: (InspirationItem) -> Unit
): RecyclerView.Adapter<FavoritesItemAdapter.InspirationViewHolder>() {

    class InspirationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleInspiration = itemView.findViewById<TextView>(R.id.description)
        val dateInspiration = itemView.findViewById<TextView>(R.id.date)
        val imageInspiration = itemView.findViewById<ImageView>(R.id.ic_type)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InspirationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        return InspirationViewHolder(view)
    }

    override fun onBindViewHolder(holder: InspirationViewHolder, position: Int) {
        val inspirationItem = inspirationList[position]
        with(inspirationItem) {
            holder.titleInspiration.text = description
            holder.titleInspiration.setTextColor(Color.parseColor(category.color_primary))
            holder.dateInspiration.text = formatDate(created_at.toString())
            holder.dateInspiration.setTextColor(Color.parseColor(category.color_primary))
            holder.imageInspiration.setImageResource(imageInspiration(category.icono))

        }
        holder.itemView.setOnClickListener{
            onItemClick(inspirationItem)

        }
    }

    fun addList(inspirationList: List<InspirationItem>) {
        this.inspirationList.addAll(inspirationList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = inspirationList.size

}