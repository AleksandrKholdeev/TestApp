package com.example.testapp.presentation.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.testapp.databinding.TopNewsItemBinding
import com.example.testapp.model.entities.News

class TopNewsItemAdapter : ListAdapter<News, TopNewsItemViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean = false

            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopNewsItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TopNewsItemBinding.inflate(layoutInflater, parent, false)

        return TopNewsItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopNewsItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}