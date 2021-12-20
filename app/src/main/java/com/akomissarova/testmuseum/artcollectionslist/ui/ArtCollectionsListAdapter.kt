package com.akomissarova.testmuseum.artcollectionslist.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akomissarova.testmuseum.R
import com.akomissarova.testmuseum.artcollectionslist.domain.ArtCollectionsListViewItem
import com.bumptech.glide.Glide

class ArtCollectionsListAdapter: RecyclerView.Adapter<CollectionsListItemViewHolder>() {

    private val list: MutableList<ArtCollectionsListViewItem> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CollectionsListItemViewHolder =
        CollectionsListItemViewHolder.from(parent)


    override fun onBindViewHolder(holder: CollectionsListItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    private fun getItem(position: Int): ArtCollectionsListViewItem = list[position]

    override fun getItemCount(): Int = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(list: List<ArtCollectionsListViewItem>) {
        this.list.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }
}

class CollectionsListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title: TextView = itemView.findViewById(R.id.collectionTitleView)
    private val author: TextView = itemView.findViewById(R.id.collectionAuthorView)
    private val image: ImageView = itemView.findViewById(R.id.collectionImage)

    fun bind(item: ArtCollectionsListViewItem) {
        title.text = item.title
        author.text = itemView.resources.getString(R.string.collection_author_name, item.author)
        Glide.with(itemView.context)
            .load(item.imageUrl)
            .into(image)
    }

    companion object {
        fun from(parent: ViewGroup): CollectionsListItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = layoutInflater.inflate(R.layout.item_art_collections_list, parent, false)
            return CollectionsListItemViewHolder(binding)
        }
    }
}
