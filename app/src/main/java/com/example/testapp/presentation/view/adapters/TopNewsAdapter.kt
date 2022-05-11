package com.example.testapp.presentation.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.databinding.TopNewsHolderBinding
import com.example.testapp.model.entities.News

class TopNewsAdapter(private val topNews: List<News>) : RecyclerView.Adapter<TopNewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopNewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TopNewsHolderBinding.inflate(layoutInflater, parent, false)

        return TopNewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopNewsViewHolder, position: Int) {
        holder.bind(topNews)
    }

    override fun getItemCount(): Int = topNews.size

}