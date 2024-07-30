package mohsin.code.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mohsin.code.fragments.R
import mohsin.code.fragments.Songs

class ItemAdapter(
    private val items: ArrayList<Songs>,
    private val onItemClick: (Songs) -> Unit
) : RecyclerView.Adapter<ItemAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_count, parent, false)
        return MyViewHolder(itemView)
    }

    //define the size of item in recycler view
    override fun getItemCount(): Int {
        return items.size
    }

    
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val song = items[position]
        holder.songName.text = song.songName
        holder.singerName.text = song.singerName
        holder.itemView.setOnClickListener {
            onItemClick(song)
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val songName: TextView = itemView.findViewById(R.id.tvSongName)
        val singerName: TextView = itemView.findViewById(R.id.tvSingerName)
    }
}