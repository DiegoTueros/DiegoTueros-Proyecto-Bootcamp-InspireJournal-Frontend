package com.nttdata.inspirationjournal2.view.home


import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nttdata.inspirationjournal2.R
import com.nttdata.inspirationjournal2.model.InspirationItem
import com.nttdata.inspirationjournal2.utlis.*
import com.nttdata.inspirationjournal2.view.formatDate

class InspirationItemAdapter(
    private val inspirationList: MutableList<InspirationItem>,
    private val onItemClick: (InspirationItem) -> Unit
): RecyclerView.Adapter<InspirationItemAdapter.InspirationViewHolder>() {

    class InspirationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleInspiration = itemView.findViewById<TextView>(R.id.title)
        val dateInspiration = itemView.findViewById<TextView>(R.id.date)
        val imageInspiration = itemView.findViewById<ImageView>(R.id.ic_type)
        val iconFavorite = itemView.findViewById<ImageView>(R.id.favorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InspirationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_inspiration, parent, false)
        return InspirationViewHolder(view)
    }

    override fun onBindViewHolder(holder: InspirationViewHolder, position: Int) {
        val inspirationItem = inspirationList[position]
        with(inspirationItem) {
            holder.titleInspiration.text = title
            holder.titleInspiration.setTextColor(Color.parseColor(category.color_primary))
            holder.dateInspiration.text = formatDate(created_at.toString())
            holder.dateInspiration.setTextColor(Color.parseColor(category.color_primary))
            holder.imageInspiration.setImageResource(imageInspiration(category.icono))
            holder.iconFavorite.setImageResource(favoriteIcon(status))

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