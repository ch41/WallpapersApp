package com.example.wallpapersapp.ui.screens.historyfragment.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.data.db.searchdb.SearchDb
import com.example.wallpapersapp.R
import com.example.wallpapersapp.databinding.HistoryCardViewBinding
import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat
import java.util.*

class HistorySearchAdapter(
    val updateQuery: (searchDb: SearchDb) -> Unit,
    val timeConverter: (time: String) -> String
) :
    ListAdapter<SearchDb, HistorySearchAdapter.SearchsViewHolder>(ImagesDiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_card_view, parent, false)
        return SearchsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SearchsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = HistoryCardViewBinding.bind(itemView)

        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        fun bind(searches: SearchDb?) = with(itemView) {
            if (!searches?.searchQuery.isNullOrEmpty()) {
                binding.searchQueryTextView.text = searches?.searchQuery
                binding.resultAmountTextView.text =
                    "${searches?.resultAmount.toString()} результатов, ${timeConverter(searches?.searchDate.toString())} "

                if (searches?.isFavorite == true) binding.favoritesImageButton.setColorFilter(
                    resources.getColor(R.color.main_color)
                ) else binding.favoritesImageButton.setColorFilter(resources.getColor(R.color.text_color))

                binding.favoritesImageButton.setOnClickListener {
                    searches?.let { searchDb -> updateQuery(searchDb) }
                    notifyDataSetChanged()
                }
            }
        }
    }
}

private object ImagesDiffCallback : DiffUtil.ItemCallback<SearchDb>() {
    override fun areItemsTheSame(oldItem: SearchDb, newItem: SearchDb): Boolean =
        oldItem.searchQuery == newItem.searchQuery

    override fun areContentsTheSame(oldItem: SearchDb, newItem: SearchDb): Boolean =
        oldItem.isFavorite == newItem.isFavorite
}


