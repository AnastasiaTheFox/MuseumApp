package com.akomissarova.testmuseum.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akomissarova.testmuseum.R
import com.akomissarova.testmuseum.domain.ArtCollectionsListItem

class ArtCollectionsListAdapter: RecyclerView.Adapter<CollectionsListItemViewHolder>() {

    private val list: MutableList<ArtCollectionsListItem> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CollectionsListItemViewHolder =
        CollectionsListItemViewHolder.from(parent)


    override fun onBindViewHolder(holder: CollectionsListItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    private fun getItem(position: Int): ArtCollectionsListItem = list[position]

    override fun getItemCount(): Int = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(list: List<ArtCollectionsListItem>) {
        this.list.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }
}

class CollectionsListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val name: TextView = itemView.findViewById(R.id.collectionNameView)

    fun bind(item: ArtCollectionsListItem) {
        name.text = item.name
    }

    companion object {
        fun from(parent: ViewGroup): CollectionsListItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = layoutInflater.inflate(R.layout.item_art_collections_list, parent, false)
            return CollectionsListItemViewHolder(binding)
        }
    }
}
